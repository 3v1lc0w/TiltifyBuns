package com.buncord.tiltifybuns.tiltify;

import com.buncord.tiltifybuns.TiltifyBuns;
import com.buncord.tiltifybuns.capabilities.ITiltifyBunsCapability;
import com.buncord.tiltifybuns.entities.ScalableRabbit;
import com.buncord.tiltifybuns.init.EntityInit;
import com.buncord.tiltifybuns.tiltify.data.DonationData;
import com.buncord.tiltifybuns.tiltify.data.DonationsResponse;
import com.buncord.tiltifybuns.tiltify.data.TokenResponse;
import com.google.gson.Gson;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.server.ServerLifecycleHooks;

public class TiltifyClient {

  private static final ScheduledExecutorService scheduler =
      Executors.newScheduledThreadPool(1);

  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSSSSX");

  private final String clientID;
  private final String clientSecret;

  private String token = null;
  private LocalDateTime tokenExpireDateTime = null;

  private String campaignID = null;
  private OffsetDateTime latestDonationDateTime = null;

  public TiltifyClient(String clientID, String clientSecret) {
    this.clientID = clientID;
    this.clientSecret = clientSecret;

    getToken();
  }

  private void getToken() {
    try {
      HttpClient httpClient = HttpClient.newHttpClient();

      Map<String, String> formData = new HashMap<>();
      formData.put("client_id", this.clientID);
      formData.put("client_secret", this.clientSecret);
      formData.put("grant_type", "client_credentials");
      formData.put("scope", "public");

      String url = "https://v5api.tiltify.com/oauth/token?" + getFormDataAsString(formData);

      HttpRequest request = HttpRequest.newBuilder()
                                       .uri(URI.create(url))
                                       .timeout(Duration.ofMinutes(1))
                                       .POST(BodyPublishers.noBody())
                                       .build();

      HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

      if (response.statusCode() == 200) {
        String responseString = response.body();

        TokenResponse tokenResponse = new Gson().fromJson(responseString, TokenResponse.class);

        this.token = tokenResponse.getAccess_token();
        this.tokenExpireDateTime = LocalDateTime.now().plus(
            tokenResponse.getExpires_in(),
            ChronoUnit.SECONDS
        );
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void readDonations() {
    if (this.token == null || LocalDateTime.now().isAfter(this.tokenExpireDateTime)) {
      this.getToken();
    }

    try {
      boolean keepPolling = true;
      OffsetDateTime latestCompletionDateTime = null;
      OffsetDateTime completedBeforeDateTime = null;

      do {
        HttpClient httpClient = HttpClient.newHttpClient();

        String url = "https://v5api.tiltify.com/api/public/campaigns/" + this.campaignID + "/donations";

        Map<String, String> formData = new HashMap<>();

        if (this.latestDonationDateTime != null) {
          formData.put("completed_after", this.latestDonationDateTime.format(dtf));
        }

        if (completedBeforeDateTime != null) {
          formData.put("completed_before", completedBeforeDateTime.format(dtf));
        }

        if (!formData.isEmpty()) {
          url += "?" + getFormDataAsString(formData);
        }

        System.out.println(URLDecoder.decode(url, StandardCharsets.UTF_8));

        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(url))
                                         .timeout(Duration.ofMinutes(1))
                                         .header("Authorization", "Bearer " + this.token)
                                         .GET()
                                         .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
          String responseString = response.body();

          DonationsResponse donationsResponse = new Gson().fromJson(responseString, DonationsResponse.class);

          Random r = new Random();
          List<ServerPlayer> players = ServerLifecycleHooks.getCurrentServer()
                                                           .getPlayerList()
                                                           .getPlayers();

          if (donationsResponse.getData().isEmpty()) {
            keepPolling = false;
          }

          for (DonationData donationData : donationsResponse.getData()) {
            OffsetDateTime completedAt = OffsetDateTime.parse(donationData.getCompleted_at(), dtf);

            String donorName = donationData.getDonor_name();

            int playerCount = players.size();
            int playerIndex = r.nextInt(playerCount);

            Player selectedPlayer = players.get(playerIndex);
            Level level = selectedPlayer.getLevel();

            ITiltifyBunsCapability tiltifyBunsCapability = TiltifyBuns.getCapability(level);

            if (!tiltifyBunsCapability.hasDonationID(donationData.getId())) {
              if (!tiltifyBunsCapability.hasBunName(donorName)) {
                ScalableRabbit rabbit = new ScalableRabbit(EntityInit.SCALABUN.get(), level);
                rabbit.copyPosition(selectedPlayer);
                rabbit.setCustomName(new TextComponent(donorName));
                rabbit.setCustomNameVisible(true);
                rabbit.setInvulnerable(true);

                float amount = donationData.getAmount().getValue();

                rabbit.setScale(0.6F + (amount / 10.0F));

                level.addFreshEntity(rabbit);
                tiltifyBunsCapability.addBun(donorName, rabbit.getId());

                rabbit.refreshDimensions();
              } else {
                int bunID = tiltifyBunsCapability.getBun(donorName);
                ScalableRabbit rabbit = (ScalableRabbit)level.getEntity(bunID);

                if (rabbit != null) {
                  float amount = tiltifyBunsCapability.getTotal(donorName)
                      + donationData.getAmount().getValue();

                  rabbit.setScale(0.6F + (amount / 10.0F));

                  rabbit.refreshDimensions();
                }
              }

              tiltifyBunsCapability.addDonation(
                  donationData.getId(),
                  donorName,
                  donationData.getAmount().getValue()
              );
            }

            if (latestCompletionDateTime == null || latestCompletionDateTime.isBefore(completedAt)) {
              latestCompletionDateTime = completedAt;
            }

            if (completedBeforeDateTime == null || completedBeforeDateTime.isAfter(completedAt)) {
              completedBeforeDateTime = completedAt;
            }
          }

          if (completedBeforeDateTime != null) {
            completedBeforeDateTime = completedBeforeDateTime.minus(1, ChronoUnit.MICROS);
          }
        } else {
          keepPolling = false;
        }
      } while (keepPolling);

      if (latestCompletionDateTime != null &&
              (this.latestDonationDateTime == null || this.latestDonationDateTime.isBefore(latestCompletionDateTime))
      ) {
        this.latestDonationDateTime = latestCompletionDateTime.plus(1, ChronoUnit.MICROS);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void watchCampaign(String campaignID) {
    if (this.token != null) {
      this.campaignID = campaignID;

      scheduler.scheduleAtFixedRate(new PollDonationsTask(), 0, 30, TimeUnit.SECONDS);
    }
  }

  private static class PollDonationsTask implements Runnable {
    @Override public void run() {
      if (Tiltify.CLIENT != null) {
        Tiltify.CLIENT.readDonations();
      }
    }
  }

  private static String getFormDataAsString(Map<String, String> formData) {
    StringBuilder formBodyBuilder = new StringBuilder();
    for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
      if (formBodyBuilder.length() > 0) {
        formBodyBuilder.append("&");
      }
      formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
      formBodyBuilder.append("=");
      formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
    }
    return formBodyBuilder.toString();
  }

}

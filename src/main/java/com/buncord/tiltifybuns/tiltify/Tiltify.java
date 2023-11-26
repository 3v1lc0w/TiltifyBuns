package com.buncord.tiltifybuns.tiltify;

public class Tiltify {

  public static TiltifyClient CLIENT = null;

  public static void campaign(String campaignID) {
    if (Tiltify.CLIENT != null) {
      Tiltify.CLIENT.watchCampaign(campaignID);
    }
  }

  public static void login(String clientID, String clientSecret) {
    Tiltify.CLIENT = new TiltifyClient(clientID, clientSecret);
  }

}

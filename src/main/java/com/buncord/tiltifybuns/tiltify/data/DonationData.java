package com.buncord.tiltifybuns.tiltify.data;

public class DonationData {

  private DonationAmount amount;
  private String campaign_id;
  private String completed_at;
  private String created_at;
  private String donor_comment;
  private String donor_name;
  private String fundraising_event_id;
  private String id;
  private Integer legacy_id;
  private String poll_id;
  private String poll_option_id;
  private String reward_id;
  private boolean sustained;
  private String target_id;
  private String team_event_id;

  public DonationAmount getAmount() {
    return amount;
  }

  public String getCampaign_id() {
    return campaign_id;
  }

  public String getCompleted_at() {
    return completed_at;
  }

  public String getCreated_at() {
    return created_at;
  }

  public String getDonor_comment() {
    return donor_comment;
  }

  public String getDonor_name() {
    return donor_name;
  }

  public String getFundraising_event_id() {
    return fundraising_event_id;
  }

  public String getId() {
    return id;
  }

  public Integer getLegacy_id() {
    return legacy_id;
  }

  public String getPoll_id() {
    return poll_id;
  }

  public String getPoll_option_id() {
    return poll_option_id;
  }

  public String getReward_id() {
    return reward_id;
  }

  public boolean isSustained() {
    return sustained;
  }

  public String getTarget_id() {
    return target_id;
  }

  public String getTeam_event_id() {
    return team_event_id;
  }

}

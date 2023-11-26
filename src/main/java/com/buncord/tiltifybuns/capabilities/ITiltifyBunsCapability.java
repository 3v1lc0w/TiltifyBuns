package com.buncord.tiltifybuns.capabilities;

public interface ITiltifyBunsCapability {

  boolean hasBunName(String bunName);

  boolean hasDonationID(String donationID);

  void addDonation(String donationID, String bunName, float value);

  Float getTotal(String bunName);

  int getBun(String bunName);
  void addBun(String bunName, int id);

}

package com.buncord.tiltifybuns.capabilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TiltifyBunsCapability implements ITiltifyBunsCapability {

  private Set<String> bunNames;
  private Set<String> donationIDs;
  private Map<String, Float> totalByName;
  private Map<String, Integer> bunByName;

  public TiltifyBunsCapability() {
    this.bunNames = Collections.synchronizedSet(new HashSet<>());
    this.donationIDs = Collections.synchronizedSet(new HashSet<>());
    this.totalByName = Collections.synchronizedMap(new HashMap<>());
    this.bunByName = Collections.synchronizedMap(new HashMap<>());
  }

  @Override
  public boolean hasBunName(String bunName) {
    return this.bunNames.contains(bunName);
  }

  @Override
  public boolean hasDonationID(String donationID) {
    return this.donationIDs.contains(donationID);
  }

  @Override
  public void addDonation(String donationID, String bunName, float value) {
    this.donationIDs.add(donationID);
    this.bunNames.add(bunName);

    if (totalByName.containsKey(bunName)) {
      totalByName.put(bunName, totalByName.get(bunName) + value);
    } else {
      totalByName.put(bunName, value);
    }
  }

  @Override
  public Float getTotal(String bunName) {
    return this.totalByName.get(bunName);
  }

  @Override
  public int getBun(String bunName) {
    return this.bunByName.get(bunName);
  }

  @Override
  public void addBun(String bunName, int id) {
    this.bunByName.put(bunName, id);
  }
}

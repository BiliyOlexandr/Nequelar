package com.ommyf.nequelar.shops.data;

import android.location.Location;

/**
 * Created by zayts on 3/27/2017.
 */

public class Shop {

  private int mId;
  private String mName;
  private String mDescription;
  private String mImgUri;
  private Location mLocation;
  private int mCommentsId;

  public Shop(int mId, String mName, String mDescription, String mImgUri, Location mLocation,
      int mCommentsId) {
    this.mId = mId;
    this.mName = mName;
    this.mDescription = mDescription;
    this.mImgUri = mImgUri;
    this.mLocation = mLocation;
    this.mCommentsId = mCommentsId;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public String getDescription() {
    return mDescription;
  }

  public String getImgUri() {
    return mImgUri;
  }

  public Location getLocation() {
    return mLocation;
  }

  public int getCommentsId() {
    return mCommentsId;
  }
}

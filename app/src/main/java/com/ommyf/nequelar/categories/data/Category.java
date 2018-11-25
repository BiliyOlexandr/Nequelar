package com.ommyf.nequelar.categories.data;

/**
 * Created by zayts on 3/18/2017.
 */

public class Category {

  private int mId;
  private String mName;
  private String mDescription;
  private String mImgUri;

  public Category(int mId, String mName, String mDescription, String mImgUri) {
    this.mId = mId;
    this.mName = mName;
    this.mDescription = mDescription;
    this.mImgUri = mImgUri;
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

}

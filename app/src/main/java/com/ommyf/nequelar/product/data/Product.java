package com.ommyf.nequelar.product.data;

/**
 * Created by zayts on 3/19/2017.
 */

public class Product {

  private int mId;
  private double mPrice;
  private String mName;
  private String mDescription;
  private String mImgUri;

  public Product(int mId, double mPrice, String mName, String mDescription, String mImgUri) {
    this.mId = mId;
    this.mPrice = mPrice;
    this.mName = mName;
    this.mDescription = mDescription;
    this.mImgUri = mImgUri;
  }

  public int getId() {
    return mId;
  }

  public double getPrice() {
    return mPrice;
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

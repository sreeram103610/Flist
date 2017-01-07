package com.maadlabs.flist.model;

/**
 * Created by brainfreak on 12/28/16.
 */

public class LocalItem {

    String mName;
    float mQuantity;
    String mType;
    Location mLocation;
    String mImage;
    String mUnit;
    boolean mItemPurchased;

    public boolean isItemPurchased() {
        return mItemPurchased;
    }

    public void setItemPurchased(boolean itemPurchased) {
        mItemPurchased = itemPurchased;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getQuantity() {
        return mQuantity;
    }

    public void setQuantity(float quantity) {
        mQuantity = quantity;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }
}

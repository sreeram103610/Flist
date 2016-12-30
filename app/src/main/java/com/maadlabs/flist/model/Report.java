package com.maadlabs.flist.model;

/**
 * Created by brainfreak on 12/28/16.
 */

public class Report {

    Consumer mConsumer;
    Item mItem;
    String mPhotoURI;

    public Consumer getConsumer() {
        return mConsumer;
    }

    public void setConsumer(Consumer consumer) {
        mConsumer = consumer;
    }

    public Item getItem() {
        return mItem;
    }

    public void setItem(Item item) {
        mItem = item;
    }

    public String getPhotoURI() {
        return mPhotoURI;
    }

    public void setPhotoURI(String photoURI) {
        mPhotoURI = photoURI;
    }
}

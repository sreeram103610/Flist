package com.maadlabs.flist.model;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by brainfreak on 12/28/16.
 */

public interface Item {

    String getName();
    float getQuantity();
    String getType();
    Location getLocation();
    String getIcon();
}

package com.maadlabs.flist.util;

import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by brainfreak on 12/29/16.
 */

public class MyUtil {

    public static final boolean isConnectedToNetwork(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}

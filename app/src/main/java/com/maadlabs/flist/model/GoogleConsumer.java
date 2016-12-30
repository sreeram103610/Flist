package com.maadlabs.flist.model;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by brainfreak on 12/30/16.
 */

public class GoogleConsumer implements Consumer {

    String mUsername;
    String mEmailId;
    String mDisplayPicture;

    public GoogleConsumer(GoogleSignInAccount account) {
        mUsername = account.getDisplayName();
        mEmailId = account.getEmail();
        mDisplayPicture = account.getPhotoUrl() == null ? null : account.getPhotoUrl().getPath();
    }

    @Override
    public String getEmailId() {
        return mEmailId;
    }

    @Override
    public String getUserName() {
        return mUsername;
    }

    @Override
    public String getDisplayPicture() {
        return mDisplayPicture;
    }

    @Override
    public void setUserName(String name) {
        mUsername = name;
    }

    @Override
    public void setEmailId(String email) {
        mEmailId = email;
    }

    @Override
    public void setDisplayPicture(String uri) {
        mDisplayPicture = uri;
    }

    @Override
    public Type getType() {
        return Type.GOOGLE;
    }
}

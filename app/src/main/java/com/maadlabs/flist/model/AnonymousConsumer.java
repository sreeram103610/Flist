package com.maadlabs.flist.model;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by brainfreak on 12/30/16.
 */
public class AnonymousConsumer implements Consumer{

    String mUsername;
    String mEmailId;
    String mDisplayPicture;

    public AnonymousConsumer(FirebaseUser user) {
        mUsername = user.getDisplayName();
        mEmailId = user.getEmail();
        mDisplayPicture = user.getPhotoUrl() == null ? null : user.getPhotoUrl().getPath();
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
        return Type.ANONYMOUS;
    }
}

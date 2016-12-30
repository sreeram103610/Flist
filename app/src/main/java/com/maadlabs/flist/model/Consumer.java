package com.maadlabs.flist.model;

import java.security.PublicKey;

/**
 * Created by brainfreak on 12/28/16.
 */

public interface Consumer {

    public static enum Type {
        GOOGLE, ANONYMOUS;
    }

    public String getEmailId();
    public String getUserName();
    public String getDisplayPicture();
    public void setUserName(String name);
    public void setEmailId(String email);
    public void setDisplayPicture(String uri);
    public Type getType();
}

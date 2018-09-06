package com.cloud.storage.common;

import java.io.Serializable;

public class AuthPackage extends Package implements Serializable {
    private String login;
    private byte[] pass;
    private boolean auth = false;

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getLogin() {
        return login;
    }

    public byte[] getPass() {
        return pass;
    }

    public AuthPackage(String login, byte[] pass) {
        this.login = login;
        this.pass = pass;
    }
}

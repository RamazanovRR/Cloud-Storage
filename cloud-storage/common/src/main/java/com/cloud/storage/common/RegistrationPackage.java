package com.cloud.storage.common;

import java.io.Serializable;

public class RegistrationPackage implements Serializable {
    private String userName;
    private String userSurename;
    private String loginName;
    private String email;
    private String city;
    private String gender;
    private byte[] pass;
    private boolean auth = false;

    public String getUserName() {
        return userName;
    }

    public String getUserSurename() {
        return userSurename;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public byte[] getPass() {
        return pass;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public RegistrationPackage(String userName, String userSurename, String loginName, String email, String city, String gender, byte[] pass) {
        this.userName = userName;
        this.userSurename = userSurename;
        this.loginName = loginName;
        this.email = email;
        this.city = city;
        this.gender = gender;
        this.pass = pass;
    }

    public void nullField(){
        this.userName = null;
        this.userSurename = null;
        this.loginName = null;
        this.email = null;
        this.city = null;
        this.gender = null;
        this.pass = null;
    }
}

package com.cloud.storage.common;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPackage implements Serializable {

    private String typeMsg;
    private String fileName;
    private byte[] data;
    private String checkSumData;
    private File[] files;
    private String login;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setCheckSumData(String checkSumData) {
        this.checkSumData = checkSumData;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public String getLogin() {
        return login;
    }

    public String getTypeMsg() {
        return typeMsg;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }

    public String getCheckSumData() {
        return checkSumData;
    }

    public File[] getFiles() {
        return files;
    }

    public DataPackage(String typeMsg, String login) {
        this.typeMsg = typeMsg;
        this.login = login;
    }

}

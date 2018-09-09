package com.cloud.storage.common;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPackage implements Serializable {
         private String fileName;
         private byte[] data;
         private String checkSumData;

    public String getCheckSumData() {
        return checkSumData;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }

    public DataPackage(String typeMsg) {

    }

}

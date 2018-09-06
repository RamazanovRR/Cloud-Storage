package com.cloud.storage.common;

import java.io.Serializable;

public abstract class Package implements Serializable {
    private String typeMsg;
    private String title;
    private byte[] bytesData;
    private String chekSum;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBytesData(byte[] bytesData) {
        this.bytesData = bytesData;
    }

    public void setChekSum(String chekSum) {this.chekSum = chekSum; }

    public String getTypeMsg() {
        return typeMsg;
    }

    public String getTitle() {
        return title;
    }

    public byte[] getBytesData() {
        return bytesData;
    }

    public String getChekSum() {
        return chekSum;
    }

    public Package(){}

    public Package(String typeMsg) {
        this.typeMsg = typeMsg;
    }
}

package com.cloud.storage.client;

public class FileInfo {
    private String nameFile;
    private String sizeFile;

    public FileInfo(String nameFile, String sizeFile) {
        this.nameFile = nameFile;
        this.sizeFile = sizeFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(String sizeFile) {
        this.sizeFile = sizeFile;
    }
}

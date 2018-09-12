package com.cloud.storage.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DirsAndFiles implements Const {
    private static String localPath = "client/local/";
    private static String serverPath = "server/downloads/";

    public static void createLocalDirs(String login) {
        File directory = new File(localPath + login);
        if(!(directory.exists())) directory.mkdirs();

    }

    public static void createServerDirs(String login) {
        File directory = new File(serverPath + login);
        if(!(directory.exists())) directory.mkdirs();
    }

    public static File[] returnLocalList(String login) {
        File[] files = null;
        File directory = new File(localPath+login);
        String[] tmp = directory.list();
        if(!(tmp.length == 0)) {
            files = new File[tmp.length];
            for(int i = 0; i < tmp.length; i++){
                files[i] = new File(localPath + login + "/" + tmp[i]);
            }
        }
        return files;
    }

    public static File[] returnServerList(String login) {
        File[] files = null;
        File directory = new File(serverPath + login);
        String[] tmp = directory.list();
        if(!(tmp.length == 0)) {
            files = new File[tmp.length];
            for (int i = 0; i < tmp.length; i++) {
                files[i] = new File(serverPath + login + "/" + tmp[i]);
            }
        }
        return files;
    }

    public static boolean deleteFile(String nameFile,String login, String localOrServer) {
        boolean tmp = false;
        File file;
        if(localOrServer.equals(LOCAL)) {
            file = new File(localPath + login + "/" + nameFile);
            tmp = file.delete();
        }
        if(localOrServer.equals(SERVER)) {
            file = new File(serverPath + login + "/" + nameFile);
            tmp = file.delete();
        }
        return tmp;
    }

    public static String weight(File file){
        String result = "";
        int big = 1024;
        if(file.length() < big) {
            result = file.length() + "";
            result = result.substring(0, result.lastIndexOf(".") + 3);
            return result += " B";
        } else if (file.length() < big*1024) {
            result = (double)file.length()/big + "";
            result = result.substring(0, result.lastIndexOf(".") + 3);
            return result += " KB";
        } else if (file.length() < big*1024*1024) {
            result = (double)file.length()/big/big + "";
            result = result.substring(0, result.lastIndexOf(".") + 3);
            return result += " MB";
        } else if (file.length() < big*1024*1024*1024) {
            result = (double)file.length()/big/big/big + "";
            result = result.substring(0, result.lastIndexOf(".") + 3);
            return result += " GB";
        } else if (file.length() < big*1024*1024*1024*1024) {
            result = (double)file.length()/big/big/big/big + "";
            result = result.substring(0, result.lastIndexOf(".") + 3);
            return result += " TB";
        } else return result;
    }

    public static void writeAFile(DataPackage dataPackage, String localOrServer) {
        if(dataPackage.getCheckSumData().equals(WriteData.checkSum(dataPackage.getData()))) {
            if(localOrServer.equals(LOCAL)) {
                try {
                    File newFile = new File(localPath + dataPackage.getLogin() + "/" + dataPackage.getFileName());
                    newFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(newFile.getPath());
                    fos.write(dataPackage.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(localOrServer.equals(SERVER)) {
                try {
                    File newFile = new File(serverPath + dataPackage.getLogin() + "/" + dataPackage.getFileName());
                    newFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(newFile.getPath());
                    fos.write(dataPackage.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
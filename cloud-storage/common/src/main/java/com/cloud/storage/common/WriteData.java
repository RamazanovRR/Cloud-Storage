package com.cloud.storage.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteData {

    public WriteData(){

    }

    public static byte[] Write(String fileName) {
        byte[] bytes = null;
        try {
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            int a;
            int count = 0;
            while ( (a = is.read()) != -1) {
                bytes[count] = (byte) a;
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static String checkSum(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        try {
            // Получаем контрольную сумму для файла в виде массива байт
            final MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(bytes);
            byte[] mdBytes = md.digest();

            // Переводим контрольную сумму в виде массива байт в
            // шестнадцатеричное представление
            for(int i = 0; i < mdBytes.length; i++) {
                sb.append(Integer.toString((mdBytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DataPackage.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}

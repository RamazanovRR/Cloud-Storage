package com.cloud.storage.server;

public class ServerMainClass {
    public static void main(String[] args) {

        Database db = new Database();
        db.connect();
        db.createTable();
        db.addUserInTable(addArr(1));
        db.addUserInTable(addArr(2));
        db.addUserInTable(addArr(3));
        db.disconnect();
    }

    public static String[] addArr(int num) {
        String[] testArr = {"test" + num, "test" +num, "test" +num, "test" +num, "test" +num, "test" +num, "test" +num};
        return testArr;
    }
}

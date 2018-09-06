package com.cloud.storage.server;

import java.sql.*;

public class Database {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement ps;


     void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:CloudStorageUsers.db");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка при подключении. Класс не найден.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении.");
            e.printStackTrace();
        }
        System.out.println("Подключение к БД успешно.");

    }

    void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения к БД.");
            e.printStackTrace();
        }
        System.out.println("Отключение от БД успешно");
    }

    public void dropTable() {
        if(connection != null) {
            try {
                statement = connection.createStatement();
                statement.execute("DROP TABLE IF EXISTS `Users`;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Таблица удалена.");
        }
    }

    void createTable() {
        if(connection != null){
            try {
                statement = connection.createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS `Users` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`name` VARCHAR(15) NOT NULL," +
                        "`lastName` VARCHAR(25) NOT NULL, " +
                        "`login_name` VARCHAR(48) UNIQUE  NOT NULL, " +
                        "`email` VARCHAR(48) UNIQUE  NOT NULL, " +
                        "`city` VARCHAR(48) NOT NULL, " +
                        "`gender` VARCHAR(1) NOT NULL, " +
                        "`password` VARCHAR(48) NOT NULL);");
            } catch (SQLException e) {
                System.out.println("Ошибка в создании таблицы 'Users'");
                e.printStackTrace();
            }
            System.out.println("Таблица 'Users' создана или была создана ранее");
        } else System.out.println("Соединение с БД не установлено.");
    }

    boolean addUserInTable(String[] user) {
        if(connection != null) {
            try {
                ps = connection.prepareStatement("INSERT INTO `Users` (`name`, `lastName`, `login_name`, `email`, `city`, `gender`, `password`) VALUES (?, ?, ?, ?, ?, ?, ?);");
                ps.setString(1, user[0]);
                ps.setString(2, user[1]);
                ps.setString(3, user[2]);
                ps.setString(4, user[3]);
                ps.setString(5, user[4]);
                ps.setString(6, user[5]);
                ps.setString(7, user[6]);
                return ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println("Ошибка записи данных");
        return false;
    }

    boolean authInDatabase(String login, String pass) throws SQLException {
        boolean auth;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT `password` FROM `Users` WHERE `login_name` = '" + login +"';");
        auth = resultSet.next();
        if(auth) {
            return resultSet.getString("password").equals(pass);
        } else return auth;
    }
}

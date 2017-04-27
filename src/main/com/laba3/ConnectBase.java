package com.laba3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by set on 23.04.17.
 */
public class ConnectBase {

    public static Connection initConnection() {
        java.sql.Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/chat", "test", "test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}

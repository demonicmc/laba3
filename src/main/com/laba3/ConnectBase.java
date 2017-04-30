package com.laba3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by set on 23.04.17.
 */
public class ConnectBase {

    private static ConnectBase instance = null;

    private ConnectBase() {

        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/chat", "test", "test");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ConnectBase getInstance() {
         if (instance == null){
             instance = new ConnectBase();
         }
         return instance;
    }
}

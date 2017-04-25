package main.com.laba3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by set on 23.04.17.
 */
public class ConnectBase {

    private static Connection connection;

    private String url = "jdbc:postgresql://localhost:5433/postgres";
    private String name = "Test";
    private String password = "ruav";
    private static Logger logger = LoggerFactory.getLogger(ConnectBase.class);


    private ConnectBase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, name, password);
        logger.info("Connection is created");
    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null){
            new ConnectBase();
        }

        return connection;
    }

}

package com.gd.studentapp.db;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final Config config = ConfigFactory.load();

    public Connection getConnnection() {
        Connection connection = null;

        try {
            String connectionURL = config.getString("connectionURL");
            String username = config.getString("username");
            String password = config.getString("password");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, username, password);
        } catch (
                InstantiationException |
                IllegalAccessException |
                ClassNotFoundException |
                SQLException e
                ) {
            e.getLocalizedMessage();
        }

        return connection;
    }
}

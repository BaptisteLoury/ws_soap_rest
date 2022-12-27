package com.train.booking.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.train.booking.model.User;

public class DatabaseManager {
    private static DatabaseManager INSTANCE;

    private Connection connection;

    private DatabaseManager() throws SQLException {
        String url = "jdbc:postgresql://dbserver:5432/train-booking";
        Properties props = new Properties();
        props.setProperty("user", "admin");
        props.setProperty("password", "admin");
        props.setProperty("ssl", "true");
        
        connection = DriverManager.getConnection(url, props);
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean close() {
        try {
            connection.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DatabaseManager getInstance() throws SQLException {
        if(INSTANCE == null) {
            INSTANCE = new DatabaseManager();
        }
        return INSTANCE;
    }

    public boolean createUser(User user) {
        return false;
    }
}

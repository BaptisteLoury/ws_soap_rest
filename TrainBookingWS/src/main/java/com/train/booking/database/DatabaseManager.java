package com.train.booking.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static DatabaseManager getInstance() throws SQLException {
        if(INSTANCE == null) {
            INSTANCE = new DatabaseManager();
        }
        return INSTANCE;
    }

    public User getUser(String token) {
        User usr = null;
        String selectUser = "SELECT user_id, user_last_name, user_first_name, user_birth_date FROM main.USERS WHERE user_token = ?";

        try(PreparedStatement stmt = connection.prepareStatement(selectUser)) {
            stmt.setString(1, token);

            ResultSet res = stmt.executeQuery();
            if(res.next()) {
                usr = new User(
                    res.getInt("user_id"),
                    res.getString("user_last_name"),
                    res.getString("user_first_name"),
                    res.getDate("user_birth_date")
                );
            }

        } catch(SQLException e) {

        }

        return usr;
    }
}

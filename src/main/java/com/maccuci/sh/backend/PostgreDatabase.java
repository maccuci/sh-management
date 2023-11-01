package com.maccuci.sh.backend;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreDatabase implements Database {

    private final String url, user, password;
    @Getter
    private Connection connection;

    public PostgreDatabase(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database");
        } catch (Exception e) {
            System.err.println("Error when trying to connect to PostgreSQL: " + e.getMessage());
        }
    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }
}

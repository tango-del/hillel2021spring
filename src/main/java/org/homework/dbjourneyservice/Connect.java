package org.homework.dbjourneyservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect implements org.homework.dbjourneyservice.Connection {
    private final String URL;
    private final String USER;
    private final String PASS;
    private static java.sql.Connection connection;

    public Connect(final String URL, final String USER, final String PASS) {
        if (URL == null) throw new IllegalArgumentException("URL must be set");
        this.URL = URL;
        this.USER = USER;
        this.PASS = PASS;
    }

    public static Connection getConnection() {
        return connection;
    }

    @Override
    public void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}

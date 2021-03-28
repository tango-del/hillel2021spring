package org.homework.dbjourneyservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect implements org.homework.dbjourneyservice.Connection {
    private final String URL;
    private final String USER;
    private final String PASS;
    private java.sql.Connection connection;

    public Connect(final String URL, final String USER, final String PASS) {
        if (URL == null) throw new IllegalArgumentException("URL must be set");
        if (USER == null) throw new IllegalArgumentException("USER must be set");
        if (PASS == null) throw new IllegalArgumentException("PASS must be set");

        this.URL = URL;
        this.USER = USER;
        this.PASS = PASS;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection == null) return;

        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

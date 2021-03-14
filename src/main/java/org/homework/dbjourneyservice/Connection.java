package org.homework.dbjourneyservice;

import java.sql.SQLException;

public interface Connection {
    void createConnection() throws ClassNotFoundException, SQLException;
    java.sql.Connection getConnection();
    void closeConnection() throws SQLException;
}

package org.homework.dbjourneyservice;

import java.sql.SQLException;

public interface Connection {
    void createConnection() throws ClassNotFoundException, SQLException;
    void closeConnection() throws SQLException;
}

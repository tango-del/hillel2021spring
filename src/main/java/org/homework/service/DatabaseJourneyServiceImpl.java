package org.homework.service;

import org.homework.Journey;
import org.homework.dbjourneyservice.Connect;
import org.homework.dbjourneyservice.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class DatabaseJourneyServiceImpl implements JourneyService {

    private final Connection connect;
    private Statement statement;
    private ResultSet resultSet;

    public DatabaseJourneyServiceImpl(final String URL, final String USER, final String PASS) {
        connect = new Connect(URL, USER, PASS);
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        List<Journey> journeys = new ArrayList<>();
        try {
            resultSet = getRawsWithRoute(stationFrom, stationTo);

            while (resultSet.next()) {
                LocalDate startDate = resultSet.getDate("departure").toLocalDate();
                LocalDate arriveDate = resultSet.getDate("arrival").toLocalDate();

                if (dateFrom.equals(startDate) && dateTo.equals(arriveDate)) {
                    String cityFrom = resultSet.getString("station_from");
                    String cityTo = resultSet.getString("station_to");

                    journeys.add(new Journey(cityFrom, cityTo, startDate, arriveDate));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        if (journeys == null || journeys.isEmpty()) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(journeys);

    }

    private ResultSet getRawsWithRoute(String stationFrom, String stationTo) throws SQLException {
        try {
            connect.createConnection();
            statement = Connect.getConnection().createStatement();
            resultSet = statement.executeQuery(String.format("select * from database_journey_service.journey where route = '%s->%s'", stationFrom, stationTo));

        } catch (SQLException | ClassNotFoundException exception) {
            Connect.getConnection().rollback();
            exception.printStackTrace();
        } finally {
            connect.closeConnection();
        }
        return resultSet;
    }
}

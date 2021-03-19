package org.homework.service;

import org.homework.Journey;
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

    public DatabaseJourneyServiceImpl(final Connection connect) {
        if (connect == null) throw new IllegalArgumentException("connect must be set");


        this.connect = connect;
    }

    @Override
    public Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

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

        if (journeys.isEmpty()) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(journeys);

    }

    private ResultSet getRawsWithRoute(final String stationFrom, final String stationTo) throws SQLException {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");

        try {
            connect.createConnection();
            statement = connect.getConnection().createStatement();
            resultSet = statement.executeQuery(String.format("select * from database_journey_service.journey where route = '%s->%s'", stationFrom, stationTo));

        } catch (SQLException | ClassNotFoundException exception) {
            connect.getConnection().rollback();
            exception.printStackTrace();
        } finally {
            connect.closeConnection();
        }
        return resultSet;
    }
}

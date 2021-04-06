package org.homework;

import org.homework.config.RootConfig;
import org.homework.context.AppContext;
import org.homework.persistence.entity.*;
import org.homework.persistence.entity.enums.DirectionType;
import org.homework.service.JourneyService;
import org.homework.service.TicketClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

public class Starter {
    public static void main(String[] args) {

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journey1 = buildJourney("Odessa", "Kiev", Instant.now(), Instant.now().plusSeconds(10_000L));
        journey1.addStop(buildStop(10D, 176D));
        journey1 = ticketClient.createOrUpdateJourney(journey1);
        journey1.addStop(buildStop(20D, 360D));
        ticketClient.createOrUpdateJourney(journey1);
    }

    private static JourneyEntity buildJourney(final String from, final String to, final Instant dateFrom, final Instant dateTo) {
        if (StringUtils.isEmpty(from)) throw new IllegalArgumentException("station from must be set");
        if (StringUtils.isEmpty(to)) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom(from);
        journeyEntity.setStationTo(to);
        journeyEntity.setDateFrom(dateFrom);
        journeyEntity.setDateTo(dateTo);
        journeyEntity.setDirection(DirectionType.TO);
        journeyEntity.setActive(true);
        return journeyEntity;
    }

    private static StopEntity buildStop(final Double latitude, final Double longitude) {
        if (latitude == null) throw new IllegalArgumentException("latitude must be set");
        if (longitude == null) throw new IllegalArgumentException("longitude must be set");

        final StopAdditionalInfoEntity stopAddInfoEntity = new StopAdditionalInfoEntity();
        stopAddInfoEntity.setLatitude(latitude);
        stopAddInfoEntity.setLongitude(longitude);

        final StopEntity stopEntity = new StopEntity();
        stopEntity.addStopAdditionalInfo(stopAddInfoEntity);

        return stopEntity;
    }

    private static VehicleEntity buildVehicle(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
        return vehicleEntity;
    }
}

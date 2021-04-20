package org.homework;

import org.homework.config.RootConfig;
import org.homework.persistence.entity.*;
import org.homework.persistence.entity.enums.DirectionType;
import org.homework.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.time.Instant;

public class Starter {
    public static void main(String[] args) {

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        /*VehicleEntity vehicle1 = buildVehicle("bus1");
        vehicle1 = ticketClient.createOrUpdateVehicle(vehicle1);

        StopEntity stopEntity = buildStop(13D, 24D, "stop name", "stop description");

        JourneyEntity journey1 = buildJourney("Odessa", "Kiev", Instant.now(), Instant.now().plusSeconds(10_000L));
        vehicle1.setName("bus 2");
        journey1.addVehicle(vehicle1);
        journey1.addStop(stopEntity);

        ticketClient.createOrUpdateJourney(journey1);*/

        //System.out.println("delete vehicle");
        //ticketClient.removeVehicle(vehicle1);

        //System.out.println(ticketClient.findAllVehicles());

        System.out.println(ticketClient.findAllVehiclesByName("bus 2"));

        //System.out.println(ticketClient.findVehicleByIds(1L, 2L, 3L, 4L, 5L));

        //System.out.println(ticketClient.findVehicleById(1L, true));

        //System.out.println("delete journey");
        //ticketClient.removeById(journey1.getId());
//        ticketClient.remove(journey1);

        /*journey1.addStop(buildStop(10D, 176D));

        System.out.println("call create journey");
        journey1 = ticketClient.createOrUpdateJourney(journey1);

        journey1.getStops().get(0).setActive(false);
        journey1.addStop(buildStop(20D, 360D));

        System.out.println("call update journey");
        ticketClient.createOrUpdateJourney(journey1);*/
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

    private static StopEntity buildStop(final Double latitude, final Double longitude, final String name, final String description) {
        if (latitude == null) throw new IllegalArgumentException("latitude must be set");
        if (longitude == null) throw new IllegalArgumentException("longitude must be set");
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");
        if (StringUtils.isEmpty(description)) throw new IllegalArgumentException("description must be set");

        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName(name);
        commonInfo.setDescription(description);

        final StopAdditionalInfoEntity stopAddInfoEntity = new StopAdditionalInfoEntity();
        stopAddInfoEntity.setLatitude(latitude);
        stopAddInfoEntity.setLongitude(longitude);

        final StopEntity stopEntity = new StopEntity();
        stopEntity.addStopAdditionalInfo(stopAddInfoEntity);
        stopEntity.setCommonInfo(commonInfo);

        return stopEntity;
    }

    private static VehicleEntity buildVehicle(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
        return vehicleEntity;
    }
}

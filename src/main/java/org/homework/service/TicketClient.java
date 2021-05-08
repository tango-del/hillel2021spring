package org.homework.service;

import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.entity.StopEntity;
import org.homework.persistence.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class TicketClient {

    /*@Autowired
    private List<JourneyService> journeyServices;*/

    @Autowired
    @Qualifier("transactionalJourneyService")
    private TransactionalJourneyService transactionalJourneyService;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private TransactionalVehicleService vehicleService;

    @Value("${system.message:default value}")
    private String systemMessage;

    @Autowired
    private Environment environment;

    public StopEntity createOrUpdateStop(final StopEntity stopEntity) {
        if (Objects.isNull(stopEntity)) throw new IllegalArgumentException("stopEntity must be set");
        return stopService.createOrUpdateStop(stopEntity);
    }

    public Optional<JourneyEntity> getJourneyById(final Long id, boolean withDependencies) {
        //Assert.notNull(id, "id must be set");
        if (Objects.isNull(id)) return Optional.empty();
        return transactionalJourneyService.findById(id, withDependencies);
    }

    public JourneyEntity createOrUpdateJourney(final JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("entity must be set");
        return transactionalJourneyService.createOrUpdateJourney(journey);
    }

    public VehicleEntity createOrUpdateVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle must be set");

        return vehicleService.createOrUpdate(vehicle);
    }

    public void remove(final JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey must be set");

        transactionalJourneyService.remove(journey);
    }

    public void removeById(final Long journeyId) {
        if (journeyId == 0) return;

        transactionalJourneyService.removeById(journeyId);
    }

    public void removeVehicle(final VehicleEntity vehicleEntity) {
        if (Objects.isNull(vehicleEntity)) throw new IllegalArgumentException("vehicleEntity must be set");

        vehicleService.remove(vehicleEntity);
    }

    public Collection<VehicleEntity> findVehicleByIds(Long... ids) {
        if (ids.length == 0) return Collections.emptyList();

        return vehicleService.findByIds(ids);
    }

    public Optional<VehicleEntity> findVehicleById(Long id, boolean withDependencies) {
        if (id == 0L) return Optional.empty();

        return vehicleService.findById(id, withDependencies);
    }

    public Collection<VehicleEntity> findAllVehicles() {
        return vehicleService.findAll();
    }

    public Collection<VehicleEntity> findAllVehiclesByName(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");
        return vehicleService.findAllByName(name);
    }

    public void disableVehicleById(Long id) {
        if (id == 0) return;

        vehicleService.disableById(id);
    }

    public Collection<VehicleEntity> findAllVehiclesByNameAndNotActive(final String name) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set");
        return vehicleService.findAllByNameAndNotActive(name);
    }

/*    public Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

        for (JourneyService service : journeyServices) {
            System.out.println(service);

            final Collection<Journey> journeys = service.find(stationFrom, stationTo, dateFrom, dateTo);

            if (!CollectionUtils.isEmpty(journeys)) return journeys;
        }

        return Collections.emptyList();
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
//        if (journeyService == null) {
//        if (journeyServices == null) {
//            throw new IllegalArgumentException("journeyService must be set");
//        } else {
//            System.out.println("journeyService set successfully in method afterPropertiesSet() class TicketClient");
//        }
//        System.out.println(systemMessage); // -> @Value("${system.message}")
//        System.out.println(environment.getProperty("system.messag", "def")); // -> private Environment environment

    }

    @PreDestroy
    public void destroy() throws Exception {
//        System.out.println("destroy bean in method destroy() class TicketClient");
    }*/
}

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

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

public class Starter {
    public static void main(String[] args) {
        createEntitiesJourneyStopWithAddInfo();
    }

    private static void createEntitiesJourneyStopWithAddInfo() {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kiev");
        journeyEntity.setStationTo("Odessa");
        journeyEntity.setDateFrom(Instant.now());
        journeyEntity.setDateTo(Instant.now().plusMillis(10_000_000L));
        journeyEntity.setDirection(DirectionType.UNKNOWN);
        journeyEntity.setActive(false);

        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName("bus1");

        journeyEntity.addVehicle(vehicleEntity);



        StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(10D);
        stopAdditionalInfoEntity.setLongitude(176D);

        CommonInfo commonInfoForStop = new CommonInfo();
        commonInfoForStop.setName("stop 1");
        commonInfoForStop.setDescription("stop 1 description");

        StopEntity stopEntity = new StopEntity();
        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        stopEntity.setCommonInfo(commonInfoForStop);
        stopEntity.setApplyToJourneyBuild(stopEntity.isActive());

        journeyEntity.addStop(stopEntity);

        //System.out.println("create stop with id = " + ticketClient.createStop(stopEntity));

        System.out.println("create journey with id = " + ticketClient.createJourney(journeyEntity));

        final Optional<JourneyEntity> journeyById = ticketClient.getJourneyById(journeyEntity.getId(), true);

        final JourneyEntity journey = journeyById.get();
        System.out.println("get all stop by journey " + journey.getStops());
        System.out.println(journeyById);

        journey.setDirection(DirectionType.TO);

        System.out.println("update journey");

        ticketClient.saveJourney(journey);

        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    private static void createJourneyEntityAndSaveToDatabaseWithHibernate() {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);
//        System.out.println(ticketClient.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kiev");
        journeyEntity.setStationTo("Odessa");
        journeyEntity.setDateFrom(Instant.now());
        journeyEntity.setDateTo(Instant.now().plusMillis(10_000_000L));
        journeyEntity.setDirection(DirectionType.UNKNOWN);
        journeyEntity.setActive(false);
        System.out.println("create journey with id = " + ticketClient.createJourney(journeyEntity));

        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    private static void javaAnnotationsConfigAppContextSpringInitTicketClient() {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        System.out.println(">>after init in Starter.class<<");

        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        System.out.println(ticketClient.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    private static void autowireConstructorTicketClient() {
        final ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("common-beans-with-annotations.xml");
        System.out.println("after init");
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);
        System.out.println(ticketClient.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        ticketClient = applicationContext.getBean(TicketClient.class);
        System.out.println(ticketClient.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    private static void xmlWithAnnotationsBeanAppContextSpringInit() {
        final ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("common-beans-with-annotations.xml");

        System.out.println("after init");
        // дай нам bean у которых объекты имеют тип как JourneyService
        JourneyService journeyService = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

//        journeyService = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
//        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
    }

    private static void javaAnnotationsConfigAppContextSpringInit() {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        System.out.println("after init");
        // дай нам bean у которых объекты имеют тип как JourneyService
        JourneyService journeyService = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));


    }

    private static void xmlBeanAppContextSpringInit() {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");

        System.out.println("after init");
        // дай нам bean у которых объекты имеют тип как JourneyService
        JourneyService journeyService = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        journeyService = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
    }

    private static void xmlBeanFactorySpringInit() {
        // поднимается контекст в рамках маленького bean factory который мало что умеет
        final BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("common-beans.xml"));
        final JourneyService journeyService = beanFactory.getBean("inMemoryJourneyService", JourneyService.class);
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(6)));
    }

    private static void exampleBeforeSpringContext() throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        AppContext.load("application.properties");
        final JourneyService journeyService = AppContext.getBean("journeyService");

        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        // можем обернуть в какой-то Exception красивый для клиента
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(6)));
    }
}

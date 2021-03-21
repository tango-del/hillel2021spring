package org.homework;

import org.homework.config.RootConfig;
import org.homework.persistence.entity.JourneyEntity;
import org.homework.service.JourneyService;
import org.homework.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

/*
 TODO
  В Ваш проект добавить hibernate, настроить все необходимое для работы с ним (entity manager, transaction, connection pool).
  При старте системы проект должен устанавливать с бд сразу 30 соединений, максимальное число соединений не должно превышать 150.
  Максимальное время выполнения запроса - не больше 5 минут.
 */
public class DbStarter {
    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kiev");
        System.out.println("create journey with id = " + ticketClient.createJourney(journeyEntity));
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }

    public static void jdbcConnect() {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");

        JourneyService journeyService = applicationContext.getBean("dbService", JourneyService.class);

        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyService.find("Kiev", "Odessa", LocalDate.now(), LocalDate.now().plusDays(1)));
    }
}

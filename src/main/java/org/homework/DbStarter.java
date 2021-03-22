package org.homework;

import org.homework.config.RootConfig;
import org.homework.service.JourneyService;
import org.homework.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        JourneyService journeyService = applicationContext.getBean("transactionJourneyService", JourneyService.class);
        System.out.println(journeyService.find("Lviv", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

//        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);
//        System.out.println(ticketClient.find("Lviv1", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
    }
}

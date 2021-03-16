package org.homework;

import org.homework.service.JourneyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

public class DbStarter {
    public static void main(String[] args) {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");

        JourneyService journeyService = applicationContext.getBean("dbService", JourneyService.class);

        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyService.find("Kiev", "Odessa", LocalDate.now(), LocalDate.now().plusDays(1)));
    }
}
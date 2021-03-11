package org.homework;

import org.homework.context.AppContext;
import org.homework.service.JourneyService;

import java.io.IOException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        AppContext.load("application.properties");
        final JourneyService journeyService = AppContext.getBean("journeyService");

        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        // можем обернуть в какой-то Exception красивый для клиента
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(6)));
    }
}

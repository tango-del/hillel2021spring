package org.homework;

import org.homework.config.RootConfig;
import org.homework.context.AppContext;
import org.homework.service.JourneyService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) {
//        xmlBeanAppContextSpringInit();
        javaAnnotationsConfigAppContextSpringInit();
    }

    private static void javaAnnotationsConfigAppContextSpringInit() {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        System.out.println("after init");
        // дай нам bean у которых объекты имеют тип как JourneyService
        JourneyService journeyService = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        journeyService = applicationContext.getBean("inMemoryJourneyService", JourneyService.class);
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
        final JourneyService journeyService = beanFactory.getBean(JourneyService.class);
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

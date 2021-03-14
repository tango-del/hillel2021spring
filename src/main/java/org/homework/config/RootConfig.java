package org.homework.config;

import org.homework.service.InMemoryJourneyServiceImpl;
import org.homework.service.JourneyService;
import org.homework.service.StubJourneyServiceImpl;
import org.homework.service.TicketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Configuration
//@ComponentScan("org.homework.service")
public class RootConfig {

//    @Bean
//    public TicketClient ticketClient() {
//        getInMemoryJourneyService();
//        return new TicketClient(getInMemoryJourneyService());
//    }
//
//    @Bean("inMemoryJourneyService")
//    public JourneyService getInMemoryJourneyService() {
//        System.out.println("call getInMemoryJourneyService");
//        return new InMemoryJourneyServiceImpl();
//    }
//
//    @Bean
//    @Scope("prototype")
//    public JourneyService inMemoryPrototypeJourneyService() {
//        return new InMemoryJourneyServiceImpl();
//    }
//
//    @Bean
//    public JourneyService stubService() {
//        return new StubJourneyServiceImpl();
//    }
}

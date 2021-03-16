package org.homework.config;

import org.homework.service.InMemoryJourneyServiceImpl;
import org.homework.service.JourneyService;
import org.homework.service.StubJourneyServiceImpl;
import org.homework.service.TicketClient;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.homework.service")
public class RootConfig {

    @Bean
    @Lazy
    public TicketClient ticketClient() {
        return new TicketClient(getInMemoryJourneyService());
    }

    @Bean("inMemoryJourneyService")
    @Lazy
    public JourneyService getInMemoryJourneyService() {
        System.out.println("call getInMemoryJourneyService");
        return new InMemoryJourneyServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public JourneyService inMemoryPrototypeJourneyService() {
        System.out.println("call inMemoryPrototypeJourneyService");
        return new InMemoryJourneyServiceImpl();
    }

    @Bean
    @Lazy
    public JourneyService stubService() {
        return new StubJourneyServiceImpl();
    }
}

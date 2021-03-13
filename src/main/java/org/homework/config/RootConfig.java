package org.homework.config;

import org.homework.service.InMemoryJourneyServiceImpl;
import org.homework.service.JourneyService;
import org.homework.service.StubJourneyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RootConfig {

    @Bean
    public JourneyService inMemoryJourneyService() {
        return new InMemoryJourneyServiceImpl("1");
    }

    @Bean
    @Scope("prototype")
    public JourneyService inMemoryPrototypeJourneyService() {
        return new InMemoryJourneyServiceImpl("1");
    }

    @Bean
    public JourneyService stubService() {
        return new StubJourneyServiceImpl();
    }
}

package org.homework.service;

import org.homework.Journey;
import org.homework.persistence.entity.JourneyEntity;
import org.homework.persistence.entity.StopEntity;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.*;

// клиентский сервис который будет дёргать JourneyService
@Component
public class TicketClient {
//public class TicketClient implements DisposableBean { // -> без использования PreDestroy @Override destroy()
//public class TicketClient implements InitializingBean { // -> без использования @PostConstruct @Override afterPropertiesSet()

    //private JourneyService journeyService; // не будем оперировать какой-то конкретно реализацией, а интерфейсом

    @Autowired
    private List<JourneyService> journeyServices;

    //@Autowired(required = false)
    //@Autowired
    //private Map<String, JourneyService> journeyServices;

    @Autowired
    @Qualifier("transactionalJourneyService")
    private TransactionalJourneyService transactionalJourneyService;

    /*
    system.message -> ищет property с таким названием
    если system.message отсутствует то выведет дефолт значение после двоиточего 'default value'
     */
    @Value("${system.message:default value}")
    private String systemMessage;
//    @Autowired
//    private Environment environment;

    /*@Autowired
    @Qualifier("inMemoryJourneyService")
    public void setJourneyService(final JourneyService journeyService) {
        if (journeyService == null) throw new IllegalArgumentException("journeyService must be set");

        this.journeyService = journeyService;
    }*/

    @Autowired
    private TransactionalStopService stopService;


    public Long createJourney(final JourneyEntity journeyEntity) {
        if (journeyEntity == null) throw new IllegalArgumentException("journeyEntity must be set");

//        return journeyServices.get("transactionalJourneyService").createJourney(journeyEntity);
        return transactionalJourneyService.createJourney(journeyEntity);
    }

    public Long createStop(final StopEntity stopEntity) {
        if (stopEntity == null) throw new IllegalArgumentException("stopEntity must be set");
        return stopService.createStop(stopEntity);
    }

    public Optional<JourneyEntity> getJourneyById(final Long id) {
        //Assert.notNull(id, "id must be set");
        if (id == null) return Optional.empty();
        return transactionalJourneyService.getById(id);
    }

    public Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDate dateFrom, final LocalDate dateTo) {
        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
        if (dateTo == null) throw new IllegalArgumentException("date to must be set");

        // -> private JourneyService journeyService;
//        return journeyService.find(stationFrom, stationTo, dateFrom, dateTo);

        // -> private List<JourneyService> journeyServices;
        for (JourneyService service : journeyServices) {
            System.out.println(service);

            final Collection<Journey> journeys = service.find(stationFrom, stationTo, dateFrom, dateTo);

            if (!CollectionUtils.isEmpty(journeys)) return journeys;
        }

        // -> private Map<String, JourneyService> journeyServices;
        //Collection<Journey> service = journeyServices.get("inMemoryJourneyService").find(stationFrom, stationTo, dateFrom, dateTo);

//        if (service == null) {
//            return Collections.emptyList();
//        }
//
//        return service;

        return Collections.emptyList();
    }

    public static void main(String[] args) {

        final JourneyService journeyService = new InMemoryJourneyServiceImpl();
//        final TicketClient client = new TicketClient(journeyService);
        final TicketClient client = new TicketClient();

        System.out.println(client.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        // можем оберуть в какой-то Exception красивый для клиента
        System.out.println(client.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(6)));
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
    }
}

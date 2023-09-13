package clara.correa.mshistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import clara.correa.mshistory.history.RaceMessageConsumer;
import clara.correa.mshistory.history.HistoryService;
import clara.correa.mshistory.races.RaceEntity;
import clara.correa.mshistory.races.RaceRepository;

@SpringBootTest
public class MsHistoryApplicationTests {

	@InjectMocks
    private RaceMessageConsumer raceMessageConsumer;
    @InjectMocks
    private HistoryService historyService;

    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private RaceRepository raceRepository;

    @Test
    public void testReceiveRaceMessage() {
        MockitoAnnotations.openMocks(this);

        String raceJson = "{\"name\": \"Race1\", \"country\": \"Country1\", \"date\": 1631615400000, \"cars\": []}";

        raceMessageConsumer.receiveRaceMessage(raceJson);

        verifyNoInteractions(rabbitTemplate);
    }
    
    @Test
    public void testGetRaces() {
        List<RaceEntity> races = new ArrayList<>();
        races.add(new RaceEntity("Race1", "Country1", new Date(), null, LocalDateTime.now()));
        races.add(new RaceEntity("Race2", "Country2", new Date(), null, LocalDateTime.now()));
        when(raceRepository.findAll()).thenReturn(races);

        List<RaceEntity> result = historyService.getRaces();

        verify(raceRepository, times(1)).findAll();

        assertEquals(races, result);        
    }
    
    @Test
    public void testGetRaceById() {
        String raceId = "1";
        RaceEntity race = new RaceEntity("Race1", "Country1", new Date(), null, LocalDateTime.now());
        when(raceRepository.findById(raceId)).thenReturn(Optional.of(race));

        Optional<RaceEntity> result = historyService.getRaceById(raceId);

        verify(raceRepository, times(1)).findById(raceId);

        assertEquals(Optional.of(race), result);      
    }
}
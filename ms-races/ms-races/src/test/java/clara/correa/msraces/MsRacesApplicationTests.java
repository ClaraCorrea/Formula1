package clara.correa.msraces;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import clara.correa.mscars.cars.CarEntity;
import clara.correa.msraces.races.CarServiceClient;
import clara.correa.msraces.races.RaceEntity;
import clara.correa.msraces.races.RaceService;

@SpringBootTest
public class MsRacesApplicationTests {

	@Autowired
	private RaceService raceService;

	@Mock
	private CarServiceClient carServiceClient;
	@Mock
	private RabbitTemplate rabbitTemplate;

	@BeforeEach
	public void setUp() {
	    MockitoAnnotations.openMocks(this); 

	    List<CarEntity> testCars = new ArrayList<>();
	    testCars.add(new CarEntity("Car1", "Model1", "2023", 200, null));
	    testCars.add(new CarEntity("Car2", "Model2", "2023", 220, null));
	    testCars.add(new CarEntity("Car3", "Model3", "2023", 240, null));
	    when(carServiceClient.getCars()).thenReturn(testCars);
	    rabbitTemplate.convertAndSend("raceQueue", "<any String>");
	}

    @Test
    public void testPostRaceWithThreeCars() throws ParseException {
        String name = ("Race 1");
        String country =("Brasil");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date testDate = dateFormat.parse("2023-09-06T14:30:00.000");
        
        List<CarEntity> carsFromMsRaces = carServiceClient.getCars();
        
        RaceEntity race = new RaceEntity(
        		name,
        		country,
        		testDate,
        		carsFromMsRaces
        		);
        raceService.postRace(race);

        verify(carServiceClient, times(1)).getCars();
        verify(rabbitTemplate, times(1)).convertAndSend(eq("raceQueue"), anyString());
    }
}
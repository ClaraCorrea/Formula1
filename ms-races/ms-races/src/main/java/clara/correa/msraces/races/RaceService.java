package clara.correa.msraces.races;

import java.util.List;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import clara.correa.mscars.cars.CarEntity;

@Service
public class RaceService {

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private CarServiceClient carServiceClient;
    

    public RaceService(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate, RabbitTemplate rabbitTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void postRace(RaceEntity race) {
        List<CarEntity> carsFromMsRaces = carServiceClient.getCars();
        
        RaceDto races = new RaceDto(null, null, null, carsFromMsRaces);
        races.setName(race.getName());
        races.setCountry(race.getCountry());
        races.setDate(race.getDate());
        races.setCars(carsFromMsRaces);

        enviarDadosDaCorrida(races);
    }
    
    public void enviarDadosDaCorrida(RaceDto race) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String raceJson = objectMapper.writeValueAsString(race);
            rabbitTemplate.convertAndSend("raceQueue", raceJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
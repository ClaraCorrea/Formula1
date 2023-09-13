package clara.correa.mshistory.history;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import clara.correa.mshistory.cars.CarEntity;
import clara.correa.mshistory.races.RaceDto;
import clara.correa.mshistory.races.RaceEntity;
import clara.correa.mshistory.races.RaceRepository;

@Service
public class RaceMessageConsumer {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceMessageConsumer(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @RabbitListener(queues = "raceQueue")
    public void receiveRaceMessage(String raceJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(raceJson, new TypeReference<Map<String, Object>>() {});

            RaceDto msHistoryRaceDto = convertToMsHistoryRaceDto(jsonMap);
            
            String name =(msHistoryRaceDto.getName());
            String country = (msHistoryRaceDto.getCountry());
            Date date = (Date) (msHistoryRaceDto.getDate());
            List<CarEntity> cars = (msHistoryRaceDto.getCars());
            
            LocalDateTime DateCad = (LocalDateTime.now());

            RaceEntity races = new RaceEntity(
            		name,
            		country,
            		date,
            		cars,
            		DateCad
            		);

            raceRepository.save(races);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RaceDto convertToMsHistoryRaceDto(Map<String, Object> jsonMap) {
        RaceDto msHistoryRaceDto = new RaceDto();
        msHistoryRaceDto.setName((String) jsonMap.get("name"));
        msHistoryRaceDto.setCountry((String) jsonMap.get("country"));

        Long dateInMillis = (Long) jsonMap.get("date");
        msHistoryRaceDto.setDate(new Date(dateInMillis));

        msHistoryRaceDto.setCars((List<CarEntity>) jsonMap.get("cars"));

        return msHistoryRaceDto;
    }
}
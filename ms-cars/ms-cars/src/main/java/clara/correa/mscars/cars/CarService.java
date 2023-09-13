package clara.correa.mscars.cars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import clara.correa.mscars.pilots.PilotEntity;
import clara.correa.mscars.pilots.PilotRepository;
import clara.correa.mscars.pilots.PilotService;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PilotRepository pilotRepository;

    @Autowired
    private PilotService pilotService;

    public ResponseEntity<String> postCar(Map<String, Object> request) {
        Map<String, Object> pilotData = (Map<String, Object>) request.get("pilot");
        String pilotName = (String) pilotData.get("name");
        Integer pilotAge = (Integer) pilotData.get("age");

        PilotEntity pilot = new PilotEntity(        
        		pilotName,
        		pilotAge
        		);

        String brand = (String) request.get("brand");
        String model = (String) request.get("model");
        String year = (String) request.get("year");
        Integer speed = (Integer) request.get("speed");

        CarEntity car = new CarEntity(
        		brand,
        		model,
        		year,
        		speed,
        		pilot
        		);
        
        CarEntity existingCar = carRepository.findByBrandAndModelAndYearAndSpeed(
                car.getBrand(), car.getModel(), car.getYear(), car.getSpeed());
        
        PilotEntity existingPilot = pilotRepository.findByNameAndAge(
                pilot.getName(), pilot.getAge());

        return validationCarExisting(car, existingCar, pilot, existingPilot);
    }

    private ResponseEntity<String> validationCarExisting(CarEntity car, CarEntity existingCar, PilotEntity pilot, PilotEntity existingPilot) {
        if (existingCar == null && existingPilot == null) {
            pilotService.postPilot(pilot);
            carRepository.save(car);
            return ResponseEntity.ok("Carro criado e associado ao piloto com sucesso.");  
        } else {
            return ResponseEntity.ok("Carro ou Piloto semelhante já existe!");
        }
    }

    public List<CarEntity> getRandomCarsAndSortBySpeed() {
        List<CarEntity> allCars = carRepository.findAll();
        int minCars = 3;
        int maxCars = 10;
        int numberOfCarsToReturn = new Random().nextInt(maxCars - minCars + 1) + minCars;

        List<CarEntity> randomCars = getRandomSubset(allCars, numberOfCarsToReturn);
        sortCarsBySpeed(randomCars);

        return randomCars;
    }

    public void sortCarsBySpeed(List<CarEntity> cars) {
        Collections.sort(cars, (car1, car2) -> {
            int speed1 = car1.getSpeed();
            int speed2 = car2.getSpeed();

            return Integer.compare(speed2, speed1);
        });
    }

    private List<CarEntity> getRandomSubset(List<CarEntity> list, int size) {
        List<CarEntity> shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);

        int sublistSize = Math.min(size, shuffledList.size());

        return shuffledList.subList(0, sublistSize);
    }
}
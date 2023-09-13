package clara.correa.mscars.cars;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/postCar")
    public ResponseEntity<String> postCar(@RequestBody Map<String, Object> request) { 
        ResponseEntity<String> response = carService.postCar(request);
        return response;
    }
    
    @GetMapping("/getCars")
    public ResponseEntity<List<CarEntity>> getRandomCars() {
        List<CarEntity> randomCars = carService.getRandomCarsAndSortBySpeed();
        return ResponseEntity.ok(randomCars);
    }
}
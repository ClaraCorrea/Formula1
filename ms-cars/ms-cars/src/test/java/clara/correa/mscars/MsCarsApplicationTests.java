package clara.correa.mscars;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import clara.correa.mscars.cars.CarEntity;
import clara.correa.mscars.cars.CarRepository;
import clara.correa.mscars.cars.CarService;
import clara.correa.mscars.pilots.PilotRepository;

@SpringBootTest
@AutoConfigureMockMvc
class MsCarsApplicationTests {
	
    @Autowired 
    private CarService carService;  
    
    @MockBean
    private CarRepository carRepository;   
    @MockBean
    private PilotRepository pilotRepository;
	
    @Test
    public void testPostCar() {
        Map<String, Object> request = new HashMap<>();
        request.put("pilot", Map.of("name", "João", "age", 30));
        request.put("brand", "Ferrari");
        request.put("model", "213");
        request.put("year", "2003");
        request.put("speed", 200);

        ResponseEntity<String> response = carService.postCar(request);

        assertEquals("Carro criado e associado ao piloto com sucesso.", response.getBody());
    }
    
    @Test
    public void testPostCarExisting() {
        Map<String, Object> request = new HashMap<>();
        request.put("pilot", Map.of("name", "João", "age", 30));
        request.put("brand", "Ferrari");
        request.put("model", "213");
        request.put("year", "2003");
        request.put("speed", 200);
        
        when(carRepository.findByBrandAndModelAndYearAndSpeed("Ferrari", "213", "2003", 200))
        .thenReturn(new CarEntity("Ferrari", "213", "2003", 200, null));

        ResponseEntity<String> response = carService.postCar(request);

        assertEquals("Carro ou Piloto semelhante já existe!", response.getBody());
    }
}
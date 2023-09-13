package clara.correa.msraces.races;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import clara.correa.mscars.cars.CarEntity;

import java.util.List;

@FeignClient(name = "clara.correa.mscars", url = "http://localhost:8080")
public interface CarServiceClient {
    @GetMapping("cars/getCars")
    List<CarEntity> getCars();
}

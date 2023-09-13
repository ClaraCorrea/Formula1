package clara.correa.mscars.cars;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<CarEntity, String> {
	CarEntity findByBrandAndModelAndYearAndSpeed(String brand, String model, String year, Integer speed);
}

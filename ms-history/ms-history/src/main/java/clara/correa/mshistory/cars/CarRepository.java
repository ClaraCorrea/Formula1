package clara.correa.mshistory.cars;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<CarEntity, String> {
}
package clara.correa.mshistory.pilots;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PilotRepository extends MongoRepository<PilotEntity, String> {
}
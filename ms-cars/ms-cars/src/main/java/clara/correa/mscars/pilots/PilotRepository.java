package clara.correa.mscars.pilots;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PilotRepository extends MongoRepository<PilotEntity, String> {
	PilotEntity findByNameAndAge(String name, Integer age);	
}


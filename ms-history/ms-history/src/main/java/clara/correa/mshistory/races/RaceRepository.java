package clara.correa.mshistory.races;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends MongoRepository<RaceEntity, String> {
	Optional<RaceEntity> findById(String id);
}
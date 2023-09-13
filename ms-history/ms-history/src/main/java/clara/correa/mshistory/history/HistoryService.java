package clara.correa.mshistory.history;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.mshistory.races.RaceEntity;
import clara.correa.mshistory.races.RaceRepository;

@Service
public class HistoryService {
    
    @Autowired
    private RaceRepository raceRepository;
    
    public List<RaceEntity> getRaces() {
        return raceRepository.findAll();
    }


    public Optional<RaceEntity> getRaceById(String id) {
        Optional<RaceEntity> race = raceRepository.findById(id);
        return race;

    }
}

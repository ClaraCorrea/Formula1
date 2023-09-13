package clara.correa.mscars.pilots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PilotService {

    @Autowired
    private PilotRepository pilotRepository;

    public PilotEntity postPilot(PilotEntity pilot) {
        return pilotRepository.save(pilot);
    }

    public PilotEntity getPilotById(String id) {
        return pilotRepository.findById(id).orElse(null);
    }
}
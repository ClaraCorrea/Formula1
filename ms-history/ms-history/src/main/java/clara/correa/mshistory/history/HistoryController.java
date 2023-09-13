package clara.correa.mshistory.history;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import clara.correa.mshistory.races.RaceEntity;

@RestController
@RequestMapping("/history")
public class HistoryController {
    
    @Autowired
    private HistoryService historyService; 

    @GetMapping("/getAllRaces")
    public List<RaceEntity> obterCorridas() {
        List<RaceEntity> corridas = historyService.getRaces();
        return corridas;
    }
    
    @GetMapping("/getRace/{id}")
    public Object getRaceById(@PathVariable String id) {
        Optional<RaceEntity> race = historyService.getRaceById(id);
        return race;
    }
}

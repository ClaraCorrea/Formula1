package clara.correa.msraces.races;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

    @PostMapping("/postRace")
    public ResponseEntity<String> postRace(@RequestBody RaceEntity race) {
        raceService.postRace(race);
        return ResponseEntity.ok("Corrida criada com sucesso.");
    }
}
package com.bloomless.core.gameplayManagement.restController;


import com.bloomless.core.gameplayManagement.GameplayService;
import com.bloomless.core.gameplayManagement.database.PowerUpEntity;
import com.bloomless.core.gameplayManagement.mapper.GameplayMapper;
import com.bloomless.core.gameplayManagement.rest.resources.PowerUpResource;
import com.bloomless.core.gameplayManagement.rest.resources.RoundResource;
import com.bloomless.core.gameplayManagement.rest.resources.RoundResultResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
public class GameplayRestController {

    @Autowired
    private GameplayService gameplayService;

    private GameplayMapper mapper;

    @PostMapping("/bloomless/game/startStage")
    public List<PowerUpEntity> startNewStage(@RequestParam Long playerId) {
        return gameplayService.startNewStage(playerId);
    }

    // 2. PowerUp wählen und Kampf starten
    @PostMapping("/bloomless/game/choosePowerUpAndFight")
    public RoundResultResource choosePowerUpAndStartFight(
            @RequestParam Long playerId,
            @RequestParam int powerUpIndex) {
        return gameplayService.choosePowerUpAndStartFight(playerId, powerUpIndex);
    }

    //3. runden basiert kämpfen
    @PostMapping("/bloomless/game/fightRound")
    public List<RoundResultResource> fightRound(@RequestParam Long roundId) {
        return gameplayService.fightRound(roundId);
    }

    @PostMapping("/bloomless/admin/initPowerUps")
    public List<PowerUpEntity> initPowerUps() {
        return gameplayService.initPowerUps();
    }

    /*@PostMapping("bloomless/game/startRound")
    public RoundResource startNewRound(
            @RequestParam Long playerId,
            @RequestBody List<Long> powerUpIds) {
        return gameplayService.startNewRound(playerId, powerUpIds);
    }



    @PostMapping("bloomless/game/playRound")
    public RoundResource playRound(@RequestParam Long roundId) {
        return gameplayService.playRound(roundId);
    }

    @GetMapping("bloomless/game/powerups")
    public List<PowerUpResource> getRandomPowerUps() {
        return gameplayService.getRandomPowerUps();
    }*/
}

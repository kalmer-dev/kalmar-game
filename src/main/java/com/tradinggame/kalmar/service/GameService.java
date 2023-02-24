package com.tradinggame.kalmar.service;

import com.tradinggame.kalmar.model.Player;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public Player getPlayerInput(Player player) {
        player.move();
        return player;
    }

}
package com.tradinggame.kalmar.game.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Game {

    private Thread thread;
    private String identifier = RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom());
    private List<Player> players = new ArrayList<>();
    private Map map = new Map();

    public void putPlayer(Player player) {
        players.add(player);
    }

    public void updatePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(player.getName())) {
                players.remove(i);
                players.add(player);
            }
        }
    }
}


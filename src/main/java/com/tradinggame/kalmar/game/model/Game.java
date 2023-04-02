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
    private List<TradingPost> posts = new ArrayList<>();

    {
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),20, 50, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),200, 500, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),200, 2400, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),2500, 500, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),2000, 3050, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),2000, 50, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),2220, 500, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),3020, 503, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),2320, 1650, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),1020, 1050, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),2000, 650, ((int) (Math.random() * 10) + 10)));
    }

    private Thread thread;
    private String identifier = RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom());
    private List<Player> players = new ArrayList<>();
    private Map gameMap = new Map();

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


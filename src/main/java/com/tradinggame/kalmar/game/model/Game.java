package com.tradinggame.kalmar.game.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Game {
    private boolean end;
    private List<TradingPost> posts = new ArrayList<>();

    {
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),378, 1555, ((int) (Math.random() * 10) + 10)));  //200- 3000-IG KORDINÁTÁS
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),883, 2342, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),925, 432, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),1085, 777, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),1111, 1352, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),444, 935, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),2220, 500, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),1234, 2134, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),2320, 1650, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),1020, 1050, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom()),678, 914, ((int) (Math.random() * 10) + 10)));
    }

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


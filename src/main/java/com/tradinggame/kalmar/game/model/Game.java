package com.tradinggame.kalmar.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
public class Game {
   // private final SimpMessagingTemplate messagingTemplate;
    private Thread thread;
    private String identifier = RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom());
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;

//    public Game(){
//        messagingTemplate = new SimpMessagingTemplate("");
//    }
    private List<Player> players = new ArrayList<>();
    private Map map = new Map();

    public void putPlayer(Player player){
        players.add(player);
    }

//    @Override
//    public void run() {
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(() -> {
//            messagingTemplate.convertAndSend("/topic/game", this);
//        }, 0, 5, TimeUnit.SECONDS);
//    }
}

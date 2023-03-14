package com.tradinggame.kalmar.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class Game {
    private String identifier;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;
    private Map map;
}

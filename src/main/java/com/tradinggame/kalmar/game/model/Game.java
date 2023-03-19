package com.tradinggame.kalmar.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;

@Data
public class Game {
    private String identifier = RandomStringUtils.random(5, 0, 0, true, true, null, new SecureRandom());
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;
    private Map map = new Map();
}

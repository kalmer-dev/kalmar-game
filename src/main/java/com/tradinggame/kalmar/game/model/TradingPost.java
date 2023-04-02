package com.tradinggame.kalmar.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

@Data
@AllArgsConstructor
public class TradingPost {
    private String identifier;
    private int coordinateX;
    private int coordinateY;
    private int treePrice;
}

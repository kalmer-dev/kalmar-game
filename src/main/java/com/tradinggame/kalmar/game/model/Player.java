package com.tradinggame.kalmar.game.model;

import lombok.Data;

@Data
public class Player {
    private String name;
    private int coordinateX;
    private int coordinateY;

    public Player(String name) {
        this.name = name;
        coordinateX = 0;
        coordinateY = 0;
    }
}

package com.tradinggame.kalmar.game.model;

import lombok.Data;

@Data
public class Player {
    private String name;
    private int coordinateX;
    private int coordinateY;
    private int viewX;
    private int viewY;

    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        coordinateX = 0;
        coordinateY = 0;
        viewX = 0;
        viewY = 0;
        inventory=new Inventory();

    }

}

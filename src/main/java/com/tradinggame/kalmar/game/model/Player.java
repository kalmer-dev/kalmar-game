package com.tradinggame.kalmar.game.model;

import lombok.Data;

@Data
public class Player {
    private boolean onShop = false;
    private String fightWith = "";
    private String name;
    private int coordinateX;
    private int coordinateY;
    private int viewX;
    private int viewY;

    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        coordinateX = (int)(Math.random()*3000);
        coordinateY = (int)(Math.random()*3000);
        viewX = 0;
        viewY = 0;
        inventory=new Inventory();

    }

}

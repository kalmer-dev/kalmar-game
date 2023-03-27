package com.tradinggame.kalmar.game.model;

import lombok.Data;

@Data
public class Player {
    private Long id;
    private String name;
    private Tile[][] sawFromMap;
    private int coordinateX;
    private int coordinateY;
    private Inventory playerInventory;

    public Player(Long id, String name) {
        this.name = name;
        this.playerInventory = new Inventory(this.name, 100, 100);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
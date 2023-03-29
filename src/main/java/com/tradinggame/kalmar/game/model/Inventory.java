package com.tradinggame.kalmar.game.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Inventory {
    private int tree;
    private int money;

    public Inventory() {
        this.tree = 0;
        this.money = 100;
    }
}

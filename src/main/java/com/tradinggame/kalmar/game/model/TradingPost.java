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

    public void trade(Inventory inventory, int amount) {
        int money = inventory.getMoney();
        int cost = amount * treePrice;
        int trees = inventory.getTree();
        // check enough money at buy and trees at sell
        if (money >= cost && !(amount < 0 && amount * -1 > trees)) {
            inventory.setMoney(money - cost);
            inventory.setTree(trees + amount);
        }

    }
}

package com.tradinggame.kalmar.game.model;

import lombok.Getter;

public class Inventory {

    @Getter
    private final String playerName;
    private int money = 0;
    private int wood = 0;

    public Inventory(String playerName, int money, int wood) {
        this.playerName = playerName;
        this.money = money;
        this.wood = wood;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }
}
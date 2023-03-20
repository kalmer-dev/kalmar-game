package com.tradinggame.kalmar.game.model;


import lombok.Getter;

public class Tile {
    @Getter
    private String image;
    @Getter
    private Type type;

    public Tile(int identifier) {
        switch (identifier) {
            case 0 -> {
                this.type = Type.GRASS;
                this.image = "/OneColorBackgrounds/green.png";
            }
            case 1 -> {
                this.type = Type.TRADING_POST;
                this.image = "/OneColorBackgrounds/brown.png";
            }
        }

    }
}

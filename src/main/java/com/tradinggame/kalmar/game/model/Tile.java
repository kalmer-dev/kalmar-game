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
                this.image = "/Backgrounds/basic.grass3.png";
            }
            case 1 -> {
                this.type = Type.GRASSWITHFLOWERS;
                this.image = "/Backgrounds/basic.grass_withflowers.png";
            }
        }

    }
}

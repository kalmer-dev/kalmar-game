package com.tradinggame.kalmar.game.model;

import java.awt.*;

public class Tile {
    private Image image;
    private Type type;

    public Tile(int identifier){
        switch (identifier){
            case 1 -> { this.type = Type.GRASS;}
        }
    }
}

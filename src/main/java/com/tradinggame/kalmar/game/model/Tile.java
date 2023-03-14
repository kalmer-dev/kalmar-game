package com.tradinggame.kalmar.game.model;


import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    @Getter
    private BufferedImage image;
    @Getter
    private Type type;

    public Tile(int identifier) {
        try {
            switch (identifier) {
                case 1 -> {
                    this.type = Type.GRASS;
                    File file = new File("src/main/resources/images/map/OneColorBackgrounds/green.png");
                    this.image = ImageIO.read(file);

                }
                case 2 ->{
                    this.type = Type.TRADING_POST;
                    File file = new File("src/main/resources/images/map/OneColorBackgrounds/brown.png");
                    this.image = ImageIO.read(file);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.tradinggame.kalmar.model;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Super class for all entity which is able to interact
 */
@Data
public abstract class AbstractEntity {


    private BufferedImage image;
    private int HEIGHT;
    private int WIDTH;
    private double xCoordinate;
    private double yCoordinate;
    private boolean exist = true;

    public abstract void update();

    public void draw(Graphics2D g2){
        //need to implement
    };

    /**
     * Calculate the distance from another entity
     * @param abstractEntity
     * @return the distance as a double
     */
    public double distanceOfCoordinates(AbstractEntity abstractEntity) {
        double xDistance = Math.abs(this.xCoordinate - abstractEntity.getXCoordinate());
        double yDistance = Math.abs(this.yCoordinate - abstractEntity.getYCoordinate());
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }
}

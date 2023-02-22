package com.tradinggame.kalmar.model;

import java.awt.image.BufferedImage;

/**
 * All not living but able to move entity inherit from this class directly.
 */
public abstract class MovingEntity extends AbstractEntity {
    /**
     * During animation, we change this images, on image field(from super class)
     */
    private BufferedImage ups, up1, up2, downs, down1, down2, lefts, left1, left2, rights, right1, right2;
    private Direction direction = Direction.DOWN;
    private int speed;

    /**
     * Define the move
     */
    public abstract void move();
}

enum Direction{
    UP, DOWN, LEFT, RIGHT
}
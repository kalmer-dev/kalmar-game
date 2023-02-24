package com.tradinggame.kalmar.model;

import com.tradinggame.kalmar.service.KeyHandler;
import lombok.Data;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

@Data
public class Player extends MovingEntity{

    KeyHandler keyHandler;

    public Player(KeyHandler keyHandler){
        this.keyHandler = keyHandler;
        this.setSpeed(4);
        this.setImages();
        this.updateImage();
    }
    @Override
    public void move() {
        if (keyHandler.isUpPressed()) {
            this.setDirection(Direction.UP);
            this.setYCoordinate(getYCoordinate() - this.getSpeed());
        }
        if (keyHandler.isDownPressed()) {
            this.setDirection(Direction.DOWN);
            this.setYCoordinate(getYCoordinate() + this.getSpeed());
        }
        if (keyHandler.isLeftPressed()) {
            this.setDirection(Direction.LEFT);
            this.setYCoordinate(getXCoordinate() - this.getSpeed());
        }
        if (keyHandler.isRightPressed()) {
            this.setDirection(Direction.RIGHT);
            this.setYCoordinate(getXCoordinate() + this.getSpeed());
        }
    }

    @Override
    public void update() {

    }


    private void updateImage () {
        switch (this.getDirection()){
            case UP -> {
                this.setImage(this.getUps());
            }
            case DOWN -> {
                this.setImage(this.getDowns());
            }
            case LEFT -> {
                this.setImage(this.getLefts());
            }
            case RIGHT -> {
                this.setImage(this.getRights());
            }
        }
    }
    private void setImages() {
        try {
            setUp1(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyEmptyand/boy_back_move1.png"))));
            setUp2(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_back_move2.png"))));
            setUps(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_back_still.png"))));
            setDown1(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_front_move1.png"))));
            setDown2(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_front_move2.png"))));
            setDowns(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_front_still.png"))));
            setLeft1(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_left_move1.png"))));
            setLeft2(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_left_move2.png"))));
            setLefts(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_left_still.png"))));
            setRight1(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_right_move1.png"))));
            setRight2(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_right_move2.png"))));
            setRights(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/testimages/Charachters/boy/boyWithSword/boy_withsword_right_still.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

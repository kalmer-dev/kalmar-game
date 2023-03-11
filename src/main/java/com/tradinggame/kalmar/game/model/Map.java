package com.tradinggame.kalmar.game.model;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Map {

    @Value("${map}")
    private int[][] mapAsMatrix;
    private Tile[][] tiles;


}

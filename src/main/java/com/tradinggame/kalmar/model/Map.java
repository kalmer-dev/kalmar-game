package com.tradinggame.kalmar.model;

import org.springframework.beans.factory.annotation.Value;

public class Map {
    final int mapSizeX;
    final int mapSizeY;
    final int pixel;

    final int[][] mapElements;

    Map(@Value("${map.x}") int mapSizeX,
        @Value("${map.y}") int mapSizeY,
        @Value("${map.pixels}") int pixel
        ){
        this.mapSizeX = mapSizeX;
        this.mapSizeY = mapSizeY;
        this.pixel = pixel;
        this.mapElements = new int[this.mapSizeX][this.mapSizeY];
    }


}

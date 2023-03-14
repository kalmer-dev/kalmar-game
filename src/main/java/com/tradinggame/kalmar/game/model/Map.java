package com.tradinggame.kalmar.game.model;


import lombok.Getter;

public class Map {

    private int[][] mapAsMatrix = new int[][]{{0, 0, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
    @Getter
    private Tile[][] tiles;

    public Map() {
        tiles = new Tile[mapAsMatrix.length][mapAsMatrix[0].length];
        for (int rows = 0; rows < mapAsMatrix.length; rows++) {
            for (int column = 0; column < mapAsMatrix[rows].length; column++) {
                tiles[rows][column] = new Tile(mapAsMatrix[rows][column]);

            }
        }
    }
}

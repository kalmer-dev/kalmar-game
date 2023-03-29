package com.tradinggame.kalmar.game.model;

import com.google.gson.Gson;
import lombok.Getter;

import java.io.File;

public class Map {

<<<<<<< HEAD
    private final int[][] mapAsMatrix =
    private final String[][] mapAsMatrix;

>>>>>>> fb9e952 (Megjelenítés Version 2. (CSS és HTML hibajavítás))
    @Getter
    private Tile[][] tiles;

    public Map() {
        File file=new File("src/main/resources/static/Map/1.json");
        String string=file.toString();
        Gson gson = new Gson();
        mapAsMatrix = gson.fromJson(string, String[][].class);
        tiles = new Tile[mapAsMatrix.length][mapAsMatrix[0].length];
        for (int rows = 0; rows < mapAsMatrix.length; rows++) {
            for (int column = 0; column < mapAsMatrix[rows].length; column++) {
                tiles[rows][column] = new Tile(Integer.parseInt(mapAsMatrix[rows][column]));

            }
        }
    }
}

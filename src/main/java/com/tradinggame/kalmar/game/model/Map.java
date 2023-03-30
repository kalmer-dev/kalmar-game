package com.tradinggame.kalmar.game.model;

import com.google.gson.Gson;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Map {


    //    private final int[][] mapAsMatrix =
    private final String[][] mapAsMatrix;

    private List<TradingPost> posts = new ArrayList<>();
    @Getter
    private Tile[][] tiles;

    public Map() {
        posts.add(new TradingPost(20, 50, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(200, 500, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(200, 2400, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(2500, 500, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(2000, 3050, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(2000, 50, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(2220, 500, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(3020, 503, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(2320, 1650, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(1020, 1050, ((int) (Math.random() * 10) + 10)));
        posts.add(new TradingPost(2000, 650, ((int) (Math.random() * 10) + 10)));

        try {


            File file = new File("src/main/resources/static/Map/1.json");
            Gson gson = new Gson();
            Reader reader = new FileReader(file);
            mapAsMatrix = gson.fromJson(reader, String[][].class);
            tiles = new Tile[mapAsMatrix.length][mapAsMatrix[0].length];
            for (int rows = 0; rows < mapAsMatrix.length; rows++) {
                for (int column = 0; column < mapAsMatrix[rows].length; column++) {
                    tiles[rows][column] = new Tile(Integer.parseInt(mapAsMatrix[rows][column]));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

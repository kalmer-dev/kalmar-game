package com.tradinggame.kalmar.game.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class MatrixReader {

    public static int[][] matrix;

    public static int[][] matrixMap(String path) {
        try {
            // Először megnyitjuk a JSON fájlt
            File file = new File(path);
            FileReader reader = new FileReader(file);

            // Beolvassuk az adatokat a JSONObject-be
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reader);

            // Kinyerjük a mátrixot a JSON fájlból
            JSONArray matrixJsonArray = (JSONArray) jsonObject.get("matrix");

            // Létrehozzuk a Java tömböt a mátrix elemeivel
            int numRows = matrixJsonArray.size();
            int numCols = ((JSONArray) matrixJsonArray.get(0)).size();
            int[][] matrix = new int[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                JSONArray rowJsonArray = (JSONArray) matrixJsonArray.get(i);
                for (int j = 0; j < numCols; j++) {
                    matrix[i][j] = Integer.parseInt((String) rowJsonArray.get(j));
                }
            }
            return matrix;
/*
            // Kiírjuk a mátrixot a konzolra
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

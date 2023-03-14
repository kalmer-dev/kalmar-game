package com.tradinggame.kalmar.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private Tile[][] sawFromMap;
    private int coordinateX;
    private int coordinateY;
}

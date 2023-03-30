package com.tradinggame.kalmar.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradingPost {
    private int coordinateX;
    private int coordinateY;
    private int treePrice;
}

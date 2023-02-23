package com.tradinggame.kalmar.minigames;

import com.tradinggame.kalmar.model.Player;

public class RockPaperScissors implements Minigame {

    public static Player RPS_Game(Player playerOne, Player playerTwo, String tip1, String tip2) {
        if (tip1.equals(Move.SCISSORS.getValue()) && tip2.equals(Move.PAPER.getValue())) {
            return playerOne;
        }
        if (tip1.equals(Move.ROCK.getValue()) && tip2.equals(Move.SCISSORS.getValue())) {
            return playerOne;
        }
        if (tip1.equals(Move.PAPER.getValue()) && tip2.equals(Move.ROCK.getValue())) {
            return playerOne;
        }
        if (tip1.equals(tip2)) {
            return null;
        }
        return playerTwo;
    }
}

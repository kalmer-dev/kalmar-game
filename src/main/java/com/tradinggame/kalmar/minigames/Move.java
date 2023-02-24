package com.tradinggame.kalmar.minigames;

public enum Move {
    ROCK("rock"),
    PAPER("paper"),
    SCISSORS("scissors");

    private String value;

    Move(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Move fromString(String text) {
        for (Move r : Move.values()) {
            if (r.value.equalsIgnoreCase(text)) {
                return r;
            }
        }
        return null;
    }
}
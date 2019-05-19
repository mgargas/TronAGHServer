package com.software.basement.tron.server.game;

public enum Direction {
    N, S, W, E;


    public Direction turnRight(Direction direction) {
        switch (direction) {
            case N:
                return Direction.E;
            case S:
                return Direction.W;
            case W:
                return Direction.N;
            case E:
                return Direction.S;
        }
        return null;
    }

    public Direction turnLeft(Direction direction) {
        switch (direction) {
            case N:
                return Direction.W;
            case S:
                return Direction.E;
            case W:
                return Direction.S;
            case E:
                return Direction.N;
        }
        return null;
    }
}

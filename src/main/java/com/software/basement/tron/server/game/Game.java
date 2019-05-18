package com.software.basement.tron.server.game;

import java.util.List;

public class Game {

    private int height;
    private int width;
    private List<Player> players;
    private int[][] board;
    private int playersLimit;

    public Game(int height, int width, int playersLimit) {
        this.height = height;
        this.width = width;
        this.playersLimit = playersLimit;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
}

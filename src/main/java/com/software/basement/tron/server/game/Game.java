package com.software.basement.tron.server.game;

import java.awt.*;
import java.util.List;

public class Game extends Thread{

    private int height;
    private int width;
    private List<Player> players;
    private int[][] board;
    private int playersLimit;
    private int numberOfLivePlayers;

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

    public void initGame(){

        numberOfLivePlayers = players.size();

        for(Player player : players){
            board[getHeight() - player.getY()][player.getX()] = 1;
        }

        switch (players.size()){
            case 1: players.get(0).setDirection(Direction.N);
                    players.get(0).setPosition(new Point(50,20));
                    break;
        }
        System.out.println(board[20][20]);
        System.out.println(board[40][40]);
    }

    private void iteration(){
        for(Player player : players){
            player.move();
            if(board[getHeight() - player.getY()][player.getX()] == 1){
                //TODO send info about death
                numberOfLivePlayers--;
                if(numberOfLivePlayers == 1) endGame();

            } else {
                board[getHeight() - player.getY()][player.getX()] = 1;
            }
        }

    }

    private void endGame(){
        //TODO send info about end game and result in the future
        interrupt();
    }

    @Override
    public void run() {
        while(true){
            iteration();
        }
    }
}

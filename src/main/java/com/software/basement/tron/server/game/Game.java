package com.software.basement.tron.server.game;

import com.software.basement.tron.server.websockets.controllers.GameState;

import com.software.basement.tron.server.websockets.controllers.MoveController;
import lombok.Data;

import java.awt.*;
import java.util.HashMap;

@Data
public class Game extends Thread {

    private int height;
    private int width;
    private HashMap<Integer, Player> players;
    private int[][] board;
    private int numberOfLivePlayers;
    private int roomID;

    private MoveController moveController;

    public Game(int height, int width, MoveController moveController, int roomID) {
        this.height = height;
        this.width = width;
        this.moveController = moveController;
        this.roomID = roomID;
    }

    public void initGame() {
        numberOfLivePlayers = players.size();

        for (Player player : players.values()) {
            board[getHeight() - player.getY()][player.getX()] = 1;
        }

        switch (players.size()) {
            case 1:
                players.get(0).setDirection(Direction.N);
                players.get(0).setPosition(new Point(50, 20));
                break;
        }
        System.out.println(board[20][20]);
        System.out.println(board[40][40]);
    }

    private void iteration() {
        for (Player player : players.values()) {
            player.move();
            if (board[getHeight() - player.getY()][player.getX()] == 1) {
                //TODO send info about death
                numberOfLivePlayers--;
                if (numberOfLivePlayers == 1) endGame();

            } else {
                board[getHeight() - player.getY()][player.getX()] = 1;
            }
        }
        this.moveController.sendState(new GameState(this.players), String.valueOf(roomID));

    }

    private void endGame() {
        //TODO send info about end game and result in the future
        interrupt();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iteration();
        }
    }

    public void turnPlayer(int id, int turn){
        Player player = players.get(id);
        if(turn == -1)
            player.setDirection(player.getDirection().turnLeft(player.getDirection()));
        else
            player.setDirection(player.getDirection().turnRight(player.getDirection()));
    }
}

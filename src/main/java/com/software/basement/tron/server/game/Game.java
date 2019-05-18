package com.software.basement.tron.server.game;

import com.software.basement.tron.server.websockets.controllers.GameState;

import com.software.basement.tron.server.websockets.controllers.MoveController;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        this.players = new HashMap<>();
        this.board = new int[height][width];
    }

    public void initGame() {
        numberOfLivePlayers = players.size();

        List<Player> playersList = new ArrayList<>(players.values());

        switch (players.size()) {
            case 1:
                playersList.get(0).setDirection(Direction.N);
                playersList.get(0).setPosition(new Point(25, 10));
                break;
            case 2:
                playersList.get(0).setDirection(Direction.N);
                playersList.get(0).setPosition(new Point(10, 0));
                playersList.get(1).setDirection(Direction.N);
                playersList.get(1).setPosition(new Point(40, 0));
        }

        System.out.println(players.values());
        for (Player player : players.values()) {
            board[getHeight() - player.getY()][player.getX()] = 1;
        }


        System.out.println(board[20][20]);
        System.out.println(board[40][40]);
    }

    private void iteration() {
        for (Player player : players.values()) {
            if(!player.isDead())
            player.moveIteration();
            try {
                if (board[getHeight() - player.getY()][player.getX()] == 1) {
                    //TODO change it maybe
                    player.setDead(true);
                    player.setPosition(new Point(-1, -1));
                    numberOfLivePlayers--;
                    if (numberOfLivePlayers == 1) endGame();

                } else {
                    board[getHeight() - player.getY()][player.getX()] = 1;
                }
            } catch (IndexOutOfBoundsException ex){
                player.setDead(true);
                player.setPosition(new Point(-1, -1));
            }
        }
        if(moveController == null) System.out.println(">GAME>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>NULLLL XDDDD");
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

    public void addPlayer(int id){
        players.put(id,new Player(id, "name"));
    }
}

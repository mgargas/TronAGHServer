package com.software.basement.tron.server.game;

import com.software.basement.tron.server.BeanUtil;
import com.software.basement.tron.server.websockets.controllers.GameState;
import com.software.basement.tron.server.websockets.controllers.MoveController;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Game extends Thread {

    private MoveController moveController;
    private int height;
    private int width;
    private ConcurrentHashMap<Integer, Player> players;
    private int[][] board;
    private int numberOfLivePlayers;
    private int roomID;


    public Game(int height, int width, int roomID) {
        this.height = height;
        this.width = width;
        this.roomID = roomID;
        this.players = new ConcurrentHashMap<>();
        this.board = new int[height][width];
        this.moveController = BeanUtil.getBean(MoveController.class);
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
                playersList.get(0).setPosition(new Point(10, 10));
                playersList.get(1).setDirection(Direction.N);
                playersList.get(1).setPosition(new Point(40, 10));
        }

        for (Player player : players.values()) {
            board[getHeight() - player.getY()][player.getX()] = 1;
        }

    }



    private void iteration() throws InterruptedException {
        for (Player player : players.values()) {
            if (!player.isDead())
                player.moveIteration();
            try {
                if (player.isHasBeenRecentlyMoved() && board[getHeight() - player.getY()][player.getX()] == 1) {
                    System.out.println("Player with id = " + player.getId() + " died :(");
                    //TODO change it maybe
                    player.setDead(true);
                    player.setPosition(new Point(-1, -1));
                    numberOfLivePlayers--;
                    checkIfGameOver();

                } else {
                    board[getHeight() - player.getY()][player.getX()] = 1;
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Player with id = " + player.getId() + " died :(");
                player.setDead(true);
                player.setPosition(new Point(-1, -1));
                checkIfGameOver();
            }
        }
        this.moveController.sendState(new GameState(this.players, false, -1), String.valueOf(roomID));

    }

    private boolean endGame(int id) throws InterruptedException {
        this.moveController.sendState(new GameState(this.players, true, id), String.valueOf(roomID));
        throw new InterruptedException();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
                iteration();
            } catch (InterruptedException e) {
                return;
            }

        }
    }

    public void turnPlayer(int id, int turn) {
        Player player = players.get(id);
        if (turn == -1)
            player.setDirection(player.getDirection().turnLeft(player.getDirection()));
        else
            player.setDirection(player.getDirection().turnRight(player.getDirection()));
    }

    public void addPlayer(int id) {
        players.put(id, new Player(id, "name"));
    }

    public void addBot(){
        Bot bot = new Bot(this);
        players.put(bot.getId(), bot);
    }

    public void checkIfGameOver() throws InterruptedException{
        if (numberOfLivePlayers <= 1){
            for(Player lastPlayer : players.values()){
                if(!lastPlayer.getPosition().equals(new Point(-1, -1)))endGame(lastPlayer.getId());
            }
        }
    }
}

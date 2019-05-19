package com.software.basement.tron.server.game;

import com.software.basement.tron.server.BeanUtil;
import com.software.basement.tron.server.websockets.controllers.GameState;
import com.software.basement.tron.server.websockets.controllers.MoveController;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.software.basement.tron.server.game.BonusType.*;

@Data
public class Game extends Thread {

    private static final int TIME_GAP_BETWEEN_BONUSES = 20;

    private MoveController moveController;
    private int height;
    private int width;
    private int size;
    private ConcurrentHashMap<Integer, Player> players;
    private CopyOnWriteArrayList<Bonus> bonuses;
    private int[][] board;
    private int numberOfLivePlayers;
    private int roomID;
    private int iterationsToBonusSpawn;


    public Game(int height, int width, int roomID) {
        this.height = height;
        this.width = width;
        this.size = height * width;
        this.roomID = roomID;
        this.iterationsToBonusSpawn = TIME_GAP_BETWEEN_BONUSES;
        this.players = new ConcurrentHashMap<>();
        this.bonuses = new CopyOnWriteArrayList<>();
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
                playersList.get(0).setPosition(new Point(10, 0));
                playersList.get(1).setDirection(Direction.N);
                playersList.get(1).setPosition(new Point(40, 0));
        }

        for (Player player : players.values()) {
            board[getHeight() - player.getY()][player.getX()] = 1;
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
        players.put(id, new Player(id, "type"));
    }

    public void addBot() {
        Bot bot = new Bot(this);
        players.put(bot.getId(), bot);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iteration();
            updateIterationsToBonusSpawn();
        }
    }

    private void updateIterationsToBonusSpawn() {
        if (iterationsToBonusSpawn == 0) {
            iterationsToBonusSpawn = TIME_GAP_BETWEEN_BONUSES;
        } else {
            iterationsToBonusSpawn--;
        }
    }

    private void iteration() {
        for (Player player : players.values()) {
            if (!player.isDead())
                player.moveIteration();
            try {
                if (player.isHasBeenRecentlyMoved()) {
                    int playerCurrentPosition = board[getHeight() - player.getY()][player.getX()];
                    if (playerCurrentPosition == 1) {
                        System.out.println("Player with id = " + player.getId() + " died :(");
                        if (!player.isImmortal()) {
                            player.setDead(true);
                            player.setPosition(new Point(-1, -1));
                            numberOfLivePlayers--;
                            if (numberOfLivePlayers == 1) endGame();
                        }
                    } else {
                        if (playerCurrentPosition == IMMORTALITY.getId()) {
                            player.setImmortal(true);
                        } else if (playerCurrentPosition == SPEED_UP.getId()) {

                        }
                        Bonus bonus = bonuses.stream()
                                .filter(b -> b.getPosition().y == (getHeight() - player.getY()) && b.getPosition().x == player.getX())
                                .findFirst()
                                .get();
                        bonuses.remove(bonus);
                        board[getHeight() - player.getY()][player.getX()] = 1;
                    }
                } else {
                    board[getHeight() - player.getY()][player.getX()] = 1;
                }
            } catch (IndexOutOfBoundsException ex) {
                player.setDead(true);
                player.setPosition(new Point(-1, -1));
            }
        }
        if (iterationsToBonusSpawn == 0) {
            spawnBonus();
        }
        this.moveController.sendState(new GameState(this.players, this.bonuses), String.valueOf(roomID));
    }

    private void spawnBonus() {
        BonusType type = getRandomBonusType();
        Point position = determineBonusPosition();
        if (position != null) {
            Bonus newBonus = Bonus.builder()
                    .type(type)
                    .position(position)
                    .build();
            bonuses.add(newBonus);
            board[position.x][position.y] = type.getId();
        }
    }

    private BonusType getRandomBonusType() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }

    private Point determineBonusPosition() {
        Point newPosition = getRandomPointOnBoard();
        int pointsChecked = 0;
        while (board[newPosition.y][newPosition.x] != 0 && pointsChecked < size) {
            pointsChecked++;
            newPosition = getRandomPointOnBoard();
        }
        return null;
    }

    private Point getRandomPointOnBoard() {
        Random rand = new Random();
        return new Point(rand.nextInt(height), rand.nextInt(width));
    }

    private void endGame() {
        //TODO send info about end game and result in the future
        interrupt();
    }
}

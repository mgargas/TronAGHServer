package com.software.basement.tron.server.game;

import com.software.basement.tron.server.BeanUtil;
import com.software.basement.tron.server.account.Account;
import com.software.basement.tron.server.repository.AccountRepository;
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
    private AccountRepository accountsRepository;
    private Lobby lobby;


    public Game(int height, int width, int roomID) {
        this.height = height;
        this.width = width;
        this.roomID = roomID;
        this.players = new ConcurrentHashMap<>();
        this.board = new int[height][width];
        this.moveController = BeanUtil.getBean(MoveController.class);
        this.accountsRepository = BeanUtil.getBean(AccountRepository.class);
        this.lobby = BeanUtil.getBean(Lobby.class);
    }

    public void initGame() {
        numberOfLivePlayers = players.size();
        System.out.println("Live players ============================ " + numberOfLivePlayers);

        List<Player> playersList = new ArrayList<>(players.values());

        //Map map = new Map(height, width);

        //map.fillBoard(this.board);

        switch (players.size()) {
            case 1:
                playersList.get(0).setDirection(Direction.N);
                playersList.get(0).setPosition(new Point(25, 10));
                break;
            case 2:
                playersList.get(0).setDirection(Direction.S);
                playersList.get(0).setPosition(new Point(25, 95));
                playersList.get(1).setDirection(Direction.N);
                playersList.get(1).setPosition(new Point(25, 5));
                break;
            case 3:
                playersList.get(0).setDirection(Direction.S);
                playersList.get(0).setPosition(new Point(15, 95));
                playersList.get(1).setDirection(Direction.N);
                playersList.get(1).setPosition(new Point(15, 5));
                playersList.get(2).setDirection(Direction.W);
                playersList.get(2).setPosition(new Point(45, 50));
                break;
        }

        for (Player player : players.values()) {
            board[getHeight() - player.getY()][player.getX()] = 1;
        }

    }



    private void iteration() throws InterruptedException {
        for (Player player : players.values()) {
            if (!player.isDead())
                player.moveIteration();
            else
                continue;
            try {
                if (player.isHasBeenRecentlyMoved() && board[getHeight() - player.getY()][player.getX()] == 1) {
                    //TODO change it maybe
                    System.out.println("Someone died :O");
                    player.setDead(true);
                    player.setPosition(new Point(-1, -1));
                    numberOfLivePlayers--;
                    System.out.println("Live players -- in if");
                    checkIfGameOver();
                    System.out.println("DEAD POSITION " +player.getPosition());

                } else {
                    board[getHeight() - player.getY()][player.getX()] = 1;
                }
            } catch (IndexOutOfBoundsException ex) {
                player.setDead(true);
                numberOfLivePlayers--;
                System.out.println("Live players --");
                player.setPosition(new Point(-1, -1));
                checkIfGameOver();
                System.out.println("POSITION " +player.getPosition());
            }
        }
        this.moveController.sendState(new GameState(this.players, false, -1), String.valueOf(roomID));

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(70);
                iteration();
            } catch (InterruptedException e) {
                this.lobby.deleteRoom(this.roomID);
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

    public void addBot(int id){
        Bot bot = new Bot(this, id);
        players.put(bot.getId(), bot);
    }

    public void checkIfGameOver() throws InterruptedException{
        System.out.println("Number of live player " + numberOfLivePlayers);
        if (numberOfLivePlayers <= 1){
            for(Player lastPlayer : players.values()){
                if(!lastPlayer.isDead()) endGame(lastPlayer.getId());
            }
            endGame(-1);
        }
    }

    public void endGame(int id) throws InterruptedException {
        incrementWins(id);
        this.moveController.sendState(new GameState(this.players, true, id), String.valueOf(roomID));
        throw new InterruptedException();
    }

    private void incrementWins(Integer playerId) {
        Account account = accountsRepository.findByPlayerId(playerId);
        if (account != null) {
            account.setWins(account.getWins() + 1);
            accountsRepository.save(account);
        }
    }
}

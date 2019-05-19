package com.software.basement.tron.server.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.software.basement.tron.server.websockets.controllers.MoveController;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Room {

    @JsonIgnore
    private Game game;

    private Integer id;
    private String name;
    private Integer maxPlayers = 6;
    private Integer minPlayers = 2;
    private List<Integer> playersIds = new ArrayList<>();
    private boolean readyToStart;
    private Integer creatorId;

    public void createGame() {
        game = new Game(50, 50, id);
    }

    public void setReadyToStart(boolean readyToStart){
        System.out.println("Set ready to start: " + readyToStart + "room id = " + id);
        this.readyToStart = readyToStart;
        if(this.readyToStart){
            roomStart();
        }
    }


    public void roomStart() {
        if(this.readyToStart) {
            try {
                Thread.sleep(5000);
                createGame();
                for (Integer playerId : playersIds)
                    game.addPlayer(playerId);
                game.initGame();
                game.start();
                System.out.println("Game started");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

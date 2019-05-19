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
    private MoveController moveController;
    @JsonIgnore
    private Game game;

    private Integer id;
    private Integer maxPlayers = 6;
    private Integer minPlayers = 2;
    private List<Integer> playersIds = new ArrayList<>();
    private boolean readyToStart;
    private Integer creatorId;

    public void createGame() {
        game = new Game(50, 50, moveController, 0);
    }

    public void setReadyToStart(boolean readyToStart){
        this.readyToStart = readyToStart;
        if(this.readyToStart == true){
            roomStart();
        }
    }


    public void setMoveController(MoveController moveController) {
        this.moveController = moveController;
    }

    public void roomStart() {
        try {
            if (playersIds.size() == 1) {
                Thread.sleep(5000);
                createGame();
                for(Integer playerId : playersIds)
                    game.addPlayer(playerId);
                game.initGame();
                game.start();
                System.out.println("game started");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package com.software.basement.tron.server.game;

import com.software.basement.tron.server.websockets.controllers.MoveController;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Room {

    @Autowired
    MoveController moveController;

    private Integer id;
    private Integer maxPlayers = 6;
    private Integer minPlayers = 2;
    private List<Integer> playersIds = new ArrayList<>();
    private Game game;

    public void createGame(){
        game = new Game(50, 50, moveController, this.id);
    }

    public void setPlayersIds(List<Integer> playersIds){
        try {
            this.playersIds = playersIds;
            if (playersIds.size() == 1) {
                Thread.sleep(5000);
                createGame();
                game.addPlayer(playersIds.get(0));
                game.initGame();
                game.start();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

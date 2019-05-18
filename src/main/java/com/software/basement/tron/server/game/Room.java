package com.software.basement.tron.server.game;

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
        game = new Game(50, 50);
    }

    public void setPlayersIds(List<Integer> playersIds){
        try {
            this.playersIds = playersIds;
            if (playersIds.size() == 2) {
                Thread.sleep(5000);
                createGame();
                game.start();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

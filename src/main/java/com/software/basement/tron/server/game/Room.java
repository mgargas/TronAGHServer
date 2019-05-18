package com.software.basement.tron.server.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.software.basement.tron.server.websockets.controllers.MoveController;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Room {

    @JsonIgnore
    private MoveController moveController;

    private Integer id;
    private Integer maxPlayers = 6;
    private Integer minPlayers = 2;
    private List<Integer> playersIds = new ArrayList<>();
    @JsonIgnore
    private Game game;

    public void createGame(){
        //System.out.println("id: " + this.id);
        //System.out.println("movecontroller: " + moveController.equals(null));
        if(moveController == null) System.out.println(">CONST>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>NULLLL XDDDD");
        game = new Game(50, 50, moveController, 0);
    }



    public void setPlayersIds(List<Integer> playersIds){

        System.out.println("player added");
        this.playersIds = playersIds;

    }

    public void setMoveController(MoveController moveController){
        System.out.println("IN SETTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTER");
        if(moveController == null) System.out.println(">ROOM SETTER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>NULLLL XDDDD");
        this.moveController = moveController;
    }

    public void roomTestStart(){
        try {
            if(moveController == null) System.out.println(">SET>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>NULLLL XDDDD");
            if (playersIds.size() == 1) {
                Thread.sleep(5000);
                createGame();
                game.addPlayer(playersIds.get(0));
                game.initGame();
                game.start();
                System.out.println("game started");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

package com.software.basement.tron.server.websockets.controllers;

import com.software.basement.tron.server.game.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {

    private Map<Integer, Point> players;

    public GameState(Map<Integer, Player> playerMap){

        this.players = new HashMap<>();

        for(Player player : playerMap.values()){
            this.players.put(player.getId(), player.getPosition());
        }
    }

    public Map<Integer, Point> getPlayers(){
        return this.players;
    }

    public void setPlayers(Map<Integer, Point> players) {
        this.players = players;
    }


}

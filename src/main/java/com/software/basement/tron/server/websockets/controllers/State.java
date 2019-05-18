package com.software.basement.tron.server.websockets.controllers;

import com.software.basement.tron.server.game.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {

    private Map<Integer, Point> players;

    public State(List<Player> playerList){

        this.players = new HashMap<>();

        for(Player player : playerList){
            this.players.put(player.getId(), player.getPosition());
        }
    }

    public Map<Integer, Point> getPlayers(){
        return this.players;
    }

}

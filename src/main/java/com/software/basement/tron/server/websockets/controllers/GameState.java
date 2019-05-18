package com.software.basement.tron.server.websockets.controllers;

import com.software.basement.tron.server.game.Player;
import lombok.Data;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Data
public class GameState {

    private Map<Integer, Point> players;

    public GameState(Map<Integer, Player> playerMap) {

        this.players = new HashMap<>();

        for (Player player : playerMap.values()) {
            this.players.put(player.getId(), player.getPosition());
        }
    }
}

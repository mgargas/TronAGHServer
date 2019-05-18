package com.software.basement.tron.server.websockets.controllers;

import com.software.basement.tron.server.game.Player;
import lombok.Data;

import java.util.Map;
import java.util.List;

@Data
public class GameState {

    private List<PlayerInfo> playersInfo;

    public GameState(Map<Integer, Player> playerMap) {

        for (Player player : playerMap.values()) {
            new PlayerInfo(player.getId(), player.getPosition());
        }
    }
}

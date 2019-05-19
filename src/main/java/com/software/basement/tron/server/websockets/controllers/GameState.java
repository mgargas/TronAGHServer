package com.software.basement.tron.server.websockets.controllers;

import com.software.basement.tron.server.game.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class GameState {

    private List<PlayerInfo> playersInfo;

    public GameState(Map<Integer, Player> playerMap) {
        playersInfo = new ArrayList<>();

        for (Player player : playerMap.values()) {
            playersInfo.add(new PlayerInfo(player.getId(), player.getPosition()));
        }
    }
}

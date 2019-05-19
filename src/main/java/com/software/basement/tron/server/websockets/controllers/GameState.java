package com.software.basement.tron.server.websockets.controllers;

import com.software.basement.tron.server.game.Bonus;
import com.software.basement.tron.server.game.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class GameState {

    private List<PlayerInfo> playersInfo;
    private List<BonusInfo> bonusesInfo;

    public GameState(Map<Integer, Player> playerMap, List<Bonus> bonusList) {
        playersInfo = new ArrayList<>();
        bonusesInfo = new ArrayList<>();

        for (Player player : playerMap.values()) {
            playersInfo.add(new PlayerInfo(player.getId(), player.getPosition()));
        }

        for (Bonus bonus : bonusList) {
            bonusesInfo.add(new BonusInfo(bonus.getType().getName(), bonus.getPosition()));
        }
    }
}

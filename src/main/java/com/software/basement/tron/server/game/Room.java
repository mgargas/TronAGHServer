package com.software.basement.tron.server.game;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Room {

    private Integer id;
    private Integer maxPlayers;
    private Integer minPlayers;
    private List<Integer> playersIds;
}

package com.software.basement.tron.server.game;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Room {

    private Integer maxPlayers;
    private Integer minPlayers;
    private Integer availableSlots = 0;

    public Room(Integer maxPlayers, Integer minPlayers) {
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.availableSlots = maxPlayers;
    }
}

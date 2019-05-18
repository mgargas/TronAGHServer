package com.software.basement.tron.server.game;

public class Room {

    private Integer maxPlayers;
    private Integer minPlayers;
    private Integer availableSlots;

    public Room(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.availableSlots = maxPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }
}

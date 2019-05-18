package com.software.basement.tron.server.game;

public class Room {

    private Integer maxPlayers;
    private Integer minPlayers;
    private Integer availableSlots = 0;

    public Room(){}

    public Room(Integer maxPlayers, Integer minPlayers) {
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.availableSlots = maxPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }
}

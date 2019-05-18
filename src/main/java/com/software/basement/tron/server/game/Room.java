package com.software.basement.tron.server.game;

public class Room {

    private Integer maxCapacity;
    private Integer availableSlots;

    public Room(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.availableSlots = maxCapacity;
    }

    public void joinRoom() {
        if (!canPlayerJoin())
            throw new RuntimeException("Room full, cannot join.");

        availableSlots--;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    private boolean canPlayerJoin() {
        return availableSlots > 0;
    }
}

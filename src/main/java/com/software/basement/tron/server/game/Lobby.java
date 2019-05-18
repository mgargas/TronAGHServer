package com.software.basement.tron.server.game;

import java.util.HashMap;
import java.util.Map;

public class Lobby {

    private static Lobby instance = new Lobby();
    private static Integer nextRoomId = 0;
    private final static Integer MAX_ROOM_CAPACITY = 6;
    private Map<Integer, Room> rooms = new HashMap<>();

    private Lobby() {
    }

    public static Lobby getInstance() {
        return instance;
    }

    public void createNewRoom() {
        rooms.put(nextRoomId, new Room(MAX_ROOM_CAPACITY));
        nextRoomId++;
    }

    public Room getRoom(Integer roomId) {
        if (roomExists(roomId))
            return rooms.get(roomId);
        else
            throw new RuntimeException("Room with ID " + roomId + " not found.");
    }

    public static Integer getNextRoomId() {
        return nextRoomId;
    }

    public static Integer getMaxRoomCapacity() {
        return MAX_ROOM_CAPACITY;
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    private boolean roomExists(Integer roomId) {
        return rooms.get(roomId) != null;
    }
}

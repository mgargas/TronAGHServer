package com.software.basement.tron.server.game;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class Lobby {

    private static Integer nextRoomId = 0;
    private Map<Integer, Room> rooms = new HashMap<>();

    public Room getRoom(Integer roomId) {
        if (roomExists(roomId))
            return rooms.get(roomId);
        else
            throw new RuntimeException("Room with ID " + roomId + " not found.");
    }

    public void addRoom(Room room) {
        rooms.put(nextRoomId, room);
        nextRoomId++;
    }

    private boolean roomExists(Integer roomId) {
        return rooms.get(roomId) != null;
    }
}

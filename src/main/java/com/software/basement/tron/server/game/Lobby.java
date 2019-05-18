package com.software.basement.tron.server.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.software.basement.tron.server.websockets.controllers.MoveController;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class Lobby {

    @JsonIgnore
    @Autowired
    MoveController moveController;

    private static Integer nextRoomId = 0;
    private Map<Integer, Room> rooms = new HashMap<>();

    public Room getRoom(Integer roomId) {
        if (roomExists(roomId))
            return rooms.get(roomId);
        else
            throw new RuntimeException("Room with ID " + roomId + " not found.");
    }

    public void addRoom(Room room) {
        if(moveController == null) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>NULLLL XDDDD");
        room.setMoveController(moveController);
        room.setId(nextRoomId);
        rooms.put(nextRoomId, room);
        room.roomTestStart();
        nextRoomId++;
    }

    private boolean roomExists(Integer roomId) {
        return rooms.get(roomId) != null;
    }
}

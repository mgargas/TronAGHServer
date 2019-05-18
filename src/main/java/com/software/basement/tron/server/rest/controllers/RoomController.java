package com.software.basement.tron.server.rest.controllers;

import com.software.basement.tron.server.game.Lobby;
import com.software.basement.tron.server.game.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final Lobby lobby;

    @Autowired
    public RoomController(Lobby lobby) {
        this.lobby = lobby;
    }

    @GetMapping
    public List<Room> getRooms() {
        return new ArrayList<>(lobby.getRooms().values());
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Integer id) {
        return lobby.getRoom(id);
    }

    @PutMapping("/{id}")
    public List<Room> editRoom(@RequestBody Room room, @PathVariable Integer id) {
        lobby.getRooms().put(id, room);
        return new ArrayList<>(lobby.getRooms().values());
    }

    @PostMapping
    public Lobby createRoom(@RequestBody Room newRoom) {
        lobby.addRoom(newRoom);
        return lobby;
    }

}

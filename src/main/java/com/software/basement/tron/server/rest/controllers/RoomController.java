package com.software.basement.tron.server.rest.controllers;

import com.software.basement.tron.server.game.Lobby;
import com.software.basement.tron.server.game.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomController {
    private Lobby lobby = Lobby.getInstance();

    @GetMapping
    public Map<Integer, Room> getRooms() {
        return lobby.getRooms();
    }

    @GetMapping("/join")
    public Lobby join(@RequestParam(value = "roomId", defaultValue = "-1") String roomId) {
        lobby.getRoom(Integer.valueOf(roomId)).joinRoom();
        return lobby;
    }

    @GetMapping("/create")
    public Lobby create() {
        lobby.createNewRoom();
        return lobby;
    }

}

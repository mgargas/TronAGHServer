package com.software.basement.tron.server.websockets.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.software.basement.tron.server.game.Lobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MoveController {

    private SimpMessagingTemplate template;

    @JsonIgnore
    @Autowired
    Lobby lobby;

    @Autowired
    public MoveController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @MessageMapping("/room/{roomID}")
    public void processMove(@DestinationVariable String roomID, Move message) throws Exception {
        System.out.println(String.format("Got destination message from %d, command: %d", message.getId(), message.getTurn()));
        try {

            lobby.getRoom(Integer.parseInt(roomID)).getGame().turnPlayer(message.getId(), message.getTurn());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public void sendState(GameState gameState, String roomID) {
        System.out.println("Send game state " + gameState + " Room id " + roomID);
        this.template.convertAndSend("/topic/room/" + roomID, gameState);
    }

}
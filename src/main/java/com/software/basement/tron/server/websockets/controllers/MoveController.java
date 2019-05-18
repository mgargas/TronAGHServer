package com.software.basement.tron.server.websockets.controllers;

import com.software.basement.tron.server.game.Lobby;
import com.software.basement.tron.server.game.Player;
import com.software.basement.tron.server.websockets.hello.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class MoveController {

    private SimpMessagingTemplate template;

    @Autowired
    Lobby lobby;


    @Autowired
    public MoveController(SimpMessagingTemplate template){
        this.template = template;
    }


    @MessageMapping("/room/{roomID}")
    public void processMove(@DestinationVariable String room, Move message) throws Exception {
        System.out.println("Here!");
        //lobby.getRoom(room).getGame().processDestination();
    }


    public void sendState(State state, String roomID){
        this.template.convertAndSend("/topic/room/"+roomID, state);
    }

}
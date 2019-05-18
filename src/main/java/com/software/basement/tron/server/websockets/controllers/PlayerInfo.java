package com.software.basement.tron.server.websockets.controllers;

import lombok.Data;

import java.awt.*;

@Data
public class PlayerInfo {

    private int id;
    private Point position;

    public PlayerInfo(int id, Point position) {
        this.id = id;
        this.position = position;
    }
}

package com.software.basement.tron.server.websockets.controllers;

import lombok.Data;

import java.awt.*;

@Data
public class BonusInfo {

    private String name;
    private Point position;

    public BonusInfo(String name, Point position) {
        this.name = name;
        this.position = position;
    }
}

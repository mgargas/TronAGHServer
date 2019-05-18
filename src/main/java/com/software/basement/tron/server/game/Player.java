package com.software.basement.tron.server.game;

import java.awt.*;

public class Player {

    private int lives;
    private boolean isImmortal;
    private int speed;
    private Point position;
    private String name;


    public Player(int lives, boolean isImmortal, int speed, Point position, String name) {
        this.lives = lives;
        this.isImmortal = isImmortal;
        this.speed = speed;
        this.position = position;
        this.name = name;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isImmortal() {
        return isImmortal;
    }

    public void setImmortal(boolean immortal) {
        isImmortal = immortal;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.software.basement.tron.server.game;

import lombok.Data;

import java.awt.*;

@Data
public class Player {

    private int id;
    private int lives;
    private boolean isImmortal;
    private int speed;
    private int countSpeed;
    private Point position;
    private String name;
    private Direction direction;
    private boolean isDead;


    public Player(int id, String name) {
        this.id = id;
        this.lives = 3;
        this.isImmortal = false;
        this.speed = 2;
        this.countSpeed = 2;
        this.position = new Point(0, 0);
        this.name = name;
        this.direction = null;
        this.isDead = false;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public void moveIteration() {
        this.countSpeed--;
        if (countSpeed == 0) {
            countSpeed = speed;
            move();
        }
    }

    public void move() {
        switch (getDirection()) {
            case N:
                setPosition(new Point(getX(), getY() + 1));
                break;
            case S:
                setPosition(new Point(getX(), getY() - 1));
                break;
            case W:
                setPosition(new Point(getX() - 1, getY()));
                break;
            case E:
                setPosition(new Point(getX() + 1, getY()));
                break;
        }
    }
}

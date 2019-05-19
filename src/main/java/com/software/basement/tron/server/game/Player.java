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
    private boolean hasBeenRecentlyMoved;


    public Player(int id, String name) {
        this.id = id;
        this.lives = 3;
        this.isImmortal = false;
        this.speed = 6;
        this.countSpeed = 6;
        this.position = new Point(0, 0);
        this.name = name;
        this.direction = Direction.N;
        this.isDead = false;
        this.hasBeenRecentlyMoved = false;
    }

    public synchronized int getX() {
        return position.x;
    }

    public synchronized int getY() {
        return position.y;
    }

    public synchronized void moveIteration() {
        hasBeenRecentlyMoved = false;
        this.countSpeed--;
        if (countSpeed == 0) {
            hasBeenRecentlyMoved = true;
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

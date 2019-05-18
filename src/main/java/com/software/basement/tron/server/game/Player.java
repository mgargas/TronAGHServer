package com.software.basement.tron.server.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private int id;
    private int lives;
    private boolean isImmortal;
    private int speed;
    private Point position;
    private String name;
    private Direction direction;


    public Player(int id, String name) {
        this.id = id;
        this.lives = 3;
        this.isImmortal = false;
        this.speed = 1;
        this.position = new Point(0,0);
        this.name = name;
        this.direction = null;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX(){
        return position.x;
    }

    public int getY(){
        return position.y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Point> move(){
        List<Point> points = new ArrayList<>();
        int dest = getSpeed();


    }
}

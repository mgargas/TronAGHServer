package com.software.basement.tron.server.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private int id;
    private int lives;
    private boolean isImmortal;
    private int speed;
    private int countSpeed;
    private Point position;
    private String name;
    private Direction direction;


    public Player(int id, String name) {
        this.id = id;
        this.lives = 3;
        this.isImmortal = false;
        this.speed = 10;
        this.countSpeed = 10;
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

    public Point moveIteration(){
        this.countSpeed--;
        if(countSpeed == 0){
            countSpeed = speed;
            return move();
        } else {
            return getPosition();
        }
    }

    public Point move(){
        switch (getDirection()){
            case N : return new Point(getX(), getY() + 1);
            case S : return new Point(getX(), getY() - 1);
            case W : return new Point(getX() - 1, getY());
            case E : return new Point(getX() + 1, getY());
        }
        return null;
    }
}

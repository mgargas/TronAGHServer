package com.software.basement.tron.server.game;

import lombok.Data;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.software.basement.tron.server.game.BonusType.*;

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
    private Map<BonusType, Integer> bonusesIterationsLeft;
    private boolean isDead;
    private boolean hasBeenRecentlyMoved;


    public Player(int id, String name) {
        this.id = id;
        this.lives = 3;
        this.isImmortal = false;
        this.speed = 1;
        this.countSpeed = 1;
        this.position = new Point(0, 0);
        this.name = name;
        this.direction = Direction.N;
        this.isDead = false;
        this.hasBeenRecentlyMoved = false;
        this.bonusesIterationsLeft = new HashMap<>();
    }

    public synchronized int getX() {
        return position.x;
    }

    public synchronized int getY() {
        return position.y;
    }

    public synchronized void moveIteration() {
        updateBonusesIterationsLeft();
        hasBeenRecentlyMoved = false;
        this.countSpeed--;
        if (countSpeed == 0) {
            hasBeenRecentlyMoved = true;
            countSpeed = speed;
            move();
        }
    }

    public void activateBonus(BonusType bonus) {
        if (bonus == IMMORTALITY) this.setImmortal(true);
        if (bonus == SPEED_UP) this.setSpeed(speed / 2);
    }

    private void updateBonusesIterationsLeft() {
        bonusesIterationsLeft.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .forEach(entry -> bonusesIterationsLeft.put(entry.getKey(), entry.getValue() - 1));
        bonusesIterationsLeft.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .forEach(entry -> deactivateBonus(entry.getKey()));
    }

    private void deactivateBonus(BonusType bonus) {
        if (bonus == IMMORTALITY) this.setImmortal(false);
        if (bonus == SPEED_UP) this.setSpeed(speed * 2);
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

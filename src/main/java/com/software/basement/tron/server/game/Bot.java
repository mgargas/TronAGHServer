package com.software.basement.tron.server.game;

import java.awt.*;
import java.util.Random;

public class Bot extends Player {

    private int moveLength;
    private Game game;
    private Random rand;

    public Bot(Game game){
        super(100003, "Bot");
        this.game = game;
        this.rand = new Random();
        this.moveLength = 5;
    }

    public void move() {

        moveLength--;
        super.move();

        if (moveLength == 0) {

            if (rand.nextInt() % 2 == 0) {
                //System.out.println("Turn left");
                setDirection(this.getDirection().turnLeft(this.getDirection()));
            } else {
                //System.out.println("Turn right");
                setDirection(this.getDirection().turnRight(this.getDirection()));
            }

            moveLength = rand.nextInt(game.getHeight() / 4) + 1;
            //System.out.println(String.format("Next move: %d", moveLength));

        }
    }


}

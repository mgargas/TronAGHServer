package com.software.basement.tron.server.game;

import java.util.Random;

public class Bot extends Player {

    private Game game;
    private Random rand;

    public Bot(Game game) {
        super(100003, "Bot");
        this.game = game;
        this.rand = new Random();
    }

    public void move() {
        super.move();
        avoidObstacle(getDirection());
    }

    private void avoidObstacle(Direction direction) {
        switch (direction) {
            case N:
                try {
                    if (game.getBoard()[game.getHeight() - this.getY() - 1][this.getX()] == 1) {
                        // obstacle North
                        changeDirectionFromN();
                    }
                } catch (IndexOutOfBoundsException ex) {
                    // caught when out of bounds in N
                    changeDirectionFromN();
                }
                break;
            case S:
                try {
                    if (game.getBoard()[game.getHeight() - this.getY() + 1][this.getX()] == 1) {
                        // obstacle South
                        changeDirectionFromS();
                    }
                } catch (IndexOutOfBoundsException ex) {
                    // caught when out of bounds in S
                    changeDirectionFromS();
                }
                break;
            case W:
                try {
                    if (game.getBoard()[game.getHeight() - this.getY()][this.getX() - 1] == 1) {
                        // obstacle West
                        changeDirectionFromW();
                    }
                } catch (IndexOutOfBoundsException ex) {
                    // caught when out of bounds in W
                    changeDirectionFromW();
                }
                break;
            case E:
                try {
                    if (game.getBoard()[game.getHeight() - this.getY()][this.getX() + 1] == 1) {
                        // obstacle East
                        changeDirectionFromE();
                    }
                } catch (IndexOutOfBoundsException ex) {
                    // caught when out of bounds in E
                    changeDirectionFromE();
                }
                break;
        }
    }

    private void changeDirectionFromN() {
        try {
            if (game.getBoard()[game.getHeight() - this.getY()][this.getX() + 1] == 1) {
                //obstacle East, turn left (West)
                setDirection(this.getDirection().turnLeft(this.getDirection()));
            } else {
                // no obstacle East, turn right
                setDirection(this.getDirection().turnRight(this.getDirection()));
            }
        } catch (IndexOutOfBoundsException ex) {
            // caught if obstacles in N, out of bounds in E; if obstacles are in N, E and W we don't really care
            // so - turn left (West)
            setDirection(this.getDirection().turnLeft(this.getDirection()));
        }
    }

    private void changeDirectionFromS() {
        try {
            if (game.getBoard()[game.getHeight() - this.getY()][this.getX() + 1] == 1) {
                //obstacle East, turn right (West)
                setDirection(this.getDirection().turnRight(this.getDirection()));
            } else {
                // no obstacle West, turn left (East)
                setDirection(this.getDirection().turnLeft(this.getDirection()));
            }
        } catch (IndexOutOfBoundsException ex) {
            // caught if obstacles in S, out of bounds in E; if obstacles are in S, E and W we don't really care
            // so - turn right (West)
            setDirection(this.getDirection().turnRight(this.getDirection()));
        }
    }

    private void changeDirectionFromW() {
        try {
            if (game.getBoard()[game.getHeight() - this.getY() - 1][this.getX()] == 1) {
                //obstacle North, turn left (South)
                setDirection(this.getDirection().turnLeft(this.getDirection()));
            } else {
                // no obstacle North, turn right (North)
                setDirection(this.getDirection().turnRight(this.getDirection()));
            }
        } catch (IndexOutOfBoundsException ex) {
            // caught if obstacles in W, out of bounds in N; if obstacles are in W, N and S we don't really care
            // so - turn left (South)
            setDirection(this.getDirection().turnLeft(this.getDirection()));
        }
    }

    private void changeDirectionFromE() {
        try {
            if (game.getBoard()[game.getHeight() - this.getY() - 1][this.getX()] == 1) {
                //obstacle North, turn right (South)
                setDirection(this.getDirection().turnRight(this.getDirection()));
            } else {
                // no obstacle North, turn left (South)
                setDirection(this.getDirection().turnLeft(this.getDirection()));
            }
        } catch (IndexOutOfBoundsException ex) {
            // caught if obstacles in E, out of bounds in N; if obstacles are in W, N and S we don't really care
            // so - turn right (South)
            setDirection(this.getDirection().turnRight(this.getDirection()));
        }
    }

    private void turnRandomly() {
        if (rand.nextInt() % 2 == 0) {
            System.out.println("Turn left");
            setDirection(this.getDirection().turnLeft(this.getDirection()));
        } else {
            System.out.println("Turn right");
            setDirection(this.getDirection().turnRight(this.getDirection()));
        }
    }
}

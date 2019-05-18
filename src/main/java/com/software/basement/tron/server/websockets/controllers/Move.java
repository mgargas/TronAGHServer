package com.software.basement.tron.server.websockets.controllers;

public class Move {

    private Integer id;
    private Integer turn;

    public Move(){};

    public Move(Integer id, Integer turn){
        this.id = id;
        this.turn = turn;
    }

    public Integer getID() {
        return this.id;
    }

    public Integer getTurn(){
        return this.turn;
    }


}

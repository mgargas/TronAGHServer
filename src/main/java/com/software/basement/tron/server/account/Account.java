package com.software.basement.tron.server.account;


import org.bson.types.ObjectId;

public class Account {

    ObjectId id;
    private String username;
    private String password;
    private Integer playerId;
    private Integer wins;

    public Account(ObjectId id, String username, String password, Integer playerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wins = 0;
    }

    public Account() {
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

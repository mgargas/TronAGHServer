package com.software.basement.tron.server.account;


import org.bson.types.ObjectId;

public class Account {

    ObjectId id;
    private String username;
    private String password;
    private Integer playerId;
    private Integer wonGames;

    public Account(ObjectId id, String username, String password, Integer playerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wonGames = 0;
    }

    public Account() {
    }

    public Integer getWonGames() {
        return wonGames;
    }

    public void setWonGames(Integer wonGames) {
        this.wonGames = wonGames;
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

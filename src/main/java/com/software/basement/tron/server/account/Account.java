package com.software.basement.tron.server.account;


import org.bson.types.ObjectId;

public class Account {

    ObjectId id;
    private String username;
    private String password;
    public Account(ObjectId id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public Account() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

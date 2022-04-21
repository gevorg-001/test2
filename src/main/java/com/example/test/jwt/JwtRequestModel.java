package com.example.test.jwt;

import java.io.Serializable;

public class JwtRequestModel implements Serializable {

    private static final long serialVersionUID = 2636936156391265891L;
    private String username;
    private String password;
    private int id;

    public JwtRequestModel() {
    }

    public JwtRequestModel(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
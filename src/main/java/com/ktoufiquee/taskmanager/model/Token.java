package com.ktoufiquee.taskmanager.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "token")
public class Token {
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID token;

    public Token() {}

    public Token(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}

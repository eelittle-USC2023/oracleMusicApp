package com.model;

public abstract class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}

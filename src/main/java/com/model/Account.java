package com.model;

public class Account 
{
    private String username;
    private String password;
    private String role;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getRole(){
        return role;
    }
}

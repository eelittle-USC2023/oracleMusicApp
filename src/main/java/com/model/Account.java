package com.model;

public class Account 
{
    private String username;
    private String password;

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public boolean checkLogin(String username, String password)
    {
        return true;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}

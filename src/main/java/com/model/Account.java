package com.model;

public class Account 
{
    private String username;
    private String password;
    private String role;

    public Account(String username, String password, String role)
    {

    }
    public boolean checkLogin(String username, String password)
    {
        return true;
    }
    public String getUsername(){
        return username;
    }
}

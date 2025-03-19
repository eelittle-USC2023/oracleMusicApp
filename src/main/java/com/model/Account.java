package com.model;

public class Account 
{
    private String username;
    private String password;
    private String role;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean checkLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
<<<<<<< HEAD

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
=======
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    public String role() {
    return role;
    }
}
>>>>>>> 101a54ae6f8407bfdb3d122eafb061ced23efb8a

package com.model;

import java.util.ArrayList;

public class Driver {
    
    public static void main(String[] args) {
        //createAccountScenario();
        //playSongScenario();
        makeSongScenario();
    }
    
    private static void createAccountScenario() {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        boolean accountCreated = facade.createAccount("ffredickson", "Password123", "Student");
        if (!accountCreated){
            System.out.println("Account creation failed, this username is already taken");
        }
        accountCreated = facade.createAccount("ffred", "GreatPassword123", "Student");
        if (accountCreated){
            System.out.println("Account was created successfully!");
        }
        facade.logout();
        boolean loginSuccessful = facade.login("ffred", "Greatpassword123");
        if (loginSuccessful){
            System.out.println("Your login was successful!");
        }
        else {
            System.out.println("Login failed");
        }
        facade.logout();
    } 

    private static void playSongScenario() {

    }

    private static void makeSongScenario() {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        facade.login("ffredickson", "pword");
        System.out.println("Logged in as ffredrickson");
        facade.makeSong("A Horse's Journey");
        facade.addMeasure(4, 4, "Amajor");
        facade.addNote("A", 2, 1.0, 0.0);
        facade.addNote("C", 2, 1.0, 1.0);
        facade.addNote("F", 2, 1.0, 2.0);
        facade.addNote("C", 2, 1.0, 3.0);
        facade.addMeasure(4, 4, "Amajor");
        facade.addNote("A", 2, 1.0, 0.0);
        facade.addNote("C#", 2, 1.0, 0.0);
        facade.addNote("F#", 2, 1.0, 0.0);
        facade.addNote("A", 3, 1.0, 0.0);
        facade.logout();
        facade.login("ffred", "GreatPassword123");
        ArrayList<Song> searchResults = facade.songSearch("Title", "A Horse's Journey");
        
    }    
}

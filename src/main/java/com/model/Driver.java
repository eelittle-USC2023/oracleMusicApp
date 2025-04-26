package com.model;

import java.util.ArrayList;

public class Driver {
    
    public static void main(String[] args) {
        //createAccountScenario();
        playSongScenario();
        //makeSongScenario();
    }
    /**
     * Scenario where Fred Fredrickson attempts to make an account, but it fails because the username is taken.
     * He then changes his username, and his attempt succeeds. Then, he logs out and logs back in successfully.
     * @author Ethan Little and James Lyles
     */
    private static void createAccountScenario() {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        boolean accountCreated = facade.createAccount("ffredrickson", "GreatPassword123", "Student");
        if (!accountCreated){
            System.out.println("Account creation failed, this username is already taken");
        }
        accountCreated = facade.createAccount("ffred", "GreatPassword123", "Student");
        if (accountCreated){
            System.out.println("Account was created successfully!");
        }
        facade.logout();
        boolean loginSuccessful = facade.login("ffred", "GreatPassword123");
        if (loginSuccessful){
            System.out.println("Your login was successful!");
        }
    } 

    /**
     * 
     * @author Ethan Little
     */
    private static void playSongScenario() {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        facade.login("ffred", "GreatPassword123");
        ArrayList<Song> searchResults = facade.songSearch("Artist", "Deep Purple");
        for (Song s : searchResults) {
            System.out.println(s.getTitle() + " by " + s.getArtistName());
        }
        facade.setSelectedSong(searchResults.get(0));
        //facade.displayNotes();
        facade.displayTabs();
        //facade.playSong();
        

    }
    /**
     * Scenario where ffredrickson logs in, makes a new song, and then logs out. The song is saved to JSON as a result.
     * ffred then logs in and searches for and plays the song.
     * @author Ethan Little
     */
    private static void makeSongScenario() {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        facade.login("ffredrickson", "pword");
        System.out.println("Logged in as ffredrickson");
        facade.createNewSong("A Horse's Journey");
        facade.addMeasure(4, 4, "Amajor");
        facade.addNote(0, "A", 3, 1.0, 0.0);
        facade.addNote(0, "C", 4, 1.0, 1.0);
        facade.addNote(0, "F", 4, 1.0, 2.0);
        facade.addNote(0, "C", 4, 1.0, 3.0);
        facade.addMeasure(4, 4, "Amajor");
        facade.addNote(1, "A", 3, 1.0, 0.0);
        facade.addNote(1, "C#", 4, 1.0, 1.0);
        facade.addNote(1, "F#", 4, 1.0, 2.0);
        facade.addNote(1, "A", 4, 1.0, 3.0);
        facade.logout();
        System.out.println("Logged in as ffred");
        facade.login("ffred", "GreatPassword123");
        ArrayList<Song> searchResults = facade.songSearch("Title", "A Horse's Journey");
        for (Song s : searchResults) {
            System.out.println(s.getTitle() + " by " + s.getArtistName());
        }
        facade.setSelectedSong(searchResults.get(0));
        facade.playSong();
    }    
}

package com.musicapp;

import com.model.Account;
import com.model.AccountList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane; 
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class SongMenuController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField searchField;

    @FXML 
    private Button addSavedSongButton;

    @FXML
    private Button createSongButton;
    private String currentUsername = ""; /*The LoginController uses the facade to get the username and password so the MainSongMenu.fxml needs to also go to the facade in order to to get username so that it can have ( "Welcome," + whatever + "!"); instead of having hardcoded username 
     */

    @FXML
    public void initialize() {
       welcomeLabel.setText("Welcome, " + currentUsername + "!");
    }

    public void setUsername(String username){
        this.currentUsername = username;
        if(welcomeLabel != null) {
            welcomeLabel.setText("Welcome, " + username + "!");
        }
    }

    @FXML 
    private void handleSearchInput(){
        String query = searchField.getText();
        if (query == null || query.isEmpty()) {
            System.out.println("Search field is empty.");
        } else {
            System.out.println("Searching for: " + query);
        }
    }

    @FXML /*Whenever the user adds new savedsong it stores the song to an arraylist of saved songs for the user and also adds the song 
             album cover to the squares so all they have to do is click it so it can take them to the SongScreen.fxml*/ 
    private void handleAddSavedSong(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Song");
        dialog.setHeaderText("Enter song name to add");
        dialog.showAndWait().ifPresent(songName -> {
            System.out.println("Adding song: " + songName);
        });
    }

    @FXML //This needs to head towards create.fxml
    private void handleCreateSong(){
        Dialog<Song> dialog = new Dialog<>();
        dialog.setTitle("Create New Song");
        
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        TextField titleField = new TextField();
        TextField artistField = new TextField();
        
        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Artist:"), 0, 1);
        grid.add(artistField, 1, 1);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                return new Song(titleField.getText(), artistField.getText(), "");
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(newSong -> {
            System.out.println("Created new song: " + newSong.getTitle());
        });
    }
// Search bar will filter between artists or song title so that the user can search by only artists or by only song title 
    
    public static class Song {
        private String title;
        private String artist;
        private String albumArt;
        
        public Song(String title, String artist, String albumArt) {
            this.title = title;
            this.artist = artist;
            this.albumArt = albumArt;
        }
        
        public String getTitle() { return title; }
        public String getArtist() { return artist; }
        public String getAlbumArt() { return albumArt; }
    }
}
//Created by Sakthi Thanigai


package com.musicapp;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import java.io.IOException;


import java.io.IOException;
import java.net.URL;

import com.model.OracleMusicAppFacade;
import com.model.Song;

public class SongScreenController{

    @FXML private ImageView albumCover;
    @FXML private Text titleText;
    @FXML private Text artistText;
    @FXML private Text genreText;
    @FXML private Text difficultyText;

    @FXML private Button playButton;
    @FXML private Button createButton;
    @FXML private Button shareButton;
    @FXML private Button backButton;

    public void initialize() {
        // Set basic song information
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        Song song = facade.getSelectedSong();
        titleText.setText(song.getTitle());
        artistText.setText(song.getArtistName());
        genreText.setText("Genre: " + song.getGenre());
        difficultyText.setText("Difficulty: " + song.getDifficulty());
    
        // Correct resource paths based on your actual image location
        String imageFileName = song.getTitle().toLowerCase().replaceAll(" ", "_") + ".jpg";
        String imagePath = "/com/musicapp/images/" + imageFileName;
        String defaultPath = "/com/musicapp/images/default_album_cover.png";
    
        // Debug output
        System.out.println("Looking for album image: " + imagePath);
        System.out.println("Found album image? " + (getClass().getResource(imagePath) != null));
        System.out.println("Found default image? " + (getClass().getResource(defaultPath) != null));
    
        Image image = null;
    
        if (getClass().getResourceAsStream(imagePath) != null) {
            image = new Image(getClass().getResourceAsStream(imagePath));
        } else if (getClass().getResourceAsStream(defaultPath) != null) {
            System.out.println("Album cover not found, using default.");
            image = new Image(getClass().getResourceAsStream(defaultPath));
        } else {
            System.out.println(" Neither album image nor default found.");
        }
    
        albumCover.setImage(image);
    }

    @FXML
    void backButtonClicked(MouseEvent event) throws IOException {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        facade.setSelectedSong(null);
        App.setRoot("MainSongMenu");
    }

    @FXML
    private void playButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playMusic");
    }

    
}
    

//Created by Sakthi Thanigai

package com.musicapp;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

import com.model.OracleMusicAppFacade;
import com.model.Song;

/**
 * Controller class for the SongScreen view.
 * 
 * Displays song details including album cover, title, artist, genre, and difficulty.
 * Handles button events for navigating back or playing a song.
 * 
 * @author Sakthi Thanigai
 */
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

    /**
     * Initializes the SongScreen by loading the selected song's information
     * and setting the album cover image if available.
     * If no specific album cover is found, a default image is used.
     */
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

    /**
     * Event handler for when the back button is clicked.
     * Returns to the main song menu and clears the selected song.
     * 
     * @param event the MouseEvent triggered when clicking the back button
     * @throws IOException if the MainSongMenu FXML cannot be loaded
     */
    @FXML
    void backButtonClicked(MouseEvent event) throws IOException {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        facade.setSelectedSong(null);
        App.setRoot("MainSongMenu");
    }

    /**
     * Event handler for when the play button is clicked.
     * Navigates to the PlayMusic screen.
     * 
     * @param event the MouseEvent triggered when clicking the play button
     * @throws IOException if the playMusic FXML cannot be loaded
     */
    @FXML
    private void playButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playMusic");
    }

    
}
    

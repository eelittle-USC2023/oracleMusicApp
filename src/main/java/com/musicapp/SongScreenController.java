package com.musicapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import com.model.Song;

public class SongScreenController {

    @FXML private ImageView albumCover;
    @FXML private Text titleText;
    @FXML private Text artistText;
    @FXML private Text genreText;
    @FXML private Text difficultyText;

    @FXML private Button playButton;
    @FXML private Button createButton;
    @FXML private Button shareButton;

    public void setSong(Song song) {
        // Set basic song information
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
}
    
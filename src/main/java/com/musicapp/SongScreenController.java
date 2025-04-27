//Created by Sakthi Thanigai


package com.musicapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

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
    @FXML private Button backButton;

    private Song currentSong;

    @FXML
    public void initialize() {
        playButton.setOnAction(event -> openPlayMusicScreen());

        if (backButton != null) {
            backButton.setOnAction(event -> goBackToMenu());
        }
    }

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

    private void openPlayMusicScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/musicapp/playMusic.fxml"));
            Parent root = loader.load();

            PlayMusicController controller = loader.getController();
            //controller.setSongTitle(currentSong.getTitle()); // pass title to play screen

            Stage stage = (Stage) playButton.getScene().getWindow();
            stage.setScene(new Scene(root, 750, 340)); // Landscape size for playMusic.fxml
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goBackToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/musicapp/MainSongMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root, 375, 812)); // back to portrait menu
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void playButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playMusic");
    }
}
    

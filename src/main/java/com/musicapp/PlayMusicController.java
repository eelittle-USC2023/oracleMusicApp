package com.musicapp;

import java.io.IOException;
import java.util.ArrayList;

import com.model.OracleMusicAppFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 * This class manages the music playing screen.
 * @author Ethan Little
 */
public class PlayMusicController {
    @FXML
    private Text aStringText;

    @FXML
    private Text bStringText;

    @FXML
    private Text dStringText;

    @FXML
    private Button exit;

    @FXML
    private Text gStringText;

    @FXML
    private Text highEStringText;

    @FXML
    private Text lowEStringText;

    @FXML
    private Button playButton;

    @FXML
    private Text songTitle;

    @FXML
    private ToggleButton toInstrumentButton;

    @FXML
    private ToggleButton toMusicButton;

    @FXML
    private ToggleGroup displayButtons;

    private OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
    /**
     * When the screen is first loaded, sets up the title and artist name, as well as the music.
     * @author Ethan Little
     */
    public void initialize() {
        songTitle.setText(facade.getSelectedSongTitle() + "-" + facade.getSelectedSong().getArtistName());
        ArrayList<String> tabs = facade.getSongString();
        lowEStringText.setText(tabs.get(0));
        aStringText.setText(tabs.get(1));
        dStringText.setText(tabs.get(2));
        gStringText.setText(tabs.get(3));
        bStringText.setText(tabs.get(4));
        highEStringText.setText(tabs.get(5));
    }
    /**
     * Sends the user to the song screen when the exit button is clicked.
     * @param event
     * @throws IOException
     * @author Ethan Little
     */
    @FXML
    private void exitButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("SongScreen");
    }
    /**
     * Takes the user to the instrument playing screen when the button is clicked.
     * @param event
     * @throws IOException
     * @author Ethan Little
     */
    @FXML
    private void toInstrumentButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playInstrument");
    }
    /**
     * Takes the user the music playing screen when this button is clicked, but they are already there.
     * @param event
     * @throws IOException
     * @author Ethan Little
     */
    @FXML
    private void toMusicButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playMusic");
    }

    @FXML
    /**
     * Plays the song when the play button is clicked.
     * @param event
     * @throws IOException
     * @author Ethan Little
     */
    private void playButtonClicked(MouseEvent event) throws IOException {
        facade.playSong();
    }
}
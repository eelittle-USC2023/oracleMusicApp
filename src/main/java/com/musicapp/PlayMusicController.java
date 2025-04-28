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
    private Slider playbackSpeedSlider;

    @FXML
    private Text songTitle;

    @FXML
    private ToggleButton toInstrumentButton;

    @FXML
    private ToggleButton toMusicButton;

    @FXML
    private ToggleGroup displayButtons;

    private OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();

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

    @FXML
    private void exitButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("SongScreen");
    }

    @FXML
    private void toInstrumentButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playInstrument");
    }

    @FXML
    private void toMusicButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playMusic");
    }

    @FXML
    private void playButtonClicked(MouseEvent event) throws IOException {
        facade.playSong();
    }
}
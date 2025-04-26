package com.musicapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class PlayMusicController {

    @FXML
    private Button exit;

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
    private void exitButtonClicked(MouseEvent event) {

    }

}
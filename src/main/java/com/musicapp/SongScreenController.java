package com.musicapp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SongScreenController {

    @FXML
    private ImageView albumCover;

    @FXML
    private Button playButtonClicked;

    @FXML
    private void playButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playInstrument");
    }

}

package com.musicapp;

import java.io.IOException;
import java.util.UUID;

import com.model.OracleMusicAppFacade;
import com.model.SongList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SongMenuController {

    @FXML
    private Label firstSong;

    @FXML
    private void firstSongClicked(MouseEvent event) throws IOException {
        OracleMusicAppFacade.getInstance().setSelectedSong(SongList.getInstance().getSong(UUID.fromString("9b362387-2cb1-4f45-aad4-b3d672095c48")));
        App.setRoot("SongScreen");
    }

}

package com.musicapp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.model.MusicPlayer;
import com.model.OracleMusicAppFacade;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.paint.Color;

public class PlayInstrumentController {

    @FXML
    private Button exit;

    @FXML
    private GridPane guitarGrid;

    @FXML
    private Button playButton;

    @FXML
    private ToggleGroup displayButtons;

    @FXML
    private ToggleButton toInstrumentButton;

    @FXML
    private ToggleButton toMusicButton;

    @FXML
    private Slider playbackSpeedSlider;

    @FXML
    private Text songTitle;

    private OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();

    public void initialize() {
        songTitle.setText(facade.getSelectedSongTitle() + "-" + facade.getSelectedSong().getArtistName());
    }

    @FXML
    void exitButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("SongScreen");
    }
    
    @FXML
    private void toInstrumentButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("playInstrument");
    }

    @FXML
    private void toMusicButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("playMusic");
    }

    @FXML
    private void playButtonClicked(MouseEvent event) {
        ArrayList<String> tabs = facade.getSongString();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            int[] tabIndices = { 0, 0, 0, 0, 0, 0 };
            @Override
            public void handle(ActionEvent event) {
                clearCircles();
                for (int string = 0; string < 6; string++) {
                    String fret = tabs.get(string).substring(tabIndices[string], tabIndices[string] + 1);
                    if (!fret.equals("-")) {
                        if (tabIndices[string]+1 < tabs.get(string).length() && tabs.get(string).charAt(tabIndices[string]+1) != '-') {
                            fret = fret.concat(Character.toString(tabs.get(string).charAt(tabIndices[string]+1)));
                            tabIndices[string] += 7;
                        } else {
                            tabIndices[string] += 6;
                        }
                        Circle circle = new Circle(5, Color.LIGHTBLUE);
                        circle.setStroke(Color.BLUE);
                        circle.setStrokeWidth(1.0);
                        guitarGrid.add(circle, Integer.parseInt(fret), string);
                    } else {
                        tabIndices[string] += 6;
                    }
                }
            }
        }));
        timeline.setCycleCount(tabs.get(0).length()/6);
        timeline.play();
        clearCircles();
        facade.playSong();
    }

    private void clearCircles(){
        for (Node child : guitarGrid.getChildren()) {
            if (child instanceof Circle) {
                guitarGrid.getChildren().remove(child);
            }
        }
    }
}

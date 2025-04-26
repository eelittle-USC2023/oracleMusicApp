package com.musicapp;

import java.util.ArrayList;

import com.model.MusicPlayer;
import com.model.OracleMusicAppFacade;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
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
    private Slider playbackSpeedSlider;

    @FXML
    private Text songTitle;

    @FXML
    void exitButtonClicked(MouseEvent event) {

    }

    @FXML
    private void playButtonClicked(MouseEvent event) {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        MusicPlayer musicPlayer = new MusicPlayer(facade.getSelectedSong());
        ArrayList<String> tabs = new ArrayList<String>(); /* Once tabs is fixed, will be set here from musicPlayer */
        tabs.add("----------");
        tabs.add("0--3--5--12");
        tabs.add("0--3--5--12");
        tabs.add("----------");
        tabs.add("----------");
        tabs.add("----------");
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            int[] tabIndices = { 0, 0, 0, 0, 0, 0 };
            @Override
            public void handle(ActionEvent event) {
                guitarGrid.getChildren().clear();
                for (int string = 0; string < 6; string++) {
                    String fret = tabs.get(string).substring(tabIndices[string], tabIndices[string] + 1);
                    if (!fret.equals("-")) {
                        if (tabIndices[string]+1 < tabs.get(string).length() && tabs.get(string).charAt(tabIndices[string]+1) != '-') {
                            fret = fret.concat(Character.toString(tabs.get(string).charAt(tabIndices[string]+1)));
                            tabIndices[string] += 4;
                        } else {
                            tabIndices[string] += 3;
                        }
                        Circle circle = new Circle(5, Color.LIGHTBLUE);
                        circle.setStroke(Color.BLUE);
                        circle.setStrokeWidth(1.0);
                        guitarGrid.add(circle, Integer.parseInt(fret), string);
                    } else {
                        tabIndices[string] += 3;
                    }
                    System.out.print(fret);
                }
                System.out.println();
            }
        }));
        timeline.setCycleCount(tabs.get(0).length()/3+1);
        timeline.play();
        guitarGrid.getChildren().clear();
    }

}

package com.musicapp;

import java.io.IOException;
import com.model.Guitar;
import com.model.OracleMusicAppFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class CreateController {

    private int currentMeasure = 0;

    @FXML
    private Button Exit, Play, Left, Right;

    @FXML
    private Label ELLabel, ALabel, DLabel, GLabel, BLabel, EHLabel, measure;

    @FXML
    private MenuButton ELow, A, D, G, B, EHigh;

    @FXML
    private OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();

    @FXML 
    private TextField songName = new TextField(facade.getSelectedSongTitle());

    @FXML
    private void onExitClicked(ActionEvent event) throws IOException {
        save();
        App.setRoot("MainSongMenu");
    }

    @FXML
    private void onPlayClicked(ActionEvent event) throws IOException {
        save();
        facade.displayTabs();
        facade.playSong();
    }

    @FXML
    private void onLeftClicked(ActionEvent event) throws IOException {
        save();
        if (currentMeasure > 0) {
            currentMeasure--;
        }
        setMeasure();
    }

    @FXML
    private void onRightClicked(ActionEvent event) throws IOException {
        save();
        currentMeasure++;
        setMeasure();
    }

    @FXML
    private void lowEMenu(ActionEvent action) throws IOException {
        MenuItem item = (MenuItem) action.getSource();
        ELLabel.setText(item.getText());
    }

    @FXML
    private void aMenu(ActionEvent action) throws IOException {
        MenuItem item = (MenuItem) action.getSource();
        ALabel.setText(item.getText());
    }

    @FXML
    private void dMenu(ActionEvent action) throws IOException {
        MenuItem item = (MenuItem) action.getSource();
        DLabel.setText(item.getText());
    }

    @FXML
    private void gMenu(ActionEvent action) throws IOException {
        MenuItem item = (MenuItem) action.getSource();
        GLabel.setText(item.getText());
    }

    @FXML
    private void bMenu(ActionEvent action) throws IOException {
        MenuItem item = (MenuItem) action.getSource();
        BLabel.setText(item.getText());
    }

    @FXML
    private void highEMenu(ActionEvent action) throws IOException {
        MenuItem item = (MenuItem) action.getSource();
        EHLabel.setText(item.getText());
    }

    @FXML
    private void save() throws IOException {
        Guitar guitar = makeGuitar();
        facade.addGuitarToMeasure(currentMeasure, guitar);
        facade.setSelectedSongTitle(songName.getText());
        reset();
    }

    @FXML
    private Guitar makeGuitar() {
        Guitar guitar = new Guitar();
        guitar.setLowE(parseFretValue(ELLabel.getText()));
        guitar.setA(parseFretValue(ALabel.getText()));
        guitar.setD(parseFretValue(DLabel.getText()));
        guitar.setG(parseFretValue(GLabel.getText()));
        guitar.setB(parseFretValue(BLabel.getText()));
        guitar.setHighE(parseFretValue(EHLabel.getText()));
        return guitar;
    }

    @FXML
    private int parseFretValue(String text) {
        if (text == null || text.equals("-") || text.isEmpty()) {
            return -1; 
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -2; 
        }
    }

    @FXML
    private void setMeasure() {
        measure.setText(Integer.toString(currentMeasure));
    }

    @FXML
    private void reset()
    {
        ELLabel.setText("-");
        ALabel.setText("-");
        DLabel.setText("-");
        GLabel.setText("-");
        BLabel.setText("-");
        EHLabel.setText("-");
    }
}

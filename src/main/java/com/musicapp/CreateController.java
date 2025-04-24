
package com.musicapp;

import java.io.IOException;
    
import com.model.OracleMusicAppFacade;
    
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
    
public class CreateController {

    @FXML
    private Button Exit;

    @FXML
    private Button Play;

    @FXML
    private Button Left;

    @FXML
    private Button Right;

    @FXML
    private ScrollBar Scroll;

    @FXML
    private Label ELLabel;

    @FXML
    private Label ALabel;

    @FXML 
    private Label DLabel;

    @FXML
    private Label GLabel;

    @FXML
    private Label BLabel;

    @FXML
    private Label EHLabel;

    @FXML
    private MenuButton ELow;
    
    @FXML
    private MenuButton A;

    @FXML
    private MenuButton D;

    @FXML
    private MenuButton G;

    @FXML
    private MenuButton B;

    @FXML
    private MenuButton EHigh;


    @FXML
    private void onExitClicked(ActionEvent event) throws IOException 
    {
        App.setRoot("MainSongMenu");
    }

    @FXML
    private void onPlayClicked(ActionEvent event) throws IOException
    {
        //play current Note
    }

    @FXML
    private void onLeftClicked(ActionEvent event) throws IOException 
    {
        //Set Strings
        //moves to next measure
    }

    @FXML
    private void onRightClicked(ActionEvent event) throws IOException
    {
        //Set Strings (LowE, A,D,G,B,HighE)
        //moves to last measure
    }

    @FXML
    private void exitClicked(ActionEvent action) throws IOException 
    {
        App.setRoot("MainSongMenu");
    }
}


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
    
public class CreateController {

    int currentMeasure = 0;

    @FXML
    private Button Exit;

    @FXML
    private Button Play;

    @FXML
    private Button Left;

    @FXML
    private Button Right;

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
        save();
        App.setRoot("MainSongMenu");
    }

    @FXML
    private void onPlayClicked(ActionEvent event) throws IOException
    {
        save();
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        facade.playSong();
    }

    @FXML
    private void onLeftClicked(ActionEvent event) throws IOException 
    {
        save();
        //moves to next measure
    }

    @FXML
    private void onRightClicked(ActionEvent event) throws IOException
    {
        save();
        //moves to last measure
    }

    @FXML
    private void exitClicked(ActionEvent action) throws IOException 
    {
        App.setRoot("MainSongMenu");
    }

    @FXML
    private void lowEMenu(ActionEvent action) throws IOException
    {
        MenuItem item = (MenuItem) action.getSource();
        ELLabel.setText(item.getText());
    }

    @FXML
    private void aMenu(ActionEvent action) throws IOException
    {
        MenuItem item = (MenuItem) action.getSource();
        ALabel.setText(item.getText());
    }

    @FXML
    private void dMenu(ActionEvent action) throws IOException
    {
        MenuItem item = (MenuItem) action.getSource();
        DLabel.setText(item.getText());
    }

    @FXML
    private void gMenu(ActionEvent action) throws IOException
    {
        MenuItem item = (MenuItem) action.getSource();
        GLabel.setText(item.getText());
    }

    @FXML
    private void bMenu(ActionEvent action) throws IOException
    {
        MenuItem item = (MenuItem) action.getSource();
        BLabel.setText(item.getText());
    }

    @FXML
    private void highEMenu(ActionEvent action) throws IOException
    {
        MenuItem item = (MenuItem) action.getSource();
        EHLabel.setText(item.getText());
    }

    @FXML
    private void save()
    {
        Guitar guitar = makeGuitar();
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        facade.addGuitarToMeasure(currentMeasure, guitar);

    }
    
    @FXML
    private Guitar makeGuitar()
    {
        Guitar guitar = new Guitar();
        if(ELLabel.getText() != "-")
        {
            guitar.setLowE(Integer.parseInt(ELLabel.getText()));
        }
        else
        {
            guitar.setLowE(-1);
        }

        if(ALabel.getText() != "-")
        {
            guitar.setA(Integer.parseInt(ALabel.getText()));
        }
        else
        {
            guitar.setA(-1);
        }

        if(DLabel.getText() != "-")
        {
            guitar.setD(Integer.parseInt(DLabel.getText()));
        }
        else
        {
            guitar.setD(-1);
        }

        if(GLabel.getText() != "-")
        {
            guitar.setG(Integer.parseInt(GLabel.getText()));
        }
        else
        {
            guitar.setG(-1);
        }

        if(BLabel.getText() != "-")
        {
            guitar.setB(Integer.parseInt(BLabel.getText()));
        }
        else
        {
            guitar.setB(-1);
        }

        if(EHLabel.getText() != "-")
        {
            guitar.setHighE(Integer.parseInt(EHLabel.getText()));
        }
        else
        {
            guitar.setHighE(-1);
        }
        return guitar;
    }
}

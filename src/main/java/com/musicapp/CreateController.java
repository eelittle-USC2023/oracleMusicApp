
package com.musicapp;

import java.io.IOException;
    
import com.model.OracleMusicAppFacade;
    
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
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
    private MenuButton LowE;
    
    @FXML
    private MenuButton A;

    @FXML
    private MenuButton D;

    @FXML
    private MenuButton G;

    @FXML
    private MenuButton B;

    @FXML
    private MenuButton HighE;
}

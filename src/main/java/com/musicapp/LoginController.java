package com.musicapp;

import java.io.IOException;
import com.model.OracleMusicAppFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button createAccountButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML 
    private void createAccountButtonClicked(MouseEvent event) throws IOException{
        App.setRoot("createAccount");
    }

    @FXML
    private void loginButtonClicked(MouseEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();

        if (!facade.login(username, password)) {
           
            return;
        }

        App.setRoot("MainSongMenu");
    }

}

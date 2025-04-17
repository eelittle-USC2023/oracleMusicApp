package com.musicapp;

import java.io.IOException;

import com.model.OracleMusicAppFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private void loginButtonClicked(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();

        if (!facade.login(username, password)) {

            return;
        }

        App.setRoot("secondary");
    }

}

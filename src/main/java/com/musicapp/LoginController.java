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
            // Login failed - you might want to show an error message here
            return;
        }
        
        // Load the MainSongMenu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainSongMenu.fxml"));
        Parent root = loader.load();
        
        // Get the controller and set the username
        SongMenuController controller = loader.getController();
        controller.setUsername(username);
        
        // Get the current stage
        Stage stage = (Stage) loginButton.getScene().getWindow();
        
        // Set the new scene (using the dimensions from your MainSongMenu)
        Scene scene = new Scene(root,  375, 812);
        stage.setScene(scene);
        
        // Center the window (optional)
        stage.centerOnScreen();
    }
}
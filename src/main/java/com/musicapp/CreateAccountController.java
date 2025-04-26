package com.musicapp;

import com.model.OracleMusicAppFacade;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class CreateAccountController {
    @FXML private ToggleGroup accountTypeToggleGroup;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button createAccountButton;
    @FXML private PasswordField newPasswordField;
    @FXML private TextField newUsernameField;
    @FXML private RadioButton studentButton;
    @FXML private RadioButton teacherButton;

    @FXML
    private void createAccountButtonClicked(MouseEvent event) {
        try {
            // Validate inputs
            if (newUsernameField.getText().isEmpty() || 
                newPasswordField.getText().isEmpty()) {
                showAlert("Error", "Username and password cannot be empty");
                return;
            }

            if (!newPasswordField.getText().equals(confirmPasswordField.getText())) {
                showAlert("Error", "Passwords do not match");
                return;
            }

            if (!studentButton.isSelected() && !teacherButton.isSelected()) {
                showAlert("Error", "Please select an account type");
                return;
            }

            // Create account
            String role = studentButton.isSelected() ? "Student" : "Teacher";
            boolean success = OracleMusicAppFacade.getInstance().createAccount(
                newUsernameField.getText(),
                newPasswordField.getText(),
                role
            );

            if (success) {
                // Navigate to main menu on success
                App.setRoot("MainSongMenu");
            } else {
                showAlert("Error", "Username already exists");
            }
        } catch (IOException e) {
            showAlert("Navigation Error", "Could not load main menu: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showAlert("Error", "Account creation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package com.musicapp;

import com.model.OracleMusicAppFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class CreateAccountController {

    @FXML
    private ToggleGroup accountTypeToggleGroup;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button createAccountButton;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField newUsernameField;

    @FXML
    private RadioButton studentButton;

    @FXML
    private RadioButton teacherButton;

    @FXML
    private void createAccountButttonClicked(MouseEvent event) {
        if (newPasswordField.getText().equals(confirmPasswordField.getText())) {
            OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
            String role;
            if (studentButton.isSelected()) {
                role = "Student";
            } else if (teacherButton.isSelected()) {
                role = "Teacher";
            } else {
                return;
            }
            facade.createAccount(newUsernameField.getText(), newPasswordField.getText(), role);
        }
        else {

        }
    }

}
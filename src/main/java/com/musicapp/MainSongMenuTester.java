package com.musicapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSongMenuTester extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(MainSongMenuTester.class.getResource("/com/musicapp/MainSongMenu.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Main Song Menu");
            primaryStage.setScene(new Scene(root, 375, 812));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

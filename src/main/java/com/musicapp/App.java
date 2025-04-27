package com.musicapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static Stage primaryStage;
    private static final int WIDTH = 412;
    private static final int HEIGHT = 812;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        setRoot("login"); 
        stage.setTitle("ChordSeer");
        centerStageOnScreen();
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        if (scene == null) {
            scene = new Scene(root, WIDTH, HEIGHT);
            primaryStage.setScene(scene);
        } else {
            scene.setRoot(root);
        }
        centerStageOnScreen();
    }

    private static void centerStageOnScreen() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - WIDTH) / 2);
        primaryStage.setY((screenBounds.getHeight() - HEIGHT) / 2);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return loader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
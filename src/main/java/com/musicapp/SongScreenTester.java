package com.musicapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.model.Song;
import com.model.SongList;

public class SongScreenTester extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Corrected FXML path based on your folder structure
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/musicapp/SongScreen.fxml"));
            Parent root = loader.load();

            SongScreenController controller = loader.getController();

            Song smoke = SongList.getInstance()
                    .getSongs()
                    .stream()
                    .filter(s -> s.getTitle().equalsIgnoreCase("Smoke on the Water"))
                    .findFirst()
                    .orElse(null);

            if (smoke != null) {
                controller.setSong(smoke);
            } else {
                System.out.println("Smoke on the Water not found.");
                return;
            }

            primaryStage.setTitle("Song: " + smoke.getTitle());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

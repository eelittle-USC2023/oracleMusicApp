package com.musicapp;

import com.model.OracleMusicAppFacade;
import com.model.Song;
import com.model.SongList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongMenuController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField searchField;
    @FXML
    private Button addSavedSongButton;
    @FXML
    private Button createSongButton;

    @FXML
    private HBox savedSongsHBox;
    @FXML
    private HBox suggestedSongsHBox;
    @FXML
    private HBox popularSongsHBox;
    @FXML
    private HBox newSongsHBox;

    private OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();

    @FXML
    public void initialize() {

        if (facade.getCurrentAccount() != null) {
            welcomeLabel.setText("Welcome, " + facade.getCurrentAccount().getUsername() + "!");
        }

        loadSongSections();
    }

    private void loadSongSections() {
        List<Song> allSongs = SongList.getInstance().getSongs();

        loadSongSection(savedSongsHBox, getSavedSongs(allSongs));
        loadSongSection(suggestedSongsHBox, getSuggestedSongs(allSongs));
        loadSongSection(popularSongsHBox, getPopularSongs(allSongs));
        loadSongSection(newSongsHBox, getNewSongs(allSongs));
    }

    private void loadSongSection(HBox container, List<Song> songs) {
        container.getChildren().clear();
        for (Song song : songs) {
            ImageView imageView = createSongImageView(song);
            container.getChildren().add(imageView);
        }
    }

    private ImageView createSongImageView(Song song) {
        try {
            String imagePath = "/com/musicapp/images/" + song.getTitle().toLowerCase().replaceAll(" ", "_") + ".jpg";
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(image);
            
           
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
            
            
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0);");
            
            
            imageView.setOnMouseEntered(e -> 
                imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.5), 10, 0, 0, 0);")
            );
            imageView.setOnMouseExited(e -> 
                imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0);")
            );
            
            
            imageView.setOnMouseClicked(e -> {
            try {openSongScreen(song);
            } catch (Exception exception) { 
                exception.printStackTrace();
            }});
            
            return imageView;
        } catch (Exception e) {
            System.err.println("Error loading image for: " + song.getTitle());
            return createPlaceholderImageView();
        }
    }

    private void openSongScreen(Song song) throws IOException {
        OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
        facade.setSelectedSong(song);
        App.setRoot("SongScreen");
    }

    private ImageView createPlaceholderImageView() {
        ImageView placeholder = new ImageView();
        placeholder.setFitWidth(100);
        placeholder.setFitHeight(100);
        placeholder.setStyle("-fx-background-color: #702c2c; -fx-border-color: white;");
        return placeholder;
    }

    private List<Song> getSavedSongs(List<Song> allSongs) {
        if (facade.getCurrentAccount() != null) {
            return allSongs.stream()
                    .filter(song -> song.getArtistName().equalsIgnoreCase(facade.getCurrentAccount().getUsername()))
                    .limit(3)
                    .collect(Collectors.toList());
        }
        return allSongs.subList(0, Math.min(3, allSongs.size()));
    }

    private List<Song> getSuggestedSongs(List<Song> allSongs) {
        return allSongs.stream()
                .filter(song -> !"None".equalsIgnoreCase(song.getGenre()))
                .limit(3)
                .collect(Collectors.toList());
    }

    private List<Song> getPopularSongs(List<Song> allSongs) {
        return allSongs.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    private List<Song> getNewSongs(List<Song> allSongs) {
        return allSongs.stream()
                .skip(Math.max(0, allSongs.size() - 3))
                .collect(Collectors.toList());
    }

    @FXML
    private void handleSearchInput() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            showAlert("Search Error", "Please enter a search term");
            return;
        }

        ArrayList<Song> titleResults = facade.songSearch("Title", query);
        ArrayList<Song> artistResults = facade.songSearch("Artist", query);
        ArrayList<Song> allResults = new ArrayList<>(titleResults);
        allResults.addAll(artistResults);

        if (allResults.isEmpty()) {
            showAlert("No Results", "No songs found matching: " + query);
        } else {

            loadSongSection(savedSongsHBox, allResults);
        }
    }

    @FXML
    private void handleAddSavedSong() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Song");
        dialog.setHeaderText("Enter song name to save");

        dialog.showAndWait().ifPresent(songName -> {
            facade.createNewSong(songName);
            loadSongSections(); // Refresh all sections
        });
    }

    @FXML
    private void handleCreateSong() {
        try {
            facade.createNewSong("");
            App.setRoot("create");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load creation screen: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
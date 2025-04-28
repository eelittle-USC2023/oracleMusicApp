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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Controller class for the Main Song Menu screen
 * Handles showing sections of songs, searching, creating a new song and logging out
 * @author James Lyles
 */
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
    private Button logoutButton;

    @FXML
    private HBox savedSongsHBox;

    @FXML
    private Label savedSongsText;
    @FXML
    private HBox suggestedSongsHBox;
    @FXML
    private HBox popularSongsHBox;
    @FXML
    private HBox newSongsHBox;

    private OracleMusicAppFacade facade = OracleMusicAppFacade.getInstance();
/**
 * Initializes the controller when the view is loaded
 * Sets the welcome text and loads the song sections
 */
    @FXML
    public void initialize() {
        
        savedSongsText.setText("Saved Songs:");
        if (facade.getCurrentAccount() != null) {
            welcomeLabel.setText("Welcome, " + facade.getCurrentAccount().getUsername() + "!");
        }

        loadSongSections();

    }
/**
 * Loads all song sections such as: Saved, Suggested,Popular, and New Songs.
 */
    private void loadSongSections() {
        List<Song> allSongs = SongList.getInstance().getSongs();

        loadSongSection(savedSongsHBox, getSavedSongs(allSongs));
        loadSongSection(suggestedSongsHBox, getSuggestedSongs(allSongs));
        loadSongSection(popularSongsHBox, getPopularSongs(allSongs));
        loadSongSection(newSongsHBox, getNewSongs(allSongs));
    }
/**
 * Loads a specific section with songs represented as Imageview
 * @param container HBox to load images into
 * @param songs List of songs to display
 */
    private void loadSongSection(HBox container, List<Song> songs) {
        container.getChildren().clear();
        for (Song song : songs) {
            ImageView imageView = createSongImageView(song);
            container.getChildren().add(imageView);
        }
    }
/**
 * Creates an ImageView for a given song 
 * Loads a specific album image or the default if unavailable 
 * @param song the song to create an image for 
 * @return ImageView representing the song 
 */
    private ImageView createSongImageView(Song song) {
        try {
            String imagePath = "/com/musicapp/images/" + song.getTitle().toLowerCase().replaceAll(" ", "_") + ".jpg";
            Image image = null;
            if (getClass().getResourceAsStream(imagePath) != null) {
                image = new Image(getClass().getResourceAsStream(imagePath));
            } else if (getClass().getResourceAsStream("/com/musicapp/images/default_album_cover.png") != null) {
                image = new Image(getClass().getResourceAsStream("/com/musicapp/images/default_album_cover.png"));
            }
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
            try {
                openSongScreen(song);
            } catch (Exception exception) { 
                exception.printStackTrace();
            }});
            
            return imageView;
        } catch (Exception e) {
            System.err.println("Error loading image for: " + song.getTitle());
            return createPlaceholderImageView();
        }
    }
/**
 * Opens the detailed Song screen for a selected song 
 * @param song the song to display 
 * @throws IOException if the screen cannot to be loaded
 */
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
/**
 * Gets the list of songs saved by the current user 
 * @param allSongs all available songs 
 * @return List of saved songs 
 */
    private List<Song> getSavedSongs(List<Song> allSongs) {
        if (facade.getCurrentAccount() != null) {
            return allSongs.stream()
                    .filter(song -> song.getArtistName().equalsIgnoreCase(facade.getCurrentAccount().getUsername()))
                    .limit(3)
                    .collect(Collectors.toList());
        }
        return allSongs.subList(0, Math.min(3, allSongs.size()));
    }
/**
 * Gets list of suggested songs based on what the user listened to 
 * @param allSongs all available songs 
 * @return List of psuggested songs 
 */
    private List<Song> getSuggestedSongs(List<Song> allSongs) {
        return allSongs.stream()
                .filter(song -> !"None".equalsIgnoreCase(song.getGenre()))
                .limit(3)
                .collect(Collectors.toList());
    }
/**
 * Gets a lust of popular song (but only show the first three in the list)
 * @param allSongs all available songs 
 * @return List of popular songs 
 */
    private List<Song> getPopularSongs(List<Song> allSongs) {
        return allSongs.stream()
                .limit(3)
                .collect(Collectors.toList());
    }
/**
 * Gets the three of the newest songs 
 * @param allSongs all available songs 
 * @return List of new songs 
 */
    private List<Song> getNewSongs(List<Song> allSongs) {
        return allSongs.stream()
                .skip(Math.max(0, allSongs.size() - 3))
                .collect(Collectors.toList());
    }
/**
 * Handles the search input and displays matching songs 
 */
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
            savedSongsText.setText("Search Results:");
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
            loadSongSections(); 
        });
    }
/**
 * Handles the "Create a song" button 
 * Moves the user to the create a song screen
 */
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
/**
 * Displays an alert popup with specified title and message
 * @param title the title of the alert 
 * @param message the message of the alert
 */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Handles the logout button click
     * logs the user out and return to the login screen 
     * @param event Mouse event when logout is clicked
     * @throws IOException if the login screen cannot be loaded 
     */
    @FXML
    void logoutButtonClicked(MouseEvent event) throws IOException{
        facade.logout();
        App.setRoot("login");
    }
}
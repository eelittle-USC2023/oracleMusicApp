package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class SongList 
{
    private ArrayList<Song> songs;
    private static SongList instance;

    /**
     * Private constructor used when getInstance is called for the first time. Gets the songs from the DataLoader.
     * @author Ethan Little
     */
    private SongList() {
        songs = DataLoader.getSongs();
    }
    /**
     * GetInstance method to maintain Singleton design. 
     * Constructs the instance of SongList if it does not already exist, then returns the instance.
     * @return The current instance of SongList.
     * @author Ethan Little
     */
    public static SongList getInstance() {
       if (instance == null){
        instance = new SongList();
       }
       return instance;
    }

    public ArrayList<Song> getSongs() {
       return songs;
    }

    /**
     * Finds and returns the song with the id that matches the parameter.
     * @param id ID of the song that is being searched for.
     * @return The song with the ID that matches the param. If there is no song with a matching ID, returns null.
     * @author Ethan Little
     */
    public Song getSong(UUID id){
        for (Song song : songs){
            if(song.getID().equals(id)){
                return song;
            }
        }
        return null;
    }
    /**
     * 
     * @param title
     * @param artistName
     * @return
     * @author Ethan Little
     */
    public Song addSong(String title, String artistName) {
        if (title == null || title.isEmpty()) {
            return null;
        }
        Song song = new Song(title, artistName);
        songs.add(song);
        return song;
    }

    public boolean editSong(Song song) {
        return true;
    }

    public boolean deleteSong(Song song) {
        return songs.remove(song);

    }

    public ArrayList<Song> getSongsByUser(String username) {
        ArrayList<Song> userSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtistName() != null && song.getArtistName().equalsIgnoreCase(username)) {
                userSongs.add(song);
            }
        }
        return userSongs;
    }
    /**
     * Used to find any songs that match the search in the given field.
     * @param field Gives which field the search is to use.
     * @param search What the user is attempting to search using.
     * @return Any songs that match the search in the given field. If none are found, returns an empty list.
     * @author Ethan Little
     */
    public ArrayList<Song> searchSongs(String field, String search) {
        ArrayList<Song> songsFound = new ArrayList<Song>();
        if (field.equals("Artist")) {
            for (Song s : songs) {
                if (s.getArtistName().contains(search)) {
                    songsFound.add(s);
                }
            }

        } else if (field.equals("Title")) {
            for (Song s : songs) {
                if (s.getTitle().contains(search)) {
                    songsFound.add(s);
                }
            }
        }
        return songsFound;
    }
    public void save(){
        DataWriter.savedSongs(this);
    }
}

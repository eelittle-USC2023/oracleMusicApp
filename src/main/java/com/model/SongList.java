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

    public boolean addSong(String title, String artistName, String difficulty, String genre, Instrument instrument, ArrayList<Measure> measures) {
        return true;
    }

    public boolean editSong(Song song) {
        return true;
    }

    public boolean deleteSong(Song song) {
        return songs.remove(song);

    }
    public void save() {

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

}

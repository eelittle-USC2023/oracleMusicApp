package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class SongList 
{
    private ArrayList<Song> songs;
    private static SongList instance;

    private SongList() {
        songs = new ArrayList<>();

    }

    public static SongList getInstance() {
       if (instance == null){
        instance = new SongList();
       }
       return instance;
    }

    public ArrayList<Song> getSongs() {
       return songs;
    }

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

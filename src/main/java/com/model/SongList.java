package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class SongList 
{
    private ArrayList<Song> songs;
    private static SongList instance;

    private SongList()
    {
        songs = new ArrayList<>();

    }
    public static SongList getInstance()
    {
       if (instance == null){
        instance = new SongList();
       }
       return instance;
    }
    public ArrayList<Song> getSong()
    {
       return songs;
    }
    public Song getSong( String title){
        for (Song song : songs){
            if(song.getTitle().equalsIgnoreCase(title)){
                return song;
            }
        }
        return null;
    }
    public Song getSong(UUID id)
    {
        return new Song(id, null, null, null, null, null, null);
    }
    public boolean addSong(String title, String artistName, String difficulty, String genre, Instrument instrument, ArrayList<Measure> measures)
    {
        return true;
    }
    public boolean editSong(Song song)
    {
        return true;
    }
    public boolean deleteSong(Song song)
    {
        return songs.remove(song);

    }
    public void save()
    {

    }

}

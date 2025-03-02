package com.model;
import java.util.ArrayList;

public class SongList 
{
    private ArrayList<Song> songs;
    private SongList songList;

    private SongList()
    {

    }
    public static SongList getInstance()
    {
        SongList temp = new SongList();
        return temp;
    }
    public Song getSong()
    {
        Song temp = new Song();
        return temp;
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

    }
    public void save()
    {
        
    }

}

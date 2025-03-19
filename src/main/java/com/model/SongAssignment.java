package com.model;
import java.util.ArrayList;

public class SongAssignment extends Assignment
{
    private String difficultyLevel;
    private ArrayList<Song> recommendedSongs;
    private Song song;

    public SongAssignment(Song song) {
        this.song = song;
        recommendedSongs = new ArrayList<Song>();
        difficultyLevel = "";
    }

    public void completeAssignment()
    {
        
    }
}

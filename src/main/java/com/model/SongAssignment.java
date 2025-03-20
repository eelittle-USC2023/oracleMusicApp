package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class SongAssignment extends Assignment
{
    private Song song;
    private String difficultyLevel;
    private ArrayList<Song> recommendedSongs;
    


    public SongAssignment(String title, String description, boolean complete, Song song, String difficultyLevel, ArrayList<Song> recommendedSongs) {
        super(title, description, complete);
        this.song = song;
        this.difficultyLevel = difficultyLevel;
        this.recommendedSongs = recommendedSongs;

    }
    public void completeAssignment()
    {
        
    }
    public UUID getSongID(){
        return song.getID();
    }
    public String getType(){
        return "Song";
    }
}

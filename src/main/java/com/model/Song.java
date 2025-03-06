package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Song {
    private UUID uuid;
    private String title;
    private String artistName;
    private String difficulty;
    private String genre;
    private Instrument instrument;
    private ArrayList<Measure> measures;

    public Song(String title, String artistName, String difficulty, String genre, Instrument instrument, ArrayList<Measure> measures) {
        this.title = title;
        this.artistName = artistName;
        this.difficulty = difficulty;
        this.genre = genre;
        this.instrument = instrument;
        this.measures = measures;
    }
    public Song(UUID uuid, String title, String artistName, String difficulty, String genre, Instrument instrument, ArrayList<Measure> measures) {
        this.uuid = uuid;
        this.title = title;
        this.artistName = artistName;
        this.difficulty = difficulty;
        this.genre = genre;
        this.instrument = instrument;
        this.measures = measures;
    }
    public String getTitle(){
        return title;
    }
    public String getArtistName(){
        return artistName;
    }
    public String getDifficulty(){
        return difficulty;
    }
    public String getGenre(){
        return genre;
    }
    
@Override
    public String toString() {
        return title;
    }
}
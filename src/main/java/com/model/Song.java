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
    private int tempo;

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

    public String toJFugueString()
    {
        String jFugueInput = " ";
        for(int i = 0; i < measures.size(); i++)
        {
            String input = measures.get(i).toJFugueString();
            jFugueInput = jFugueInput + input;

        }
        return jFugueInput;
    }

    public UUID getID() {
        return uuid;
    }

    public Instrument getInstrument() {
        return instrument;
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
    public void addMeasure(Measure M)
    {
        measures.add(M);
    }
    public void removeMeasure(Measure M)
    {
        measures.remove(M);
    }    
@Override
    public String toString() {
        return title;
    }
    public ArrayList<Measure> getSong()
    {
        return measures;
    }
}
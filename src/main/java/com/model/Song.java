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
    private int tempo;
    private ArrayList<Measure> measures;

    public Song(String title, String artistName, String difficulty, String genre, Instrument instrument, int tempo, ArrayList<Measure> measures) {
        uuid = UUID.randomUUID();
        this.title = title;
        this.artistName = artistName;
        this.difficulty = difficulty;
        this.genre = genre;
        this.instrument = instrument;
        this.tempo = tempo;
        this.measures = measures;
    }
    /**
     * Song constructor for use in DataLoader. All data members are loaded in from the JSON file, so simply sets them.
     * @param uuid ID for the song
     * @param title Title of the song
     * @param artistName name of the artist for the song, whether that be a username or real-world Artist/Band
     * @param difficulty difficulty level of the song
     * @param genre genre of the song
     * @param instrument instrument that the song is to be played in
     * @param tempo tempo for the song, in beats per minute 
     * @param measures measures for the song.
     * @author Ethan Little
     */
    public Song(UUID uuid, String title, String artistName, String difficulty, String genre, Instrument instrument, int tempo, ArrayList<Measure> measures) {
        this.uuid = uuid;
        this.title = title;
        this.artistName = artistName;
        this.difficulty = difficulty;
        this.genre = genre;
        this.instrument = instrument;
        this.tempo = tempo;
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
    
    /**
     * Gives song in a String, text-based format. Does not use musical notation.
     * @return String displaying information about the song, then the song itself, delineating each measure. Ex: ""
     * @author Ethan Little
     */
    @Override
    public String toString() {
        String ret = "ID: " + uuid + " Title: " + title + " Artist: " + artistName + " Difficulty: " + difficulty + "\nGenre: " + genre + " Instrument: " + instrument.toString() + "\nTempo: " + tempo + " bpm Measures:\n| ";
        for (Measure m : measures) {
            ret = ret + m.toString() + " | ";
        }
        return ret;
    }
    public ArrayList<Measure> getSong()
    {
        return measures;
    }
}
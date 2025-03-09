package com.model;
import java.util.ArrayList;

/**
 * Organizes an array of measures and establishes the song's title, artist, difficulty, genre, and instrument
 * @author Ally Blackwell
 */
public class Song 
{
    private String title;
    private String artistName;
    private String difficulty;
    private String genre;
    private Instrument instrument;
    private ArrayList<Measure> song;

    /**
     * Defines the current instance of song's title, instrument, author, difficulty, genre, and establishes an array list of measures which hold the notes
     * @param t the title of the current song
     * @param i the instrument of the current song
     * @param aN the name of the artist of the current song
     * @param d the difficulty of the current song
     * @param g the genre of the current song
     */
    public Song(String t, Instrument i, String aN, String d, String g)
    {
        this.title = t;
        this.instrument = i;
        this.artistName = aN;
        this.difficulty = d;
        this.genre = g;
        this.song = new ArrayList<Measure>();
    }
    /**
     * Adds a measure to the current song
     * @param m an inputted measure of the song to be added
     */
    public void addMeasure(Measure m)
    {
        song.add(m);
    }
    /**
     * Remove a measure to the current song
     * @param m an inputted measure to be removed
     */
    public void removeMeasure(Measure m)
    {
        song.remove(m);
    }
}

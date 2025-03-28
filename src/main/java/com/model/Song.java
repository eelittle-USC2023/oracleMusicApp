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

    /**
     * Constructor used in the facade for when a user makes a new song.
     * @param title Title passed by the user for their new song.
     * @param artistName Artist for the song, in this case will be the username of the user who made the song.
     * @author Ethan Little
     */
    public Song(String title, String artistName) {
        uuid = UUID.randomUUID();
        this.title = title;
        this.artistName = artistName;
        this.difficulty = "None";
        this.genre = "None";
        this.instrument = new Guitar();
        this.tempo = 100;
        this.measures = new ArrayList<Measure>();
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
    /**
     * Takes the JFugue strings of each measure and places them together to create a string of the entire song
     * @return the JFugue string of the song
     * @author Ally Blackwell
     */
    public String toJFugueString()
    {
        StringBuilder jFugueInput = new StringBuilder();
        for(int i = 0; i < measures.size(); i++)
        {
            String input = measures.get(i).toJFugueString();
            jFugueInput.append(input);

        }
        return jFugueInput.toString();
    }
    /**
     * Gets the UUID for the Song
     * @return the song's UUID
     * @author Ethan Little
     */
    public UUID getID() {
        return uuid;
    }
    /**
     * Gets the Instrument of the song
     * @return the instrument
     * @author Ethan Little
     */
    public Instrument getInstrument() {
        return instrument;
    }
    /**
     * Gets the title of the song
     * @return the songs title
     * @author Ethan Little
     */
    public String getTitle(){
        return title;
    }
    /**
     * Gets the name of the artist of the song
     * @return the Artist's name
     * @author Ethan Little
     */
    public String getArtistName(){
        return artistName;
    }
    /**
     * Gets the difficulty of the song
     * @return the songs difficulty
     * @author Ethan Little
     */
    public String getDifficulty(){
        return difficulty;
    }
    /**
     * Gets the Genre of the song
     * @return the song's genre
     * @author Ethan Little
     */
    public String getGenre(){
        return genre;
    }
    /**
     * Returns the measure at the passed index.
     * @param measureIndex
     * @return the measure at the given index.
     * @author Ethan Little
     */
    public Measure getMeasure(int measureIndex) {
        return measures.get(measureIndex);
    }
    /**
     * Adds the inputted note to the inputted measure
     * @param measureIndex the index of the desired measure
     * @param noteName name of the created note
     * @param octave octave of the created note
     * @param length length of the created note
     * @param position position of the created note
     * @author Ethan Little
     */
    public void addNoteToMeasure(int measureIndex, String noteName, int octave, double length, double position) {
        measures.get(measureIndex).addNote(noteName, octave, length, position);
    }
    /**
     * Creates and adds a measure to this instance of song
     * @param timeSignatureTop the top of the measure's time signature
     * @param timeSignatureBottom the bottom of the measure's time signature
     * @param keySignature the key signature of the measure
     * @author Ally Blackwell
     */
    public void addMeasure(int timeSignatureTop, int timeSignatureBottom, String keySignature)
    {
        measures.add(new Measure(timeSignatureTop, timeSignatureBottom, keySignature, new ArrayList<Note>()));
    }
    /**
     * Removes the inputted measure from the song
     * @param M the measure that should be deleted
     * @author Ally Blackwell
     */
    public void removeMeasure(Measure M)
    {
        measures.remove(M);
    }  
    /**
     * Gets the measures of the current song
     * @return the arraylist of measures that makes up this song
     * @author Ally Blackwell
     */
    public ArrayList<Measure> getMeasures()
    {
        return measures;
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
}
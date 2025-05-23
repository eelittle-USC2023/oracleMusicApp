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
    private int createPosition;

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
        this.createPosition = 0;
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
            jFugueInput.append(input + "|");

        }
        return jFugueInput.toString();
    }
    /**
     * Returns the tempo in a JFugue format
     * @return Tempo in jfugue format
     * @author Ethan Little
     */
    public String tempoToJFugue() {
        return "T"+tempo;
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
     * Sets the title of the song to the input
     * @param t the title passed through
     * @author Ally Blackwell
     */
    public void setTitle(String t)
    {
        this.title = t;
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
        if (measureIndex >= 0 && measureIndex < measures.size()) {
            measures.get(measureIndex).addNote(noteName, octave, length, position);
        } else {
            System.out.println("Invalid measureIndex: " + measureIndex);
        }
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
    /**
     * Adds a 'strum' of the guitar to the song at the indicated measure, overwriting if necessary
     * @param index the current measure that the strum should be placed in
     * @param guitar the strum
     * @author Ally Blackwell
     */
    public void addTabToMeasure(int index, Guitar guitar) {
        if (measures.size() <= index) 
        { 
            addMeasure(4, 4, "Cmaj");
        }
        Measure createMeasure = new Measure(4,4,"CMaj", new ArrayList<Note>());
        String[] strings = {"LE", "A", "D", "G", "B", "HE"};
        int octave = 4;
        for (String string : strings) {

            String name = (guitar.getStringTab(string) == -1) ? "R" : tabToNoteName(string, guitar.getStringTab(string));
            if(name != "R")
            {
                octave = tabToNoteOctave(string, guitar.getStringTab(string)); 
            }
            createMeasure.addNote(name, octave, 1, createPosition);
        }
        measures.set(index, createMeasure);
        createPosition++;
    }
    
    /**
     * An array to allow easier assignment of note names
     * @author Ally Blackwell
     */
    private static final String[] NOTES = 
        {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    /**
     * Takes an input of the guitar string and the tab on that string and returns the note name in a way that can be processed by JFugue
     * @param string the string of the guitar the note lies on
     * @param tab the tab on that string
     * @return the name of the note
     * @author Ally Blackwell
     */
    private String tabToNoteName(String string, int tab) {
        int baseNoteIndex;
        switch (string) {
            case "LE": baseNoteIndex = 4; break;
            case "A":  baseNoteIndex = 9; break;
            case "D":  baseNoteIndex = 2; break;
            case "G":  baseNoteIndex = 7; break;
            case "B":  baseNoteIndex = 11; break;
            case "HE": baseNoteIndex = 4; break;
            default: return "Invalid string";
        }
    
        int noteIndex = (baseNoteIndex + tab + 12) % 12;
        return NOTES[noteIndex];
    }
    /**
     * Takes an input of the string of the guitar and the tab on that string and returns the octave that note is played in
     * @param string the string of the note
     * @param tab the tab on that string
     * @return the octave of the note
     * @author Ally Blackwell
     */
    private int tabToNoteOctave(String string, int tab) {
        int baseOctave;

        switch (string) {
            case "LE": baseOctave = 2; break;
            case "A":  baseOctave = 2; break;
            case "D":  baseOctave = 3; break;
            case "G":  baseOctave = 3; break;
            case "B":  baseOctave = 3; break;
            case "HE": baseOctave = 4; break;
            default: return -1;
        }

        return baseOctave + (tab / 12);
    }

    // Added as test for SongScreen.fxml implementation...
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
}
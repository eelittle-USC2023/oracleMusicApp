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
    private Guitar guitar;
    private ArrayList<Measure> measures;
    private ArrayList<String> LowE;
    private ArrayList<String> A;
    private ArrayList<String> D;
    private ArrayList<String> G;
    private ArrayList<String> B;
    private ArrayList<String> HighE;
    private ArrayList<ArrayList<String>> tabs;

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
        this.LowE = new ArrayList<>();
        this.A = new ArrayList<>();
        this.D = new ArrayList<>();
        this.G = new ArrayList<>();
        this.B = new ArrayList<>();
        this.HighE = new ArrayList<>();
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
    /**
     * Returns the measure at the passed index.
     * @param measureIndex
     * @return the measure at the given index.
     * @author Ethan Little
     */
    public Measure getMeasure(int measureIndex) {
        return measures.get(measureIndex);
    }
    public void addNote(int measureIndex, String noteName, int octave, double length, double position) {
        measures.get(measureIndex).addNote(noteName, octave, length, position);
    }
    public void addMeasure(int timeSignatureTop, int timeSignatureBottom, String keySignature)
    {
        measures.add(new Measure(timeSignatureTop, timeSignatureBottom, keySignature, new ArrayList<Note>()));
    }
    public void removeMeasure(Measure M)
    {
        measures.remove(M);
    }  
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
    public ArrayList<Measure> getSong()
    {
        return measures;
    }

    public ArrayList<ArrayList<String>> toTab()
    {
        ArrayList<Measure> measures = this.getSong();

        for(int i = 0; i < measures.size(); i++)
        {
            ArrayList<Note> notes = measures.get(i).getNotes();
            for(int j = 0; j < notes.size(); j++)
            {
                int noteNumber = notes.get(j).noteToJFugue();
                String string = stringRange(noteNumber);
                switch(string)
                {
                    case "LowE": LowE.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "A": A.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "D": D.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "G": G.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "B": B.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "HighE": HighE.add(Integer.toString(getFret(noteNumber, string)));  break;
                }
                if(j+1 < notes.size() && notes.get(i).getPosition() != notes.get(i+1).getPosition()) 
                {
                    addAndResetFretboard();
                }  
            }
        }
        populateTabs();
        return tabs;
    }

    private void populateTabs()
    {
        tabs = new ArrayList<ArrayList<String>>();
        this.tabs.add(LowE);
        this.tabs.add(A);
        this.tabs.add(D);
        this.tabs.add(G);
        this.tabs.add(B);
        this.tabs.add(HighE);
    }

    private void addAndResetFretboard()
    {
        this.LowE.add(Integer.toString(guitar.getLowE()));
        this.A.add(Integer.toString(guitar.getA()));
        this.D.add(Integer.toString(guitar.getD()));
        this.G.add(Integer.toString(guitar.getG()));
        this.B.add(Integer.toString(guitar.getB()));
        this.HighE.add(Integer.toString(guitar.getHighE()));

        guitar.setLowE(-1);
        guitar.setA(-1);
        guitar.setD(-1);
        guitar.setG(-1);
        guitar.setB(-1);
        guitar.setHighE(-1);
    }

    private int getFret(int nN, String s)
    {
        int stringNumber;
        switch (s) 
        {
            case "LowE": stringNumber = 28; break;
            case "A": stringNumber = 33; break;
            case "D": stringNumber = 38; break;
            case "G": stringNumber = 43; break;
            case "B": stringNumber = 47; break;
            case "HighE": stringNumber = 52; break;
            default:
                stringNumber = -1;
        }
        return nN - stringNumber;
    }

    private String stringRange(int nN)
    {
        if(nN > 27 && nN < 41 && guitar.getLowE() == -1)
        {
            return "LowE";
        }
        if (nN > 34 && nN < 46 && guitar.getA() == -1)
        {
            return "A";
        }
        if (nN > 37 && nN < 51 && guitar.getD() == -1)
        {
            return "D";
        }
        if (nN > 42 && nN < 56 && guitar.getG() == -1)
        {
            return "G";
        }
        if (nN > 46 && nN < 60 && guitar.getB() == -1)
        {
            return "B";
        }
        if (nN > 51 && nN < 65 && guitar.getHighE() == -1)
        {
            return "HighE";
        }
        return null;
    }
}
package com.model;
import java.util.ArrayList;

/**
 * Establishes a measure of the song and allows the addition and removal of notes
 * @author Ally Blackwell
 */
public class Measure 
{
    private int timeSignatureTop;
    private int timeSignatureBottom;
    private String keySigniture;
    private ArrayList<Note> measure;

    public Measure(int tSP, int tSB, String kS)
    {
        this.timeSignatureTop = tSP;
        this.timeSignatureBottom = tSB;
        this.keySigniture = kS;
        this.measure = new ArrayList();
    }
    public void addNote(Note note)
    {
        measure.add(note);
    }
    public void removeNote(Note note)
    {
        measure.remove(note);
    }
}

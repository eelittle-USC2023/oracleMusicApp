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
    private String keySignature;
    private ArrayList<Note> notes;

    public Measure(int timeSignatureTop, int timeSignatureBottom, String keySig, ArrayList<Note> notes)
    {
        this.timeSignatureBottom = timeSignatureBottom;
        this.timeSignatureTop = timeSignatureTop;
        this.keySignature = keySig;
        this.notes = notes;
    }
    public void addNote()
    {

    }
    public void addNote(Note note)
    {
        notes.add(note);
    }
    public void removeNote(Note note)
    {
        notes.remove(note);
    }
}

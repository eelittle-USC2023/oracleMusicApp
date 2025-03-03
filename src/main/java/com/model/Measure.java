package com.model;
import java.util.ArrayList;

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
        
    }

}

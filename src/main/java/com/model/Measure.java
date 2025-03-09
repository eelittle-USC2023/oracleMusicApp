package com.model;
import java.util.ArrayList;

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

}

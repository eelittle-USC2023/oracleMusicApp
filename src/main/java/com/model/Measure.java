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


    public Measure(int timeSignatureTop, int timeSignatureBottom, String keySig, ArrayList<Note> note)
    {
        this.timeSignatureBottom = timeSignatureBottom;
        this.timeSignatureTop = timeSignatureTop;
        this.keySignature = keySig;
        this.notes = note;
    }
    public void addNote(Note note)
    {
        notes.add(note);
    }
    public void removeNote(Note note)
    {
        notes.remove(note);
    }
    public ArrayList<Note> getNotes()
    {
        return notes;
    }
    public String toJFugueString()
    {
        String jFugueInput = " ";
        int noteNumber;
        for(int i = 0; i < notes.size(); i++)
        {
            noteNumber = notes.get(i).noteToJFugue();
            String next = " ";
            if(i+1 < notes.size()) 
            {
                if (notes.get(i).getPosition() == notes.get(i+1).getPosition())
                {
                    next = "+";
                }
            }
            String input = Integer.toString(noteNumber) +"/" + notes.get(i).getLength() + next;
            jFugueInput = jFugueInput + input;
        }
        System.out.println(jFugueInput);
        return jFugueInput;
    }
}

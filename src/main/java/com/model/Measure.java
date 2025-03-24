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
<<<<<<< HEAD

    /**
     * Gives the measure's characteristics in a String, text-based format. Does not use musical notation.
     * @return String of the measure in the following format "Key: C, Time: 4/4, Notes: [...]"
     * @author Ethan Little
     */
    @Override
    public String toString() {
        String ret = "Key: " + keySignature + " Time: " + timeSignatureTop + "/" + timeSignatureBottom + " Notes: [";
        for (int i = 0; i < notes.size(); i++) {
            if (i == notes.size() - 1) {
                ret = ret + notes.get(i).toString() + "]";
            }
            else {
            ret = ret + notes.get(i).toString()+ ", ";
            }
        }
        return ret;
=======
    public int getTimeSignatureTop(){
        return timeSignatureTop;
    }
    public int getTimeSignatureBottom(){
        return timeSignatureBottom;
    }
    public String getKeySignature(){
        return keySignature;
>>>>>>> d1d1ed7 (Added DataConstants to the DataWriter and added getTimeSignatureTop,  getTimeSignatureBotto and getKeySignature to the measure class)
    }
}

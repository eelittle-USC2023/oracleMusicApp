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

    /**
     * Sets the time signature fraction. key signiture, and notes to this instance of the measure
     * @param timeSignatureTop the top of the fraction for time signature
     * @param timeSignatureBottom the bottom of the fraction for time signature
     * @param keySig the key signature
     * @param notes the array list of notes which makes up the measure
     * @author Ally Blackwell
     */
    public Measure(int timeSignatureTop, int timeSignatureBottom, String keySig, ArrayList<Note> notes)
    {
        this.timeSignatureBottom = timeSignatureBottom;
        this.timeSignatureTop = timeSignatureTop;
        this.keySignature = keySig;
        this.notes = notes;
    }
    /**
     * Adds a note to the Array List which makes the measure
     * @param name Note name
     * @param octave Note Octave
     * @param length Note Length
     * @param position Note Position
     * @author Ally Blackwell
     */
    public void addNote(String name, int octave, double length, double position)
    {
        notes.add(new Note(name, octave, length, position));
    }
    /**
     * Removes note from the Array List which makes the measure
     * @param note the note to be removed
     * @author Ally Blackwell
     */
    public void removeNote(Note note)
    {
        notes.remove(note);
    }
    /**
     * Gets the array of notes which make the measure
     * @return the array of notes
     */
    public ArrayList<Note> getNotes()
    {
        return notes;
    }
    /**
     * Takes the JFugue translated notes and creates a singular string which can be played on JFugue
     * Places notes of the same position to be played together
     * @return the JFugue input string
     * @author Ally Blackwell
     */
    public String toJFugueString()
    {
        if(notes.isEmpty())
        {
            return "";
        }
        StringBuilder jFugueInput = new StringBuilder(" ");
        int noteNumber;
        for(int i = 0; i < notes.size(); i++)
        {
            noteNumber = notes.get(i).noteToJFugue();
            String next = " ";

            if(noteNumber != -2)
            {
                if(i + 1 < notes.size()) 
                {
                    if (notes.get(i).getPosition() == notes.get(i + 1).getPosition())
                    {
                        next = "+";
                    }
                }
                String input = Integer.toString(noteNumber) +"/" + notes.get(i).getLength() + next;
                jFugueInput.append(input);
            }
        }
        return jFugueInput.toString();
    }

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
    }
    /**
     * Gets the top of the time signature fraction
     * @return the top of the time signature
     * @author Ethan Little
     */
    public int getTimeSignatureTop(){
        return timeSignatureTop;
    }
    /**
     * Gets the bottom of the time signature fraction
     * @return the bottom of the time signature
     * @author Ethan Little
     */
    public int getTimeSignatureBottom(){
        return timeSignatureBottom;
    }
    /**
     * Gets the key signature
     * @return the key signature
     * @author Ethan Little 
     */
    public String getKeySignature(){
        return keySignature;
    }
}
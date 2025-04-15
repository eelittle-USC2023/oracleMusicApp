package com.model;

/**
 * Intakes the Name of the Note, Octave of the Note, and the Length
 * Outputs the JFugue time signiture and the Note Number
 * @author Ally Blackwell
 */
public class Note 
{
    private String name;
    private int octave;
    private double length;
    private double position;
    private int noteNumber = 0;

    /**
     * Sets the name, octave, length numerator and length denominator to their respective values.
     * @param n Name of the Note
     * @param o Octave of the Note
     * @param l Length of the Note
     * @param pos Position of the note
     * @author Ally Blackwell
     */
    public Note(String n, int o, double l, double pos)
    {
        this.name = n.toUpperCase();
        this.octave = o;
        this.length = l;
        this.position = pos;
    }

    /**
     * Takes the note name and returns the number that JFugue uses to play that note
     * Adds one to that number if the note is sharp and subtracts one if the note is flat
     * @return the JFugue number for the note
     * @author Ally Blackwell
     */
    public int noteToJFugue()
    {
        if(this.name== null)
        {
            return -3;
        }
        String name = this.getName();
        char letter =  this.getName().charAt(0);
        char type = 'n';
        int multiply = 1;

        setJFugueBase(letter);

        if(-1 < octave && octave < 11)
        {
            multiply = this.octave * 12;
            if(noteNumber != -1 && noteNumber != -2)
            {
                noteNumber = noteNumber + multiply;
            }
        }
        else
        {
            this.noteNumber = -4;
        }
        if (name.length() > 1) 
        {
            type = this.getName().charAt(1);
        }
        if(type == '#')
        {
            noteNumber++;
        }
        else if(type == 'b')
        {
            noteNumber--;
        }
        return noteNumber;
    }
    /**
     * Establishes the base number for each JFugue Note which can then be converted by multiplying the octave*12
     * @param letter the letter for the note name
     * @author Ally Blackwell
     */
    private void setJFugueBase(char letter)
    {
        java.util.Map<Character, Integer> noteMap = new java.util.HashMap<>();
        noteMap.put('C', 0);
        noteMap.put('D', 2);
        noteMap.put('E', 4);
        noteMap.put('F', 5);
        noteMap.put('G', 7);
        noteMap.put('A', 9);
        noteMap.put('B', 11);
        noteMap.put('R', -1);
    
        noteNumber = noteMap.getOrDefault(letter, -2);
    }
    /**
     * Gets the Name of the Note
     * @return note name
     * @author Ally Blackwell
     */
    public String getName()
    {
        return name;
    }
    /**
     * Gets the Octave of the Note
     * @return note octave
     * @author Ally Blackwell
     */
    public int getOctave()
    {
        return octave;
    }
    /**
     * Gets the Length of the Note
     * @return note length
     * @author Ally Blackwell
     */
    public double getLength()
    {
        return length;
    }
    /**
     * Gets the position of the note
     * @return note position
     * @author Ally Blackwell
     */
    public double getPosition()
    {
        return position;
    }
    /**
     * Gives note characteristics in a String format. Does not use musical notation.
     * @return String of the note in the following format: "A3 Len: 1 Pos: 1"
     * @author Ethan Little
     */
    @Override
    public String toString() {
        return name + octave + " Len: " + length + " Pos: " + position;
    }
}   

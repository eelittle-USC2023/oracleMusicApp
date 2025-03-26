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
     * Sets the name, octave, length numerator and length denominator to their respective values
     * @param n Name of the Note
     * @param o Octave of the Note
     * @param l
     * @param pos
     */
    public Note(String n, int o, double l, double pos)
    {
        this.name = n;
        this.octave = o;
        this.length = l;
        this.position = pos;
    }

     /**
     * Takes the note name and returns the number that JFugue uses to play that note
     * Adds one to that number if the note is sharp and subtracts one if the note is flat
     * @return the JFugue number for the note
     */
    public int noteToJFugue()
    {
        String name = this.getName().toLowerCase();
        char letter =  this.getName().charAt(0);
        char type = 'n';

        setJFugueBase(letter);
        noteNumber = noteNumber + octave * 12;

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
    public void setJFugueBase(char letter)
    {
        java.util.Map<Character, Integer> noteMap = new java.util.HashMap<>();
        noteMap.put('c', 14);
        noteMap.put('d', 15);
        noteMap.put('e', 16);
        noteMap.put('f', 17);
        noteMap.put('g', 18);
        noteMap.put('a', 19);
        noteMap.put('b', 20);
    
        noteNumber = noteMap.getOrDefault(letter, -1);
    }
    public String getName()
    {
        return name;
    }
    public int getOctave()
    {
        return octave;
    }
    public double getLength()
    {
        return length;
    }
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

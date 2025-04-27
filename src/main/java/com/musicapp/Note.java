package com.musicapp;

/**
 * Intakes the Name of the Note, Octave of the Note, and the Length
 * Outputs the JFugue time signature and the Note Number
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
        if (n == null || n.isEmpty()) {
            throw new IllegalArgumentException("Note name cannot be null or empty.");
        }
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
        if (this.name.isEmpty())
        {
            return -1; 
        }

        String name = this.getName();
        char letter = this.getName().charAt(0);
        char type = 'n';
        int multiply = 1;

        setJFugueBase(letter);

        if (this.octave < 0 || this.octave > 10)
        {
            this.noteNumber = -1;
            return this.noteNumber;
        }
        else
        {
            multiply = this.octave * 12;
            if (this.noteNumber != -1)
            {
                this.noteNumber = this.noteNumber + multiply;
            }
        }

        if (name.length() > 1) 
        {
            type = this.getName().charAt(1);
        }
        if (type == '#')
        {
            this.noteNumber++;
        }
        else if (type == 'b')
        {
            this.noteNumber--;
        }

        return this.noteNumber;
    }

    /**
     * Establishes the base number for each JFugue Note which can then be converted by multiplying the octave * 12
     * @param letter the letter for the note name
     * @author Ally Blackwell
     */
    private void setJFugueBase(char letter)
    {
        if (letter == 'R') 
        {
            this.noteNumber = -1;
            return;
        }
        java.util.Map<Character, Integer> noteMap = new java.util.HashMap<>();
        noteMap.put('C', 0);
        noteMap.put('D', 2);
        noteMap.put('E', 4);
        noteMap.put('F', 5);
        noteMap.put('G', 7);
        noteMap.put('A', 9);
        noteMap.put('B', 11);

        this.noteNumber = noteMap.getOrDefault(letter, -1); 
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

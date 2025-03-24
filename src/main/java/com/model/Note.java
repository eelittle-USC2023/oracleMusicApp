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
        char Name =  this.getName().charAt(0);
        char type = 'n';
        if (name.length() != 1) {
            type = this.getName().charAt(1);
        }

        var noteMap = new java.util.HashMap<String, Integer>() 
        {{
            put("2e", 28); put("2f", 29); put("2g", 31);
            put("2a", 33); put("2b", 35);
            
            put("3c", 36); put("3d", 38); put("3e", 40);
            put("3f", 41); put("3g", 43); put("3a", 45);
            put("3b", 47);
            
            put("4c", 48); put("4d", 50); put("4e", 52);
            put("4f", 53); put("4g", 55); put("4a", 57);
            put("4b", 59);
            
            put("5c", 60); put("5d", 62); put("5e", 64);
        }};

        String map = this.getOctave() + String.valueOf(Character.toLowerCase(Name));
        noteNumber = noteMap.getOrDefault(map, -1);

        if(noteNumber == -1)
        {
            return -1;
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

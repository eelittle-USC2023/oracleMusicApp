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
    private int lengthNum;
    private int lengthDenom;

    /**
     * Sets the name, octave, length numerator and length denominator to their respective values
     * @param n Name of the Note
     * @param o Octave of the Note
     * @param lN The Numerator for the length of the note
     * @param lD The Denominator for the length of the note
     */
    public Note(String n, int o, int lN, int lD)
    {
        this.name = n;
        this.octave = o;
        this.lengthNum = lN;
        this.lengthDenom = lD;
    }
    /**
     * Divides the numerator by the denominator and returns the note length in JFugue format
     * @return the JFugue format of the note length to be added after the note number
     */
    public String timeToJFugue()
    {
        double duration = (lengthNum / lengthDenom);
        return "/"+duration;
    }
    /**
     * Takes the note name and returns the number that JFugue uses to play that note
     * Adds one to that number if the note is sharp and subtracts one if the note is flat
     * @return the JFugue number for the note
     */
    public int noteToJFugue()
    {
        int noteNumber = 0;
        char Name =  name.charAt(0);
        
        if(octave == 2)
        {
            if(Character.toLowerCase(Name) == 'e') // LE0
            {
                noteNumber = 28;
            }
            else if(Character.toLowerCase(Name) == 'f') //LE1
            {
                noteNumber = 29;
            }
            else if(Character.toLowerCase(Name) == 'g') //LE3
            {
                noteNumber = 31;
            }
            else if(Character.toLowerCase(Name) == 'a') //LE5,A0
            {
                noteNumber = 33;
            }
            else if(Character.toLowerCase(Name) == 'b') //LE7,A2
            {
                noteNumber = 35;
            }
            else
            {
                noteNumber = -1;
            }
        }
        else if(octave == 3)
        {
            if(Character.toLowerCase(Name) == 'c') //LE8,A3
            {
                noteNumber = 36;
            }
            else if(Character.toLowerCase(Name) == 'd') //LE10,A5,D0
            {
                noteNumber = 38;
            }
            else if(Character.toLowerCase(Name) == 'e') //LE12,A7,D2
            {
                noteNumber = 40;
            }
            else if(Character.toLowerCase(Name) == 'f') //A8,D3
            {
                noteNumber = 41;
            }
            else if(Character.toLowerCase(Name) == 'g') //A10,D5,G0
            {
                noteNumber = 43;
            }
            else if(Character.toLowerCase(Name) == 'a') //A12,D7,G2
            {
                noteNumber = 45;
            }
            else if(Character.toLowerCase(Name) == 'b') //D9,G4,B0
            {
                noteNumber = 47;
            }
            else
            {
                noteNumber = -1;
            }
        }
        else if(octave == 4)
        {
            if(Character.toLowerCase(Name) == 'c') //D10,G5,B1
            {
                noteNumber = 48;
            }
            else if(Character.toLowerCase(Name) == 'd') //D12,G7,B3
            {
                noteNumber = 50;
            }
            else if(Character.toLowerCase(Name) == 'e') //G9,B5,HE0
            {
                noteNumber = 52;
            }
            else if(Character.toLowerCase(Name) == 'f') //G10,B6,HE1
            {
                noteNumber = 53;
            }
            else if(Character.toLowerCase(Name) == 'g') //G12,B8,HE3
            {
                noteNumber = 55;
            }
            else if(Character.toLowerCase(Name) == 'a') //B10,HE5
            {
                noteNumber = 57;
            }
            else if(Character.toLowerCase(Name) == 'b') //B12,HE7
            {
                noteNumber = 59;
            }  
            else
            {
                noteNumber = -1;
            }
        }
        else if(octave == 5)
        {
            if(Character.toLowerCase(Name) == 'c')//HE8
            {
                noteNumber = 60;
            }
            else if(Character.toLowerCase(Name) == 'd') //HE10
            {
                noteNumber = 62;
            }
            else if(Character.toLowerCase(Name) == 'e') //HE12
            {
                noteNumber = 64;
            }
            else
            {
                noteNumber = -1;
            }
        }
        else
        {
            noteNumber = -2;
        }

        if(name.charAt(1) == '#')
        {
            noteNumber++;
        }
        else if(name.charAt(1) == 'b')
        {
            noteNumber--;
        }
        return noteNumber;
    }
}   

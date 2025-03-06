package com.model;

public class Note 
{
    private char name;
    private int octave;
    private int lengthNum;
    private int lengthDenom;

    public Note(char n, int o, int lN, int lD)
    {
        this.name = n;
        this.octave = o;
        this.lengthNum = lN;
        this.lengthDenom = lD;
    }
    public String timeToJFugue()
    {
        double duration = (lengthNum / lengthDenom);
        return "/"+duration;
    }
    public int noteToJFugue()
    {
        int noteNumber = 0;

        if(octave == 2)
        {
            if(name == 'e')
            {

            }
        }

        return noteNumber;
    }

}

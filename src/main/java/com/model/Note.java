package com.model;

public class Note 
{
    private String name;
    private int octave;
    private int lengthNum;
    private int lengthDenom;

    public Note(String name, int octave, int lengthNum, int lengthDenom)
    {
        this.name = name;
        this.octave = octave;
        this.lengthNum = lengthNum;
        this.lengthDenom = lengthDenom;
    }
}

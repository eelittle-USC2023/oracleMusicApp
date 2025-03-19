package com.model;

public class Note 
{
    private String name;
    private int octave;
    private double length;
    private double position;

    public Note(String name, int octave, double length, double position)
    {
        this.name = name;
        this.octave = octave;
        this.length = length;
        this.position = position;
    }
}

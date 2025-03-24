package com.model;

public abstract class Instrument {
    protected Note[] chordPlayed;

    public abstract String toJFugueString();
    /**
     * Should return the class name, which should be an instrument, as a string. Implemented in the child classes. 
     * @author Ethan Little
     */
    public abstract String toString();
}

package com.model;

public abstract class Instrument {
    protected Note[] chordPlayed;

    public abstract String toJFugueString();
}

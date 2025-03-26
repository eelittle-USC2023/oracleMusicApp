package com.model;

import java.util.ArrayList;

public abstract class State 
{
    protected MusicPlayer player;
    protected ArrayList<String> display;

    public State(MusicPlayer p, ArrayList<String> d)
    {
        this.player = p;
        this.display = d;
    }
    public ArrayList<String> getDisplay()
    {
        return display;
    }
    public abstract void pressTabButton();
    public abstract void pressChordButton();
    public abstract void pressNoteButton();
}

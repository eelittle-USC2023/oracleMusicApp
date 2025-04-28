package com.model;

import java.util.ArrayList;

public abstract class State 
{
    protected MusicPlayer player;
    protected ArrayList<String> display;
    protected Guitar guitar = new Guitar();

    /**
     * Sets the default state with an inputted music player and display array
     * @param p the inputted music player
     * @param d the inputted display array
     * @author Ally Blackwell
     */
    public State(MusicPlayer p, ArrayList<String> d)
    {
        this.player = p;
        this.display = d;
    }
    /**
     * Returns the display array for the current state
     * @return the array with the song
     * @author Ally Blackwell
     */
    public ArrayList<String> getDisplay()
    {
        return display;
    }
    /**
     * These two are implimented in child classes to change the mode
     * @author Ally Blackwell
     */
    public abstract void pressTabButton();
    public abstract void pressNoteButton();
    /**
     * Gets the pure array list of tabs
     * @return array list of array list of tabs
     */
    protected abstract ArrayList<ArrayList<String>> getTabs();
}

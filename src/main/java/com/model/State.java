package com.model;

import java.util.ArrayList;

public abstract class State 
{
    protected MusicPlayer player;
    private ArrayList<String> tabs;
    private ArrayList<String> chords;
    private ArrayList<String> notes;
    protected Song song;

    public State(MusicPlayer p, Song s)
    {
        this.player = p;
        this.song = s;
    }
    public void notesToTabs()
    {
        player.showSong(tabs);
    }
    public void notesToChords()
    {
        player.showSong(chords);
    }
    public void notesToNotes()
    {
        player.showSong(notes);
    }
    public abstract void pressTabButton();
    public abstract void pressChordButton();
    public abstract void pressNoteButton();
}

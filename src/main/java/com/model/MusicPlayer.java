package com.model;

import java.io.FileWriter;
import java.util.ArrayList;

public class MusicPlayer {
    private State tabState;
    private State noteState;
    private State state;
    protected Song song;
    private static String timeSignature = "4/4 ";

    /**
     * Sets the default of Music Player and sets this song as the inputted song
     * @param s the song that needs to be played
     * @author Ally Blackwell
     */
    public MusicPlayer(Song s)
    {
        this.song = s;
        tabState = new TabState(this);
        noteState = new NoteState(this);
        state = tabState;
    }
    /**
     * Changes the player into "Tab Mode"
     * @author Ally Blackwell
     */
    public void pressTabButton()
    {
        state.pressTabButton();
    }
    /**
     * Changes the player into "Note Mode"
     * @author Ally Blackwell
     */
    public void pressNoteButton()
    {
        state.pressNoteButton();
    }
    /**
     * Sets the state as the inputted state
     * @param state the state to be set
     * @author Ally Blackwell
     */
    public void setState(State state)
    {
        this.state = state;
    }
    /**
     * Gets the tabState
     * @return the tabstate
     * @author Ally Blackwell
     */
    public State getTabState()
    {
        return tabState;
    }
    /**
     * Gets the noteState
     * @return the note state
     * @author Ally Blackwell
     */
    public State getNoteState()
    {
        return noteState;
    }
    /**
     * Writes the current state of the song into a text file
     * @author Ally Blackwell
     */
    public void showSong()
    {
        String fileName = song.getArtistName() + "_" + song.getTitle()+".txt";
        try(FileWriter writer = new FileWriter(fileName))
        {
            for(String line : this.state.getDisplay())
            {
                writer.write(line);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Plays the song in Jfugue
     * @author Ally Blackwell / Ethan Little
     */
    public void playSong()
    {
        String line = song.toJFugueString();
        try
        {
            Music.play(song.getInstrument().toJFugueString() + timeSignature + line);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Gets the song
     * @return the song of this music player
     * @author Ally Blackwell
     */
    public Song getSong()
    {
        return song;
    }
    /**
     * Sets the song to the inputted song
     * @param s the inputted song
     * @author Ally Blackwell
     */
    public void setSong(Song s)
    {
        this.song = s;
    }

    /**
     * 
     */
    public ArrayList<String> getSongString() {
        return this.state.getDisplay();
    }
    public ArrayList<ArrayList<String>> getTabs()
    {
        return tabState.getTabs();
    }
}


package com.model;

import java.lang.Thread;
import java.util.ArrayList;

public class MusicPlayer {
    private State tabState;
    private State chordState;
    private State noteState;
    private State state;
    private Song song;
    private static String timeSignature = "4/4 ";

    public MusicPlayer()
    {
        tabState = new TabState(this);
        chordState = new ChordState(this);
        noteState = new NoteState(this);
        state = noteState;
    }
    public void pressTabButton()
    {
        state.pressTabButton();
    }
    public void pressChordButton()
    {
        state.pressChordButton();
    }
    public void pressNoteButton()
    {
        state.pressNoteButton();
    }
    public void setState(State state)
    {
        this.state = state;
    }
    public State getTabState()
    {
        return tabState;
    }
    public State getChordState()
    {
        return chordState;
    }
    public State getNoteState()
    {
        return noteState;
    }
    public void showSong(ArrayList<String> display)
    {
        for(String line : display)
        {
            System.out.println(line);
        }
    }
    public void playSong(Song s)
    {

        String line = s.toJFugueString();
        System.out.print(line);
        try
        {
            Music.play(s.getInstrument().toJFugueString() + timeSignature + line);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public Song getSong()
    {
        return song;
    }
    public static void main(String[] args) {
        ArrayList<Song> songs = DataLoader.getSongs();
        MusicPlayer player = new MusicPlayer();
        player.playSong(songs.get(1));
    }
}


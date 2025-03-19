package com.model;
import java.util.ArrayList;

public class MusicPlayer 
{
    private State tabState;
    private State chordState;
    private State state;
    private Song song;

    public MusicPlayer()
    {

    }
    public void pressTabButton()
    {

    }
    public void pressChordButton()
    {

    }
    public void setState(State state)
    {

    }
    public State getTabState()
    {
        MusicPlayer player = new MusicPlayer();
        State temp = new State(player, "title");
        return temp;
    }
    public State getChordState()
    {
        MusicPlayer player = new MusicPlayer();
        State temp = new State(player, "title");
        return temp; 
    }
    public void showSong(String songName, ArrayList<String> notes)
    {

    }
    public void acceptInstrumentInput()
    {
        
    }
}
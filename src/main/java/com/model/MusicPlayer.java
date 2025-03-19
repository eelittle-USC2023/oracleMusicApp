package com.model;
import java.util.ArrayList;
import com.musicapp.Music;
import java.lang.Thread;

public class MusicPlayer 
{
    private static String guitar = "I[Guitar] ";
    private static String timeSignature = "3/4 ";
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
    public static void main(String[] args) {
        try {
            Music.play(guitar + timeSignature + "54q+48h 47q");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

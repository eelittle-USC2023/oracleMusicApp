package com.model;

import java.lang.Thread;
import java.util.ArrayList;

public class MusicPlayer {
    private State tabState;
    private State chordState;
    private State state;
    private Song song;
    private static String timeSignature = "4/4 ";

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
    public static void main(String[] args) {
        ArrayList<Song> songs = DataLoader.getSongs();
        MusicPlayer player = new MusicPlayer();
        player.playSong(songs.get(1));
    }
}


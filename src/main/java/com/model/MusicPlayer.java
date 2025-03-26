package com.model;

//import java.util.ArrayList;

public class MusicPlayer {
    private State tabState;
    private State chordState;
    private State noteState;
    private State state;
    protected Song song;
    private static String timeSignature = "4/4 ";

    public MusicPlayer(Song song)
    {
        this.song = song;
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
    public void showSong()
    {
        for(String line : this.state.getDisplay())
        {
            System.out.print(line);
        }
    }
    public void playSong()
    {
        pressTabButton();
        showSong();
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
    public Song getSong()
    {
        return song;
    }
    public void setSong(Song s)
    {
        this.song = s;
    }
    /** 
    public static void main(String[] args) {
        ArrayList<Song> songs = DataLoader.getSongs();
        MusicPlayer player = new MusicPlayer(songs.get(1));
        player.playSong(songs.get(1));
    }
    */
}


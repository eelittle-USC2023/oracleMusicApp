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
    /*
     * Lowest Note: E -2nd Octave
     * Highest Note E -5th Octave
     */
    /*
     * Octave starts: 
     * 0 -0
     * 12 -1
     * 24 -2
     * 36 -3
     * 48 -4
     * 60 -5
     * 
     */
}   

    /*
     * Fret Board Note Designation
     * High E(E fourth octave): 
     * -52 : 4
     * Fret 1: F -53 : 4
     * Fret 2: F sharp -54 : 4
     * Fret 3: G -55 : 4
     * Fret 4: G sharp -56 : 4
     * Fret 5: A -57 : 4
     * Fret 6: A sharp -58 : 4
     * Fret 7: B -59 : 4
     * Fret 8: C -60 : 5
     * Fret 9: C sharp -61 :5
     * Fret 10: D -62 : 5
     * Fret 11: D sharp -63
     * Fret 12: E -64
     * 
     * B(third octave): -47
     * Fret 1: C -48
     * Fret 2: C sharp -49
     * Fret 3: D -50
     * Fret 4: D sharp -51
     * Fret 5: E -52
     * Fret 6: F -53
     * Fret 7: F sharp -54
     * Fret 8: G -55
     * Fret 9: G Sharp -56
     * Fret 10: A -57
     * Fret 11: A sharp -58
     * Fret 12: B -59
     * 
     * G(third octave): -43
     * Fret 1: G sharp -44
     * Fret 2: A -45
     * Fret 3: A sharp -46
     * Fret 4: B -47
     * Fret 5: C -48
     * Fret 6: C sharp -49
     * Fret 7: D -50
     * Fret 8: D sharp -51
     * Fret 9: E -52
     * Fret 10: F -53
     * Fret 11: F sharp -54
     * Fret 12: G -55
     * 
     * D(third octave): -38
     * Fret 1: D sharp -39
     * Fret 2: E -40
     * Fret 3: F -41
     * Fret 4: F sharp -42
     * Fret 5: G -43
     * Fret 6: G sharp -44
     * Fret 7: A -45
     * Fret 8: A sharp -46
     * Fret 9: B -47
     * Fret 10: C -48
     * Fret 11: C sharp -49
     * Fret 12: D -50
     * 
     * A(Second octave): -33
     * Fret 1: A sharp -34
     * Fret 2: B -35
     * Fret 3: C -36
     * Fret 4: C sharp -37
     * Fret 5: D -38
     * Fret 6: D sharp -39
     * Fret 7: E -40
     * Fret 8: F -41
     * Fret 9: F sharp -42
     * Fret 10: G -43
     * Fret 11: G sharp -44
     * Fret 12: A -45
     * 
     * Fret 0: Low E(E second octave): -28
     * Fret 1: F -29
     * Fret 2: F sharp -30
     * Fret 3: G -31
     * Fret 4: G sharp -32
     * Fret 5: A -33
     * Fret 6: A sharp -34
     * Fret 7: B -35
     * Fret 8: C -36
     * Fret 9: C sharp -37
     * Fret 10: D -38
     * Fret 11: D sharp -39
     * Fret 12: E -40
     */


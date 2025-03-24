package com.model;

import java.util.ArrayList;

public class NoteState extends State
{
    private static final ArrayList<String> chords = new ArrayList<String>();
    
    public NoteState(MusicPlayer player)
    {
        super(player, chords);
    }
    public void pressTabButton()
    {
        System.out.println("Entering Tab Mode");
        player.setState(player.getTabState());
    }
    public void pressChordButton()
    {
        System.out.println("Entering Chord Mode");
        player.setState(player.getChordState());
    }
    public void pressNoteButton()
    {
        System.out.println("Already in Note State");
    }
}

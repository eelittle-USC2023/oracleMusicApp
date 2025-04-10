package com.model;

import java.util.ArrayList;

//This Will Be Implemented in the next Sprint

public class ChordState extends State
{
    private static final ArrayList<String> chords = new ArrayList<String>();
    
    public ChordState(MusicPlayer player)
    {
        
    }
    public void pressTabButton()
    {
        System.out.println("Entering Tab Mode");
        player.setState(player.getTabState());
    }
    public void pressChordButton()
    {
        System.out.println("Already in Chord Mode");
    }
    public void pressNoteButton()
    {
        System.out.println("Entering Note Mode");
        player.setState(player.getNoteState());
    }
}

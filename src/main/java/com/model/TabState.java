package com.model;

import java.util.ArrayList;
public class TabState extends State
{
    private static final ArrayList<String> tabs = new ArrayList<String>();
    
    public TabState(MusicPlayer player)
    {
        super(player, tabs);
    }
    public void pressTabButton()
    {
        System.out.println("Already In Tab Mode");
    }
    public void pressChordButton()
    {
        System.out.println("Entering Chord Mode");
        player.setState(player.getChordState());
    }
    public void pressNoteButton()
    {
        System.out.println("Entering Note Mode");
        player.setState(player.getNoteState());
    }
}

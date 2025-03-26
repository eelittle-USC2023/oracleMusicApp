package com.model;

import java.util.ArrayList;

public class NoteState extends State
{
    
    public NoteState(MusicPlayer player)
    {
        super(player, new ArrayList<String>());
        this.display = createDisplay();
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
    public ArrayList<String> createDisplay()
    {
        ArrayList<String> displayTemplate = new ArrayList<String>();
        ArrayList<Measure> measures = player.getSong().getMeasures();
        for(int i = 0; i < measures.size(); i++)
        {
            ArrayList<Note> notes = measures.get(i).getNotes();
            StringBuilder measure = new StringBuilder();
            for(int j = 0; j < notes.size(); j++)
            {
                measure.append(notes.get(j).getName() + " ");
            }
            measure.append("|");
            displayTemplate.add(measure.toString());
        }
        return displayTemplate;
    }
}

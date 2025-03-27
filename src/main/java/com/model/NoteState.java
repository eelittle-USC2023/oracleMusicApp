package com.model;

import java.util.ArrayList;
/**
 * Sets the display array to the note names
 * @author Ally Blackwell
 */
public class NoteState extends State
{
    /**
     * Calls the State class with the music player and a new array list which will become the display
     * @param player the music player
     * @author Ally Blackwell
     */
    public NoteState(MusicPlayer player)
    {
        super(player, new ArrayList<String>());
        this.display = createDisplay();
    }
    /**
     * Changes the user into "Tab Mode"
     * @author Ally Blackwell
     */
    public void pressTabButton()
    {
        System.out.println("Entering Tab Mode");
        player.setState(player.getTabState());
    }
    /**
     * Changes the user into "Chord Mode"
     * @author Ally Blackwell
     */
    public void pressChordButton()
    {
        System.out.println("Entering Chord Mode");
        player.setState(player.getChordState());
    }
    /**
     * Reminds the user that it is already in "Note Mode"
     * @author Ally Blackwell
     */
    public void pressNoteButton()
    {
        System.out.println("Already in Note Mode");
    }
    /**
     * Creates the display array by calling getName for every note in the song and appending it into a printable string
     * @author Ally Blackwell
     */
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
                if(!notes.get(j).getName().equals("R"))
                {
                    measure.append(notes.get(j).getName());
                    if(j + 1 < notes.size()) 
                    {
                        if (notes.get(j).getPosition() == notes.get(j + 1).getPosition() && !notes.get(j+1).getName().equals("R"))
                        {
                            measure.append("+");
                        }
                        else
                        {
                            measure.append(" ");
                        }
                    }
                }
            }
            measure.append("| ");
            displayTemplate.add(measure.toString());
        }
        return displayTemplate;
    }
}

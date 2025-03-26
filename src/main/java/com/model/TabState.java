package com.model;

import java.util.ArrayList;
public class TabState extends State
{
    private ArrayList<ArrayList<String>> tabs;
    
    public TabState(MusicPlayer player)
    {
        super(player, new ArrayList<>());
        this.tabs = player.getSong().toTab();
        this.display = createDisplay(tabs);
        
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
    private ArrayList<String> createDisplay(ArrayList<ArrayList<String>> t)
    {
        ArrayList<String> displayTemplate = new ArrayList<String>();
        for(int i = 0; i < t.size(); i++)
        {
            StringBuilder displayString = new StringBuilder();

            for(int j = 0; j < t.get(i).size(); j++)
            {
                if(t.get(i).get(j).equals("-1"))
                {
                    displayString.append("-"); 
                }
                else
                {
                    displayString.append(t.get(i).get(j));
                }
                if(j < t.get(i).size() - 1)
                {
                    displayString.append(" ");
                }
            }
            displayTemplate.add(displayString.toString());
        }
        return displayTemplate;
    }
}

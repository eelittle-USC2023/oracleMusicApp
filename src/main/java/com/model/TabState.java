package com.model;

import java.util.ArrayList;
/**
 * Sets the display array to the Tabs
 * @author Ally Blackwell
 */
public class TabState extends State
{
    private ArrayList<ArrayList<String>> tabs;
    private ArrayList<String> LowE;
    private ArrayList<String> A;
    private ArrayList<String> D;
    private ArrayList<String> G;
    private ArrayList<String> B;
    private ArrayList<String> HighE;
    private Guitar guitar;
    
    /**
     * Calls the super and creates a new arrayList as well as calling the tabs arraylist for the song and initializing create display
     * @author Ally Blackwell
     */
    public TabState(MusicPlayer player)
    {
        super(player, new ArrayList<>());
        this.tabs = new ArrayList<ArrayList<String>>();
        toTab();
        this.display = createDisplay(tabs);
        
    }
    /**
     * Reminds the user that they are already in "Tab Mode"
     * @author Ally Blackwell
     */
    public void pressTabButton()
    {
        System.out.println("Already In Tab Mode");
    }
    /**
     * Switches the user to "Chord Mode"
     * @author Ally Blackwell
     */
    public void pressChordButton()
    {
        System.out.println("Entering Chord Mode");
        player.setState(player.getChordState());
    }
    /**
     * Switches the user to "Note Mode"
     * @author Ally Blackwell
     */
    public void pressNoteButton()
    {
        System.out.println("Entering Note Mode");
        player.setState(player.getNoteState());
    }
    /**
     * Takes the tab array and puts each string into a single String changing -1 values to '-' to represent the string
     * @param t the ArrayList of tabs
     * @return the string array for display
     * @author Ally Blackwell
     */
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
    /**
     * Creates and populates an array list of array lists for each string while filling each string with the tab number for each note played in the song
     * @return the Array List of Array Lists of tabs
     * @author Ally Blackwell
     */
    private ArrayList<ArrayList<String>> toTab()
    {
        ArrayList<Measure> measures = player.song.getMeasures();
        LowE = new ArrayList<String>();
        A = new ArrayList<String>();
        D = new ArrayList<String>();
        G = new ArrayList<String>();
        B = new ArrayList<String>();
        HighE = new ArrayList<String>();

        for(int i = 0; i < measures.size(); i++)
        {
            ArrayList<Note> notes = measures.get(i).getNotes();
            for(int j = 0; j < notes.size(); j++)
            {
                int noteNumber = notes.get(j).noteToJFugue();
                String string = stringRange(noteNumber);
                switch(string)
                {
                    case "LowE": LowE.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "A": A.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "D": D.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "G": G.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "B": B.add(Integer.toString(getFret(noteNumber, string))); break;
                    case "HighE": HighE.add(Integer.toString(getFret(noteNumber, string)));  break;
                }
                if(j+1 < notes.size() && notes.get(j).getPosition() != notes.get(j+1).getPosition()) 
                {
                    addAndResetFretboard();
                }  
            }
        }
        populateTabs();
        return tabs;
    }

    /**
     * Adds the array list for each string to the tabs array list
     * @author Ally Blackwell
     */
    private void populateTabs()
    {
        tabs = new ArrayList<ArrayList<String>>();
        this.tabs.add(LowE);
        this.tabs.add(A);
        this.tabs.add(D);
        this.tabs.add(G);
        this.tabs.add(B);
        this.tabs.add(HighE);
    }
    /**
     * Add the current fret to each string and then reset the guitar for the next note
     * @author Ally Blackwell
     */
    private void addAndResetFretboard()
    {
        this.LowE.add(Integer.toString(guitar.getLowE()));
        this.A.add(Integer.toString(guitar.getA()));
        this.D.add(Integer.toString(guitar.getD()));
        this.G.add(Integer.toString(guitar.getG()));
        this.B.add(Integer.toString(guitar.getB()));
        this.HighE.add(Integer.toString(guitar.getHighE()));

        guitar.setLowE(-1);
        guitar.setA(-1);
        guitar.setD(-1);
        guitar.setG(-1);
        guitar.setB(-1);
        guitar.setHighE(-1);
    }
    /**
     * Gets the fret number for the note on its particular string
     * @param nN note number determined by the toJFugue in Note Class
     * @param s The string the note is played on, determined by the stringRange
     * @return the note number subtracted by the string number which gives the fret
     * @author Ally Blackwell
     */
    private int getFret(int nN, String s)
    {
        int stringNumber;
        switch (s) 
        {
            case "LowE": stringNumber = 28; break;
            case "A": stringNumber = 33; break;
            case "D": stringNumber = 38; break;
            case "G": stringNumber = 43; break;
            case "B": stringNumber = 47; break;
            case "HighE": stringNumber = 52; break;
            default:
                stringNumber = -1;
        }
        return nN - stringNumber;
    }
    /**
     * Determines what note the string is on based on its JFugue number
     * @param nN note number
     * @return the string the note is played on
     * @author Ally Blackwell
     */
    private String stringRange(int nN)
    {
        if(nN > 27 && nN < 41 && guitar.getLowE() == -1)
        {
            return "LowE";
        }
        if (nN > 34 && nN < 46 && guitar.getA() == -1)
        {
            return "A";
        }
        if (nN > 37 && nN < 51 && guitar.getD() == -1)
        {
            return "D";
        }
        if (nN > 42 && nN < 56 && guitar.getG() == -1)
        {
            return "G";
        }
        if (nN > 46 && nN < 60 && guitar.getB() == -1)
        {
            return "B";
        }
        if (nN > 51 && nN < 65 && guitar.getHighE() == -1)
        {
            return "HighE";
        }
        return "error";
    }
}

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
    
    /**
     * Calls the super and creates a new arrayList as well as calling the tabs arraylist for the song and initializing create display
     * @author Ally Blackwell
     */
    public TabState(MusicPlayer player)
    {
        super(player, new ArrayList<>());
        this.tabs = new ArrayList<ArrayList<String>>();
        resetFretboard();
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
                    displayString.append("--"); 
                }
                else
                {
                    displayString.append(t.get(i).get(j) + "-");
                }
                displayString.append("-");
            }
            //displayString.append('\n');
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
            int string = 0;
            for(int j = 0; j < notes.size(); j++)
            {
                int noteNumber = notes.get(j).noteToJFugue();
                String fret = "-1";
                if (noteNumber != -1)
                {
                    fret = Integer.toString(getFret(noteNumber, string));
                }
                else
                {
                    fret = "- ";
                }
                switch(string)
                {
                    case 0: LowE.add(fret); break;
                    case 1: A.add(fret); break;
                    case 2: D.add(fret); break;
                    case 3: G.add(fret); break;
                    case 4: B.add(fret); break;
                    case 5: HighE.add(fret);  break;
                }
                string ++; 
                if(string == 6) 
                {
                    addFretboard();
                    resetFretboard();
                    string = 0;
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
     * Add the current fret to each string
     * @author Ally Blackwell
     */
    private void addFretboard()
    {
        this.LowE.add(Integer.toString(guitar.getLowE()));
        this.A.add(Integer.toString(guitar.getA()));
        this.D.add(Integer.toString(guitar.getD()));
        this.G.add(Integer.toString(guitar.getG()));
        this.B.add(Integer.toString(guitar.getB()));
        this.HighE.add(Integer.toString(guitar.getHighE()));
    }
    /**
     * Reset fretboard for next note
     * @author Ally Blackwell
     */
    private void resetFretboard()
    {
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
    private int getFret(int nN, int s)
    {
        int stringNumber;
        switch (s) 
        {
            case 0: stringNumber = 28; break;  // Low E
            case 1: stringNumber = 33; break;  // A
            case 2: stringNumber = 38; break;  // D
            case 3: stringNumber = 43; break;  // G
            case 4: stringNumber = 47; break;  // B
            case 5: stringNumber = 52; break;  // High E
            default:
                stringNumber = -1; 
        }

        int fret = nN - stringNumber - 12;

        if (fret < 0) {
            return -1;
        }

        return fret;
    }


    public ArrayList<ArrayList<String>> getTabs()
    {
        return tabs;
    }
}

package com.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Guitar extends Instrument
{
    private int LowEFret;
    private int AFret;
    private int DFret;
    private int GFret;
    private int BFret;
    private int HighEFret;
    private static final HashMap<String, HashMap<Integer, Integer>> fretboard = new HashMap<>();

    public Guitar(int lE, int A, int D, int G, int B, int hE)
    {
        this.LowEFret = lE;
        this.AFret = A;
        this.DFret = D;
        this.GFret = G;
        this.BFret = B;
        this.HighEFret = hE;
    }
    public Guitar () {
        LowEFret = -1;
        AFret = -1;
        DFret = -1;
        GFret = -1;
        BFret = -1;
        HighEFret = -1;
    }
    public String toJFugueString() {
        return "I[Guitar] ";
    }

    public ArrayList<Guitar> toTab(Song s)
    {
        ArrayList<Guitar> tabs = new ArrayList<Guitar>();
        Guitar guitar = new Guitar();
        ArrayList<Measure> measures = s.getSong();
        for(int i = 0; i < measures.size(); i++)
        {
            ArrayList<Note> notes = measures.get(i).getNotes();
            for(int j = 0; j < notes.size(); j++)
            {
                int stringCheck = 0;
                int noteNumber = notes.get(j).noteToJFugue();
                if(noteNumber > 27 && noteNumber < 41 && stringCheck == 0)
                {
                    guitar.LowEFret = getFret(noteNumber, "Low E");
                }
                stringCheck++;
                if (noteNumber > 34 && noteNumber < 46 && stringCheck == 1)
                {
                    guitar.AFret = getFret(noteNumber, "A");
                }
                stringCheck++;
                if (noteNumber > 37 && noteNumber < 51 && stringCheck == 2)
                {
                    guitar.DFret = getFret(noteNumber, "D");
                }
                stringCheck++;
                if (noteNumber > 42 && noteNumber < 56 && stringCheck == 3)
                {
                    guitar.GFret = getFret(noteNumber, "G");
                }
                stringCheck++;
                if (noteNumber > 46 && noteNumber < 60 && stringCheck == 4)
                {
                    guitar.BFret = getFret(noteNumber, "B");
                }
                stringCheck++;
                if (noteNumber > 51 && noteNumber < 65 && stringCheck == 5)
                {
                    guitar.HighEFret = getFret(noteNumber, "High E");
                }
                if(j+1 < notes.size()) 
                {
                    if (notes.get(i).getPosition() != notes.get(i+1).getPosition())
                    {
                        System.out.println(guitar);
                        tabs.add(guitar);
                        guitar.LowEFret = -1;
                        guitar.AFret = -1;
                        guitar.DFret = -1;
                        guitar.GFret = -1;
                        guitar.BFret = -1;
                        guitar.HighEFret = -1;
                    }
                }  
            }
        }
        return tabs;
    }

    public int getFret(int nN, String s)
    {
        if(fretboard.containsKey(s))
        {
            HashMap<Integer, Integer> tabList = fretboard.get(s);
            if(tabList.containsKey(nN))
            {
                return tabList.get(nN);
            }
        }
        System.out.println("Get Fret Error");
        return -2;
    }

    static {
        HashMap<Integer, Integer> lowE = new HashMap<>();
        lowE.put(-28, 0); lowE.put(-29, 1); lowE.put(-30, 2); lowE.put(-31, 3);
        lowE.put(-32, 4); lowE.put(-33, 5); lowE.put(-34, 6); lowE.put(-35, 7);
        lowE.put(-36, 8); lowE.put(-37, 9); lowE.put(-38, 10); lowE.put(-39, 11);
        lowE.put(-40, 12); 

        HashMap<Integer, Integer> A = new HashMap<>();
        A.put(-33, 0); A.put(-34, 1); A.put(-35, 2); A.put(-36, 3); 
        A.put(-37, 4); A.put(-38, 5); A.put(-39, 6); A.put(-40, 7); 
        A.put(-41, 8); A.put(-42, 9); A.put(-43, 10); A.put(-44, 11); 
        A.put(-45, 12);

        HashMap<Integer, Integer> D = new HashMap<>();
        D.put(-38, 0); D.put(-39, 1); D.put(-40, 2); D.put(-41, 3); 
        D.put(-42, 4); D.put(-43, 5); D.put(-44, 6); D.put(-45, 7); 
        D.put(-46, 8); D.put(-47, 9); D.put(-48, 10); D.put(-49, 11); 
        D.put(-50, 12); 

        HashMap<Integer, Integer> G = new HashMap<>();
        G.put(-43, 0); G.put(-44, 1); G.put(-45, 2); G.put(-46, 3); 
        G.put(-47, 4); G.put(-48, 5); G.put(-49, 6); G.put(-50, 7); 
        G.put(-51, 8); G.put(-52, 9); G.put(-53, 10); G.put(-54, 11); 
        G.put(-55, 12); 

        HashMap<Integer, Integer> B = new HashMap<>();
        B.put(-47, 0); B.put(-48, 1); B.put(-49, 2); B.put(-50, 3); 
        B.put(-51, 4); B.put(-52, 5); B.put(-53, 6); B.put(-54, 7); 
        B.put(-55, 8); B.put(-56, 9); B.put(-57, 10); B.put(-58, 11); 
        B.put(-59, 12); 

        HashMap<Integer, Integer> highE = new HashMap<>();
        highE.put(-52, 0); highE.put(-53, 1); highE.put(-54, 2); highE.put(-55, 3);
        highE.put(-56, 4); highE.put(-57, 5); highE.put(-58, 6); highE.put(-59, 7);
        highE.put(-60, 8); highE.put(-61, 9); highE.put(-62, 10); highE.put(-63, 11);
        highE.put(-64, 12); 

        fretboard.put("Low E", lowE);
        fretboard.put("A", A);
        fretboard.put("D", D);
        fretboard.put("G", G);
        fretboard.put("B", B);
        fretboard.put("High E", highE);
    }

    /**
     * toString method giving Guitar as a String.
     * @return "Guitar", as that is the instrument being represented by this class.
     * @author Ethan Little
     */
    @Override
    public String toString() {
        return "Guitar";
    }
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
 */

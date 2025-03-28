package com.model;

public class Guitar extends Instrument
{
    private int LowEFret;
    private int AFret;
    private int DFret;
    private int GFret;
    private int BFret;
    private int HighEFret;

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

    public void setLowE(int fret)
    {
        this.LowEFret = fret;
    }
    
    public void setA(int fret)
    {
        this.AFret = fret;
    }
    
    public void setD(int fret)
    {
        this.DFret = fret;
    }

    public void setG(int fret)
    {
        this.GFret = fret;
    }

    public void setB(int fret)
    {
        this.BFret = fret;
    }
    
    public void setHighE(int fret)
    {
        this.HighEFret = fret;
    }

    public int getLowE()
    {
        return LowEFret;
    }
    
    public int getA()
    {
        return AFret;
    }
    
    public int getD()
    {
        return DFret;
    }

    public int getG()
    {
        return GFret;
    }

    public int getB()
    {
        return BFret;
    }
    
    public int getHighE()
    {
        return HighEFret;
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


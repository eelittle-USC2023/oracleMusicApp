package com.model;

public class Guitar extends Instrument
{
    private int LowEFret;
    private int AFret;
    private int DFret;
    private int GFret;
    private int BFret;
    private int HighEFret;

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

}

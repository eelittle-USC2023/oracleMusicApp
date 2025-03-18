package com.model;

public class TabState 
{
    final Guitar guitar = "temp";
    
    public TabState(MusicPlayer player)
    {
        this.guitar = new Guitar();
        this.guitar.LowEFret = 0;
        this.guitar.AFret = 0;
        this.guitar.DFret = 0;
        this.guitar.GFret = 0;
        this.guitar.BFret = 0;
        this.guitar.HighEFret = 0;
    }
    public void pressTabButton()
    {

    }
    public void pressChordButton()
    {
        
    }
}

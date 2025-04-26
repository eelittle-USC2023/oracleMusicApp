package com.model;
/**
 * Establishes the baseline of a guitar "Strum"
 * @author Ally Blackwell
 */
public class Guitar extends Instrument
{
    private int LowEFret;
    private int AFret;
    private int DFret;
    private int GFret;
    private int BFret;
    private int HighEFret;

    /**
     * Defaults each played fret as -1
     * @author Ally Blackwell
     */
    public Guitar () {
        this.LowEFret = -1;
        this.AFret = -1;
        this.DFret = -1;
        this.GFret = -1;
        this.BFret = -1;
        this.HighEFret = -1;
    }
    /**
     * This song returns the JFugue indicator of Guitar
     * @author Ethan Little
     */
    public String toJFugueString() {
        return "I[Guitar] ";
    }
    /**
     * Sets the value of LowE to the inputted fret
     * @param fret the inputted fret
     * @author Ally Blackwell
     */
    public void setLowE(int fret)
    {
        this.LowEFret = fret;
    }
    /**
     * Sets the value of A to the inputted fret
     * @param fret the inputted fret
     * @author Ally Blackwell
     */
    public void setA(int fret)
    {
        this.AFret = fret;
    }
    /**
     * Sets the value of D to the inputted fret
     * @param fret the inputted fret
     * @author Ally Blackwell
     */
    public void setD(int fret)
    {
        this.DFret = fret;
    }
    /**
     * Sets the value of G to the inputted fret
     * @param fret the inputted fret
     * @author Ally Blackwell
     */
    public void setG(int fret)
    {
        this.GFret = fret;
    }
    /**
     * Sets the value of B to the inputted fret
     * @param fret the inputted fret
     * @author Ally Blackwell
     */
    public void setB(int fret)
    {
        this.BFret = fret;
    }
    /**
     * Sets the value of HighE to the inputted fret
     * @param fret the inputted fret
     * @author Ally Blackwell
     */    
    public void setHighE(int fret)
    {
        this.HighEFret = fret;
    }
    /**
     * Gets the value of LowE
     * @return LowE Fret
     * @author Ally Blackwell
     */
    public int getLowE()
    {
        return LowEFret;
    }
    /**
     * Gets the value of A
     * @return A Fret
     * @author Ally Blackwell
     */
    public int getA()
    {
        return AFret;
    }
    /**
     * Gets the value of D
     * @return D Fret
     * @author Ally Blackwell
     */
    public int getD()
    {
        return DFret;
    }
    /**
     * Gets the value of G
     * @return G Fret
     * @author Ally Blackwell
     */
    public int getG()
    {
        return GFret;
    }
    /**
     * Gets the value of B
     * @return B Fret
     * @author Ally Blackwell
     */
    public int getB()
    {
        return BFret;
    }
    /**
     * Gets the value of HighE
     * @return HighE Fret
     * @author Ally Blackwell
     */
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

    public int getStringTab(String string)
    {

        if (string == "LE")
        {
            return getLowE();
        }
        else if (string == "A")
        {
            return getA();
        }
        else if (string == "B")
        {
            return getB();
        }
        else if (string == "D")
        {
            return getD();
        }
        else if (string == "G")
        {
            return getG();
        }
        else if (string == "HE")
        {
            return getHighE();
        }
        else
        {
            return -2;
        }
    }
}


package com.model;

public class Note 
{
    private char name;
    private int octave;
    private int lengthNum;
    private int lengthDenom;

    public Note(char n, int o, int lN, int lD)
    {
        this.name = n;
        this.octave = o;
        this.lengthNum = lN;
        this.lengthDenom = lD;
    }
    public String timeToJFugue()
    {
        double duration = (lengthNum / lengthDenom);
        return "/"+duration;
    }
    public int noteToJFugue()
    {
        if(octave == 2)
        {
            if(Character.toLowerCase(name) == 'e') // LE0
            {
                return 28;
            }
            else if(Character.toLowerCase(name) == 'f') //LE1
            {
                return 29;
            }
            else if(Character.toLowerCase(name) == 'g') //LE3
            {
                return 31;
            }
            else if(Character.toLowerCase(name) == 'a') //LE5,A0
            {
                return 33;
            }
            else if(Character.toLowerCase(name) == 'b') //LE7,A2
            {
                return 35;
            }
            else
            {
                return -1;
            }
        }
        else if(octave == 3)
        {
            if(Character.toLowerCase(name) == 'c') //LE8,A3
            {
                return 36;
            }
            else if(Character.toLowerCase(name) == 'd') //LE10,A5,D0
            {
                return 38;
            }
            else if(Character.toLowerCase(name) == 'e') //LE12,A7,D2
            {
                return 40;
            }
            else if(Character.toLowerCase(name) == 'f') //A8,D3
            {
                return 41;
            }
            else if(Character.toLowerCase(name) == 'g') //A10,D5,G0
            {
                return 43;
            }
            else if(Character.toLowerCase(name) == 'a') //A12,D7,G2
            {
                return 45;
            }
            else if(Character.toLowerCase(name) == 'b') //D9,G4,B0
            {
                return 47;
            }
            else
            {
                return -1;
            }
        }
        else if(octave == 4)
        {
            if(Character.toLowerCase(name) == 'c') //D10,G5,B1
            {
                return 48;
            }
            else if(Character.toLowerCase(name) == 'd') //D12,G7,B3
            {
                return 50;
            }
            else if(Character.toLowerCase(name) == 'e') //G9,B5,HE0
            {
                return 52;
            }
            else if(Character.toLowerCase(name) == 'f') //G10,B6,HE1
            {
                return 53;
            }
            else if(Character.toLowerCase(name) == 'g') //G12,B8,HE3
            {
                return 55;
            }
            else if(Character.toLowerCase(name) == 'a') //B10,HE5
            {
                return 57;
            }
            else if(Character.toLowerCase(name) == 'b') //B12,HE7
            {
                return 59;
            }  
            else
            {
                return -1;
            }
        }
        else if(octave == 5)
        {
            if(Character.toLowerCase(name) == 'c')//HE8
            {
                return 60;
            }
            else if(Character.toLowerCase(name) == 'd') //HE10
            {
                return 62;
            }
            else if(Character.toLowerCase(name) == 'e') //HE12
            {
                return 64;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

}   
/*
* Fret Board Note Designation
* High E(E fourth octave): -52 : 4
* Fret 1: F -53 : 4
* Fret 3: G -55 : 4
* Fret 5: A -57 : 4
* Fret 7: B -59 : 4
* Fret 8: C -60 : 5
* Fret 10: D -62 : 5
* Fret 12: E -64
*/

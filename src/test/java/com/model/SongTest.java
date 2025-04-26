package com.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.ArrayList;

//Tested By Ally Blackwell
public class SongTest 
{
    private Song song;
    private Measure measure;

    @Test
    public void testToJFugueStringEmpty()
    {
        song = new Song("Title", "Artist");
        assertEquals("", song.toJFugueString());
    }

    @Test
    public void testToJFugueStringOne()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4, 0, "1");
        song.addNoteToMeasure(0, "C", 4, 1, 0);
        assertEquals(" 48/1.0 |", song.toJFugueString());
    }

    @Test
    public void testToJFugueStringTwo()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4, 0,"1");
        song.addNoteToMeasure(0, "C", 4, 1, 0);
        song.addMeasure(4, 4, 1,"1");
        song.addNoteToMeasure(1, "C", 4, 1, 0);
        assertEquals(" 48/1.0 | 48/1.0 |", song.toJFugueString());
    }

    @Test
    public void testAddNoteToMeasureNormal()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4,0, "1");
        song.addNoteToMeasure(0, "C", 4, 1, 0);
        assertEquals(new Note("C", 4, 1, 0), song.getMeasure(0).getNotes().get(0));
    }

    @Test
    public void testAddNoteToMeasureNull()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4,0, "1");
        song.addNoteToMeasure(0, "null", 4, 1, 0);
        assertEquals(null , song.getMeasure(0).getNotes().get(0));
    }

    @Test
    public void testAddNoteToMeasureIncorrectNoteName()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4,0, "1");
        song.addNoteToMeasure(0, "Q", 4, 1, 0);
        assertEquals(null , song.getMeasure(0).getNotes().get(0));
    }

    @Test
    public void testAddNoteToMeasureIncorrectOctave()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4, 0,"1");
        song.addNoteToMeasure(0, "Q", 50, 1, 0);
        assertEquals(null , song.getMeasure(0).getNotes().get(0));
    }

    @Test
    public void testAddNoteToMeasureNullOctave()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4, 0,"1");
        song.addNoteToMeasure(0, "Q", -1, 1, 0);
        assertEquals(null , song.getMeasure(0).getNotes().get(0));
    }
    
    @Test
    public void testAddMeasureInvalidTop()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(-1, 4, 0,"1");
        assertEquals(null, song.getMeasure(0));
    }

    @Test
    public void testAddMeasureInvalidBottom()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, -1,0, "1");
        assertEquals(null, song.getMeasure(0));
    }

    @Test
    public void testRemoveMeasureNormal()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4,0, "1");
        song.removeMeasure(null);
        measure = new Measure(4, 4, "1", new ArrayList<Note>());
        assertEquals(measure, song.getMeasure(0));
    }

    @Test
    public void testRemoveMeasureTopNotFound()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4, 0,"1");
        measure = new Measure(5, 4, "1", new ArrayList<Note>());
        song.removeMeasure(measure);
        Measure test = new Measure(4, 4, "1", new ArrayList<Note>());
        assertEquals(test,song.getMeasure(0));
    }

    @Test
    public void testRemoveMeasureBottomNotFound()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4, 0,"1");
        measure = new Measure(4, 3, "1", new ArrayList<Note>());
        song.removeMeasure(measure);
        Measure test = new Measure(4, 4, "1", new ArrayList<Note>());
        assertEquals(test,song.getMeasure(0));
    }

    @Test
    public void testRemoveMeasureSigNotFound()
    {
        song = new Song("Title", "Artist");
        song.addMeasure(4, 4,0, "1");
        measure = new Measure(4, 3, "1", new ArrayList<Note>());
        song.removeMeasure(measure);
        Measure test = new Measure(4, 4, "1", new ArrayList<Note>());
        assertEquals(test,song.getMeasure(0));
    }
}

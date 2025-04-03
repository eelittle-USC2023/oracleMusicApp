package com.model;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class MeasureTest 
{
    private Measure measure;
    private ArrayList<Note> notes;

    @Test
    public void testAddNoteNull()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote(null, 4, 1, 0);
        assertEquals(notes.get(0), measure.getNotes().get(0));
    }

    @Test
    public void testAddNoteNote()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 4, 1, 0);
        assertEquals(new Note("A", 4, 1, 0), measure.getNotes().get(0));
    }

    @Test
    public void testAddNoteLowerCase()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("a", 4, 1, 0);
        assertEquals(new Note("A", 4, 1, 0), measure.getNotes().get(0));
    }

    @Test
    public void testAddNoteInvalidName()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("Q", 4, 1, 0);
        assertEquals(notes.get(0), measure.getNotes().get(0));
    }

    @Test
    public void testAddNoteInvalidOctave()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 50, 1, 0);
        assertEquals(notes.get(0), measure.getNotes().get(0));
    }

    @Test
    public void testAddNoteInvalidPosition()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 50, 1, 0);
        measure.addNote("A", 50, 1, 2);
        assertEquals(new Note("R", -1, 1, 1), measure.getNotes().get(2));
    }

    @Test
    public void testRemoveNoteNormal()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 4, 1, 0);
        measure.removeNote(measure.getNotes().get(0));
        assertEquals(notes.get(0), measure.getNotes().get(0));
    }

    @Test
    public void testRemoveNoteNull()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 4, 1, 0);
        measure.removeNote(null);
        assertEquals(new Note("A", 4, 1, 0), measure.getNotes().get(0));
    }

    @Test
    public void testRemoveNoteNameNotFound()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 4, 1, 0);
        measure.removeNote(new Note("B", 4, 1, 0));
        assertEquals(new Note("A", 4, 1, 0), measure.getNotes().get(0));
    }

    @Test
    public void testRemoveNoteOctaveNotFound()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 4, 1, 0);
        measure.removeNote(new Note("A", 5, 1, 0));
        assertEquals(new Note("A", 4, 1, 0), measure.getNotes().get(0));
    }

    @Test
    public void testRemoveNoteLengthNotFound()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 4, 1, 0);
        measure.removeNote(new Note("A", 4, 2, 0));
        assertEquals(new Note("A", 4, 1, 0), measure.getNotes().get(0));
    }
    
    @Test
    public void testRemoveNotePositionNotFound()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("A", 4, 1, 0);
        measure.removeNote(new Note("A", 4, 1, 1));
        assertEquals(new Note("A", 4, 1, 0), measure.getNotes().get(0));
    }

    @Test
    public void testToJFugueStringNull()
    {
        measure = new Measure(4,4,"&", notes);
        String test = measure.toJFugueString();
        assertEquals(null, test);
    }

    @Test
    public void testToJFugueStringNormal()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("C", 4, 1, 0);
        measure.addNote("D", 4, 1,1);
        measure.addNote("E", 4, 1, 2);
        String test = measure.toJFugueString();
        assertEquals(" 48/1.0 50/1.0 52/1.0 ", test);
    }

    @Test
    public void testToJFugueStringChord()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("C", 4, 1, 0);
        measure.addNote("D", 4, 1,0);
        measure.addNote("E", 4, 1, 0);
        String test = measure.toJFugueString();
        assertEquals(" 48/1.0+50/1.0+52/1.0 ", test); 
    }

    @Test
    public void testToJFugueStringMissingPosition()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("C", 4, 1, 0);
        measure.addNote("D", 4, 1,2);
        measure.addNote("E", 4, 1, 1);
        String test = measure.toJFugueString();
        assertEquals(" 48/1.0 52/1.0 50/1.0 ", test);  
    }

    @Test
    public void testToJFugueStringSkipPosition()
    {
        notes = new ArrayList<Note>();
        measure = new Measure(4,4,"&", notes);
        measure.addNote("C", 4, 1, 0);
        measure.addNote("D", 4, 1, 2);
        String test = measure.toJFugueString();
        assertEquals(" 48/1.0 R/1.0 50/1.0 ", test);  
    }

}

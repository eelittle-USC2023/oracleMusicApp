package com.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

//Tested By Ally Blackwell

public class NoteTest 
{
    private Note note;

    @Test
    public void testNoteToJFugueNullName()
    {
        note = new Note(null, 4, 1, 0);
        int test = note.noteToJFugue();
        assertEquals(-3, test);
    }

    @Test
    public void testNoteToJFugueNoteName()
    {
        note = new Note("A", 4, 1, 0);
        int test = note.noteToJFugue();
        assertEquals(57, test);
    }

    @Test
    public void testNoteToJFugueNotNoteName()
    {
        note = new Note("Q", 4, 1, 0);
        int test = note.noteToJFugue();
        assertEquals(-2, test);
    }

    @Test
    public void testNoteToJFugueSharpName()
    {
        note = new Note("A#", 4, 1, 0);
        int test = note.noteToJFugue();
        assertEquals(58, test);
    }

    @Test
    public void testNoteToJFugueFlatName()
    {
        note = new Note("Ab", 4, 1, 0);
        int test = note.noteToJFugue();
        assertEquals(56, test);
    }

    @Test
    public void testNoteToJFugueOctaveOutOfBounds()
    {
        note = new Note("A", 12, 1, 0);
        int test = note.noteToJFugue();
        assertEquals(-4, test);
    }    
}

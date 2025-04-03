package com.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;

/**
 * @author Ethan Little
 */
public class MusicPlayerStatesTest {
    
    @Test
    public void testPlayCompleteSong() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        MusicPlayer player = new MusicPlayer(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, measures));
        player.playSong();
    }

    @Test 
    public void testMusicPlayerNullSong() {
        MusicPlayer player = new MusicPlayer(null);
    }
    // Null Pointer exception

    @Test
    public void testPlaySongNoMeasures() {
        ArrayList<Measure> measures = new ArrayList<Measure>();
        MusicPlayer player = new MusicPlayer(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, measures));
        player.playSong();
    }
    // I don't think it should try to play or even create the player if there's no notes, but this bug doesn't have a huge effect here, at least

    @Test
    public void testPlaySongNullMeasures() {
        MusicPlayer player = new MusicPlayer(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, null));
        player.playSong();
    }
    // NullPointerException

    @Test
    public void testPlaySongMeasuresWithEmptyNotes() {
        ArrayList<Note> notes = new ArrayList<Note>();
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        MusicPlayer player = new MusicPlayer(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, measures));
        player.playSong();
    }
    // I don't think it should try to play or even create the player if there's no notes, but this bug doesn't have a huge effect here, at least

    @Test
    public void testPlaySongMeasuresWithNullNotes() {
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", null));
        measures.add(new Measure(4, 4, "Cmajor", null));
        MusicPlayer player = new MusicPlayer(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, measures));
        player.playSong();
    }
    // NullPointerException

    @Test
    public void testShowCompleteSong() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        MusicPlayer player = new MusicPlayer(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, measures));
        player.showSong();
    }

    @Test
    public void testShowSongNoMeasures() {
        ArrayList<Measure> measures = new ArrayList<Measure>();
        MusicPlayer player = new MusicPlayer(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, measures));
        player.showSong();
    }
    // Shouldn't write to a file if the song has no measures, creates blank file

    @Test
    public void testShowSongMeasuresWithEmptyNotes() {
        ArrayList<Note> notes = new ArrayList<Note>();
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        MusicPlayer player = new MusicPlayer(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, measures));
        player.showSong();
    }
    // Shouldn't write to a file if the measures of a song have no notes, creates file with only measure markings

    @Test
    public void testNoteStateNullMusicPlayer() {
        NoteState noteState = new NoteState(null);
    }
    // Null pointer exception
    @Test
    public void testTabStateNullMusicPlayer() {
        TabState tabState = new TabState(null);
    }
    // Null pointer exception


}

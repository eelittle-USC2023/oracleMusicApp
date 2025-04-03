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
public class DataWriterTest {
    private AccountList accountList = AccountList.getInstance();
    private ArrayList<Account> accounts = accountList.getAccounts();
    private SongList songList = SongList.getInstance();
    private ArrayList<Song> songs = songList.getSongs();

    @Before
    public void setup() {
        AccountList.getInstance().getAccounts().clear();
        DataWriter.savedAccounts(accountList);
        SongList.getInstance().getSongs().clear();
        DataWriter.savedSongs(songList);
    }

    @After
    public void teardown() {
        AccountList.getInstance().getAccounts().clear();
        DataWriter.savedAccounts(accountList);
        SongList.getInstance().getSongs().clear();
        DataWriter.savedSongs(songList);
    }

    @Test 
    public void testWriteZeroAccounts() {
        accounts = DataLoader.getAccounts();
        assertEquals(0, accounts.size());
    }

    @Test
    public void testWriteOneAccounts() {
        accounts.add(new Student("mozartrella", "apple", new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        assertEquals("mozartrella", DataLoader.getAccounts().get(0).getUsername());
    }

    @Test
    public void testWriteFiveAccounts() {
        accounts.add(new Student("mozartrella", "apple", new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        accounts.add(new Student("briethoven", "five5th", new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        accounts.add(new Student("panini", "viola", new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        accounts.add(new Student("jake&finn", "sword", new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        accounts.add(new Student("me", "numberone", new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        assertEquals("me", DataLoader.getAccounts().get(4).getUsername());
    }

    @Test
    public void testWriteAccountEmptyUsername() {
        accounts.add(new Student("", "", new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        assertEquals("", DataLoader.getAccounts().get(0).getUsername());
    }
    // Shouldn't be allowed to write an empty username

    @Test
    public void testWriteAccountNullUsername() {
        accounts.add(new Student(null, null, new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        assertEquals(null, DataLoader.getAccounts().get(0).getUsername());
    }
    // Shouldn't be allowed to write a null username

    @Test
    public void testWriteAccountEmptyPassword() {
        accounts.add(new Student("", "", new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        assertEquals("", DataLoader.getAccounts().get(0).getPassword());
    }
    // Shouldn't be allowed to write an empty password

    @Test
    public void testWriteAccountNullPassword() {
        accounts.add(new Student(null, null, new ArrayList<Song>(), 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        assertEquals(null, DataLoader.getAccounts().get(0).getPassword());
    }
    // Shouldn't be allowed to write a null password

    @Test
    public void testWriteOneAccountSavedSong() {
        ArrayList<Song> savedSongs = new ArrayList<Song>();
        Song song = new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
        "me", "None", "None", new Guitar(), 120, new ArrayList<Measure>());
        savedSongs.add(song);
        accounts.add(new Student("mozartrella", "apple", savedSongs, 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        songs.add(song);
        assertEquals(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), 
            ((Student)DataLoader.getAccounts().get(0)).getSavedSongs().get(0).getID());
    }

    @Test
    public void testWriteAccountEmptySavedSongs() {
        ArrayList<Song> savedSongs = new ArrayList<Song>();
        accounts.add(new Student("mozartrella", "apple", savedSongs, 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        assertEquals(0, ((Student)DataLoader.getAccounts().get(0)).getSavedSongs().size());
    }
    // Empty saved songs is allowed

    @Test
    public void testWriteAccountNullSavedSongs() {
        accounts.add(new Student("mozartrella", "apple", null, 
            new ArrayList<Lesson>(), new ArrayList<Assignment>(), new ArrayList<Achievement>(), new ArrayList<Song>(), new ArrayList<Lesson>()));
        DataWriter.savedAccounts(accountList);
        assertEquals(null, ((Student)DataLoader.getAccounts().get(0)).getSavedSongs());
    }
    // Null savedSongs causes exception, so shouldn't try to write it. And null shouldn't exist, so doesn't need to be allowed

    @Test
    public void testWriteOneSong() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
            "me", "None", "None", new Guitar(), 120, measures));
        DataWriter.savedSongs(songList);
        assertEquals(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), DataLoader.getSongs().get(0).getID());
    }

    @Test
    public void testWriteThreeSongs() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
            "me", "None", "None", new Guitar(), 120, measures));
        songs.add(new Song(UUID.fromString("db29ba2e-d7df-4147-bb3e-55a56095f6ff"), "A Hoarse Journey", 
            "you", "None", "None", new Guitar(), 120, measures));
        songs.add(new Song(UUID.fromString("9b362387-2cb1-4f45-aad4-b3d672095c48"), "A Horze Journey", 
            "someone", "None", "None", new Guitar(), 120, measures));
        DataWriter.savedSongs(songList);
        assertEquals(UUID.fromString("9b362387-2cb1-4f45-aad4-b3d672095c48"), DataLoader.getSongs().get(2).getID());
    }

    @Test
    public void testWriteOneSongNullMeasures() {
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
            "me", "None", "None", new Guitar(), 120, null));
        DataWriter.savedSongs(songList);
        assertEquals(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), DataLoader.getSongs().get(0).getID());
    }
    // Null pointer exception with null measures, so shouldn't try to write null measures

    @Test
    public void testWriteOneSongNoMeasures() {
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
            "me", "None", "None", new Guitar(), 120, new ArrayList<Measure>()));
        DataWriter.savedSongs(songList);
        assertEquals(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), DataLoader.getSongs().get(0).getID());
    }
    // Empty measure list can happen legally, like when a user creates a new song but doesn't add any measures, so that's allowed

    @Test
    public void testWriteOneSongNullNotes() {
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", null));
        measures.add(new Measure(4, 4, "Cmajor", null));
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
            "me", "None", "None", new Guitar(), 120, measures));
        DataWriter.savedSongs(songList);
        assertEquals(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), DataLoader.getSongs().get(0).getID());
    }
    // Null pointer exception with null notes, so shouldn't try to write null notes


    @Test
    public void testWriteOneSongEmptyNotes() {
        ArrayList<Note> notes = new ArrayList<Note>();
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
            "me", "None", "None", new Guitar(), 120, new ArrayList<Measure>()));
        DataWriter.savedSongs(songList);
        assertEquals(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), DataLoader.getSongs().get(0).getID());
    }
    // Empty note list can happen, like when a user creates a new measure but doesn't add any notes, so that's allowed

    @Test
    public void testWriteOneSongNullID() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        songs.add(new Song(null, "A Horse Journey", 
            "me", "None", "None", new Guitar(), 120, measures));
        DataWriter.savedSongs(songList);
        assertEquals(null, DataLoader.getSongs().get(0).getID());
    }
    // Null ID throws exceptions, so shouldn't try to write null ID

    @Test
    public void testWriteOneSongEmptyTitle() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "", 
            "me", "None", "None", new Guitar(), 120, measures));
        DataWriter.savedSongs(songList);
        assertEquals("", DataLoader.getSongs().get(0).getTitle());
    }
    // Is an empty title okay? I don't see why not

    @Test
    public void testWriteOneSongNullTitle() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), null, 
            "me", "None", "None", new Guitar(), 120, measures));
        DataWriter.savedSongs(songList);
        assertEquals(null, DataLoader.getSongs().get(0).getTitle());
    }
    // Shouldn't allow null title, I think

    @Test
    public void testWriteOneSongEmptyArtistName() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey", 
            "", "None", "None", new Guitar(), 120, measures));
        DataWriter.savedSongs(songList);
        assertEquals("", DataLoader.getSongs().get(0).getArtistName());
    }
    // Shouldn't allow empty artistName

    @Test
    public void testWriteOneSongNullArtistName() {
        ArrayList<Note> notes = new ArrayList<Note>();
        notes.add(new Note("C", 4, 1, 0));
        notes.add(new Note("E", 4, 1, 1));
        notes.add(new Note("G", 4, 1, 2));
        notes.add(new Note("C", 4, 1, 3));
        ArrayList<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure(4, 4, "Cmajor", notes));
        measures.add(new Measure(4, 4, "Cmajor", notes));
        songs.add(new Song(UUID.fromString("708aeef0-d6c2-4cef-82d9-1bd54db516fe"), "A Horse Journey",
            null, "None", "None", new Guitar(), 120, measures));
        DataWriter.savedSongs(songList);
        assertEquals(null, DataLoader.getSongs().get(0).getArtistName());
    }
    // Shouldn't allow null artistName
}

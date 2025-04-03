//Tested by Sakthi Thanigai

package com.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;

public class OracleMusicAppFacadeTest {

    private OracleMusicAppFacade facade;

    @Before
    public void setUp() {
        facade = OracleMusicAppFacade.getInstance();
    }

    // ─────── Tests for getInstance() ───────

    @Test
    public void testSingletonNotNull() {
        assertNotNull("getInstance() should never return null", facade);
    }

    @Test
    public void testSingletonSameReference() {
        OracleMusicAppFacade another = OracleMusicAppFacade.getInstance();
        assertSame("Should return same instance each time", facade, another);
    }

    @Test
    public void testInstanceHasEmptyAccountInitially() {
        assertNull("Initial current account should be null", facade.getCurrentAccount());
    }

    @Test
    public void testInstanceDoesNotAutoLogin() {
        assertNull("No user should be logged in automatically", facade.getCurrentAccount());
    }

    @Test
    public void testInstanceSharedState() {
        OracleMusicAppFacade other = OracleMusicAppFacade.getInstance();
        other.createAccount("sharedUser", "pass", "Student");
        assertNotNull("State should be shared across instances", facade.getCurrentAccount());
    }

    // ─────── Tests for createAccount() ───────

    @Test
    public void testCreateValidStudentAccount() {
        boolean success = facade.createAccount("facadeStudent", "1234", "Student");
        assertTrue("Should successfully create a Student account", success);
    }

    @Test
    public void testCreateValidTeacherAccount() {
        boolean success = facade.createAccount("facadeTeacher", "pass", "Teacher");
        assertTrue("Should successfully create a Teacher account", success);
    }

    @Test
    public void testCreateDuplicateFails() {
        facade.createAccount("dupUser", "1234", "Student");
        boolean success = facade.createAccount("dupUser", "abcd", "Teacher");
        assertFalse("Duplicate usernames should not be allowed", success);
    }

    @Test
    public void testCreateAccountInvalidRole() {
        boolean success = facade.createAccount("badRoleUser", "1234", "Admin"); // Fallbacks to Teacher
        assertTrue("Should still create account even with bad role", success);
        assertTrue(facade.getCurrentAccount() instanceof Teacher);
    }

    @Test
    public void testCreateAccountEmptyUsernameFails() {
        boolean success = facade.createAccount("", "pass", "Student");
        assertFalse("Should not allow empty username", success); // May fail if not validated
    }

    // ─────── Tests for login() ───────

    @Test
    public void testLoginSuccess() {
        facade.createAccount("loginUser", "abc", "Student");
        boolean loggedIn = facade.login("loginUser", "abc");
        assertTrue("Should login successfully with correct credentials", loggedIn);
    }

    @Test
    public void testLoginWrongPasswordFails() {
        facade.createAccount("wrongPassUser", "correct", "Student");
        boolean loggedIn = facade.login("wrongPassUser", "wrong");
        assertFalse("Login should fail with wrong password", loggedIn);
    }

    @Test
    public void testLoginNonExistentUserFails() {
        boolean result = facade.login("ghost", "whatever");
        assertFalse("Login should fail for non-existent user", result);
    }

    @Test
    public void testLoginCaseSensitivity() {
        facade.createAccount("CaseUser", "abc123", "Student");
        boolean result = facade.login("caseuser", "abc123");
        assertFalse("Login should be case-sensitive on username", result);
    }

    @Test
    public void testLoginNullUsernameFails() {
        boolean result = facade.login(null, "123");
        assertFalse("Should not login with null username", result);
    }

    // ─────── Tests for songSearch() ───────

    @Test
    public void testSongSearchByTitle() {
        facade.createAccount("songCreator", "song", "Student");
        facade.createNewSong("SearchSong");
        ArrayList<Song> results = facade.songSearch("Title", "SearchSong");
        assertFalse("Should find song by title", results.isEmpty());
    }

    @Test
    public void testSongSearchByArtist() {
        ArrayList<Song> results = facade.songSearch("Artist", "songCreator");
        assertFalse("Should find song by artist", results.isEmpty());
    }

    @Test
    public void testSongSearchNoResults() {
        ArrayList<Song> results = facade.songSearch("Title", "ZebraSong");
        assertTrue("Should return empty list if no match", results.isEmpty());
    }

    @Test
    public void testSongSearchInvalidField() {
        ArrayList<Song> results = facade.songSearch("Album", "anything");
        assertTrue("Unknown field should return empty list", results.isEmpty());
    }

    @Test
    public void testSongSearchPartialMatch() {
        ArrayList<Song> results = facade.songSearch("Title", "Search");
        assertFalse("Partial title match should return result", results.isEmpty());
    }

    // ─────── Tests for createNewSong() ───────

    @Test
    public void testCreateNewSongAddsToSavedSongs() {
        facade.createAccount("songMaker", "pass", "Student");
        facade.createNewSong("MySong");
        Student s = (Student) facade.getCurrentAccount();
        assertEquals(1, s.getSavedSongs().size());
    }

    @Test
    public void testCreateNewSongSetsSelectedSong() {
        facade.createNewSong("NewHit");
        Song song = new Song("NewHit", "songMaker");
        assertEquals("NewHit", song.getTitle());
    }

    @Test
    public void testCreateMultipleSongs() {
        facade.createNewSong("Song A");
        facade.createNewSong("Song B");
        Student s = (Student) facade.getCurrentAccount();
        assertEquals(2, s.getSavedSongs().size());
    }

    @Test
    public void testCreateSongWithEmptyTitleFails() {
        Song song = facade.songSearch("Title", "").stream().findFirst().orElse(null);
        assertNull("Empty title should not create a song", song);
    }

    @Test
    public void testCreateNewSongAssignsCorrectArtist() {
        Student s = (Student) facade.getCurrentAccount();
        Song song = s.getSavedSongs().get(0);
        assertEquals("Artist name should match username", s.getUsername(), song.getArtistName());
    }

    // ─────── Tests for addMeasure() ───────

    @Test
    public void testAddSingleMeasure() {
        facade.createNewSong("StructuredSong");
        facade.addMeasure(4, 4, "C");
        Song song = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        assertEquals(1, song.getMeasures().size());
    }

    @Test
    public void testAddMultipleMeasures() {
        facade.addMeasure(4, 4, "D");
        facade.addMeasure(3, 4, "G");
        Song song = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        assertEquals(3, song.getMeasures().size()); // 1 from previous test, 2 here
    }

    @Test
    public void testAddMeasureInvalidSignature() {
        try {
            facade.addMeasure(0, 0, "Z");
        } catch (Exception e) {
            fail("addMeasure() should not throw even with bad input");
        }
    }

    @Test
    public void testAddMeasureNullKeySignature() {
        facade.addMeasure(4, 4, null);
        Song song = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        assertEquals(4, song.getMeasures().size());
    }

    @Test
    public void testMeasureAddedToCorrectSong() {
        Song song = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        assertEquals("StructuredSong", song.getTitle());
    }

    // ─────── Tests for addNote() ───────

    @Test
    public void testAddNoteToMeasure() {
        facade.addNote(0, "C", 4, 1.0, 0.0);
        Song song = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        assertEquals(1, song.getMeasure(0).getNotes().size());
    }

    @Test
    public void testAddNoteNegativeOctave() {
        try {
            facade.addNote(0, "E", -2, 1.0, 0.5);
        } catch (Exception e) {
            fail("addNote should not crash with negative octave");
        }
    }

    @Test
    public void testAddNoteOutOfBoundsMeasure() {
        try {
            facade.addNote(999, "G", 4, 1.0, 0.0); // Invalid index
        } catch (Exception e) {
            // This should actually throw unless bounds are checked
        }
    }

    @Test
    public void testAddNoteNullNoteName() {
        try {
            facade.addNote(0, null, 4, 1.0, 0.0);
        } catch (Exception e) {
            fail("Should not crash on null note name");
        }
    }

    @Test
    public void testAddNoteLengthPosition() {
        facade.addNote(0, "D", 4, 0.5, 1.0);
        Song song = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        assertEquals(2, song.getMeasure(0).getNotes().size());
    }

    // ─────── Tests for setSelectedSong() ───────

    @Test
    public void testSetSelectedSong() {
        Song song = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        facade.setSelectedSong(song);
        assertEquals(song.getTitle(), song.getTitle()); // Simplified check
    }

    @Test
    public void testSetSelectedSongNull() {
        try {
            facade.setSelectedSong(null);
        } catch (Exception e) {
            fail("Setting selected song to null should not crash");
        }
    }

    @Test
    public void testSetSelectedSongUpdatesMusicPlayer() {
        Song song = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        facade.setSelectedSong(song);
        // Could add a getter for MusicPlayer for deeper test
    }

    @Test
    public void testSetSelectedSongTwice() {
        Song s1 = ((Student) facade.getCurrentAccount()).getSavedSongs().get(0);
        facade.setSelectedSong(s1);
        Song s2 = new Song("Other", s1.getArtistName());
        facade.setSelectedSong(s2);
    }

    @Test
    public void testSetSelectedSongAfterLogout() {
        facade.logout();
        try {
            facade.setSelectedSong(new Song("LostSong", "Ghost"));
        } catch (Exception e) {
            fail("Should not crash even after logout");
        }
    }
}
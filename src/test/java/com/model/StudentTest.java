//Tested by Sakthi Thanigai

package com.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.UUID;

public class StudentTest {

    private Student student;

    @Before
    public void setUp() {
        student = new Student("student1", "pass123");
    }

    // ─────── Tests for addCourse() ───────

    // Add a valid course
    @Test
    public void testAddCourseSuccess() {
        Course course = new Course("Theory 101", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        boolean added = student.addCourse(course);
        assertTrue("Course should be added", added);
    }

    // Add the same course again — should fail
    @Test
    public void testAddCourseDuplicateFails() {
        UUID id = UUID.randomUUID();
        Course c1 = new Course("Harmony", id, new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        Course c2 = new Course("Harmony Clone", id, new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        student.addCourse(c1);
        boolean added = student.addCourse(c2);
        assertFalse("Duplicate course should not be added", added);
    }

    // Add course already in invited list — should fail
    @Test
    public void testAddCourseInInvitedListFails() {
        UUID id = UUID.randomUUID();
        Course course = new Course("Jazz", id, new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        student.addCourseInvitedTo(course);
        boolean added = student.addCourse(course);
        assertFalse("Should not add course already in invited list", added);
    }

    // Add null course (should fail or be handled)
    @Test
    public void testAddCourseNull() {
        boolean result = student.addCourse(null);
        assertFalse("Null course should not be added", result); // May fail if not null-checked
    }

    // Add multiple unique courses
    @Test
    public void testAddMultipleUniqueCourses() {
        Course c1 = new Course("C1", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        Course c2 = new Course("C2", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        assertTrue(student.addCourse(c1));
        assertTrue(student.addCourse(c2));
        assertEquals(2, student.getCourses().size());
    }

    // ─────── Tests for addCourseInvitedTo() ───────

    // Add valid invite course
    @Test
    public void testAddCourseInvitedSuccess() {
        Course course = new Course("Invited Course", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        boolean added = student.addCourseInvitedTo(course);
        assertTrue("Should add to invited courses", added);
    }

    // Add same course twice to invited — should fail
    @Test
    public void testAddCourseInvitedDuplicateFails() {
        UUID id = UUID.randomUUID();
        Course course1 = new Course("Duplicate", id, new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        student.addCourseInvitedTo(course1);
        boolean addedAgain = student.addCourseInvitedTo(course1);
        assertFalse("Duplicate invite should not be added", addedAgain);
    }

    // Add a course that's already in enrolled courses — should fail
    @Test
    public void testAddCourseInvitedAlreadyEnrolled() {
        UUID id = UUID.randomUUID();
        Course course = new Course("Enrolled", id, new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        student.addCourse(course);
        boolean invited = student.addCourseInvitedTo(course);
        assertFalse("Course already enrolled should not be added to invited", invited);
    }

    // Add null course to invited — should fail
    @Test
    public void testAddCourseInvitedNull() {
        boolean added = student.addCourseInvitedTo(null);
        assertFalse("Null invited course should not be added", added); // May fail if not null-checked
    }

    // Add multiple unique invites
    @Test
    public void testAddMultipleInvites() {
        Course c1 = new Course("Invite1", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        Course c2 = new Course("Invite2", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        assertTrue(student.addCourseInvitedTo(c1));
        assertTrue(student.addCourseInvitedTo(c2));
    }

    // ─────── Tests for getCourses() ───────

    @Test
    public void testGetCoursesEmptyInitially() {
        assertTrue("New student should have no courses", student.getCourses().isEmpty());
    }

    @Test
    public void testGetCoursesAfterAdding() {
        Course c = new Course("Theory", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        student.addCourse(c);
        assertEquals("Should contain one course", 1, student.getCourses().size());
    }

    @Test
    public void testGetCoursesDoesNotIncludeInvited() {
        Course c = new Course("InvitedOnly", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        student.addCourseInvitedTo(c);
        assertTrue("Invited course should not appear in getCourses()", student.getCourses().isEmpty());
    }

    @Test
    public void testGetCoursesReturnsCorrectCourses() {
        Course c = new Course("Music", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        student.addCourse(c);
        assertEquals("First course name should match", "Music", student.getCourses().get(0).getCourseName());
    }

    @Test
    public void testGetCoursesNotNull() {
        assertNotNull("getCourses() should never return null", student.getCourses());
    }

    // ─────── Tests for addSavedSong() ───────

    @Test
    public void testAddSavedSongOnce() {
        Song song = new Song("Track1", "student1");
        student.addSavedSong(song);
        assertEquals(1, student.getSavedSongs().size());
    }

    @Test
    public void testAddSavedSongTwice() {
        Song song = new Song("Repeat", "student1");
        student.addSavedSong(song);
        student.addSavedSong(song);
        assertEquals(2, student.getSavedSongs().size()); // Does not prevent duplicates
    }

    @Test
    public void testAddNullSavedSong() {
        try {
            student.addSavedSong(null); // If null not handled, this could crash
        } catch (Exception e) {
            fail("Should not throw exception when adding null song");
        }
    }

    @Test
    public void testSavedSongsListNotNull() {
        assertNotNull("Saved songs list should not be null", student.getSavedSongs());
    }

    @Test
    public void testAddMultipleSavedSongs() {
        Song s1 = new Song("A", "student1");
        Song s2 = new Song("B", "student1");
        student.addSavedSong(s1);
        student.addSavedSong(s2);
        assertEquals(2, student.getSavedSongs().size());
    }

    // ─────── Tests for toString() ───────

    @Test
    public void testToStringIncludesUsername() {
        String result = student.toString();
        assertTrue("toString() should include username", result.contains("student1"));
    }

    @Test
    public void testToStringIncludesCourse() {
        Course c = new Course("Theory", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>());
        student.addCourse(c);
        String result = student.toString();
        assertTrue("toString() should include course name", result.contains("Theory"));
    }

    @Test
    public void testToStringEmptyCourseList() {
        String result = student.toString();
        assertEquals("student1 ", result); // Expected if no courses added
    }

    @Test
    public void testToStringMultipleCourses() {
        student.addCourse(new Course("A", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>()));
        student.addCourse(new Course("B", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>()));
        String result = student.toString();
        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
    }

    @Test
    public void testToStringDoesNotIncludeInvitedCourses() {
        student.addCourseInvitedTo(new Course("InvitedOnly", UUID.randomUUID(), new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>()));
        String result = student.toString();
        assertFalse("Invited courses should not appear in toString()", result.contains("InvitedOnly"));
    }
}
package com.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


public class DataLoaderTest {
    @Test
    public void testLoadValidAccounts(){
        ArrayList<Account> accounts = DataLoader.getAccounts();
        assertNotNull("The account list shouldn't be null", accounts);
        assertFalse("The account list shouldn't be empty",accounts.isEmpty());

        Account account = accounts.get(0);
        assertNotNull("First account shouldn't be null",account);
        assertNotNull("Account username should not be null", account.getUsername());
        assertNotNull("Account password should not be null", account.getPassword());
    }
    @Test
    public void testLoadValidLessons(){
        ArrayList<Lesson> lessons = DataLoader.getLessons();
        assertNotNull("Lesson List shouldn't be null", lessons);
        assertFalse("Lesson list should not be empty", lessons.isEmpty());

        Lesson lesson = lessons.get(0);
        assertNotNull("First lesson shouldn't be null", lesson);
        assertNotNull("Lesson title shouldn't be null", lesson.getTitle());
        assertNotNull("Lesson ID should not be null", lesson.getID());
    }
    @Test
    public void testLoadValidQuestions(){
        ArrayList<Question> questions = DataLoader.getQuestions();
        assertNotNull("The question list should not be null", questions);
        assertFalse("Question list shouldn't be emoty", questions.isEmpty());

        Question question = questions.get(0);
        assertNotNull("First question shouldn't be null", question);
        assertNotNull(" Question text should not be null", question.getQuestionText());
        assertNotNull("Question choices shouldn't be empty", question.getAnswerChoices().isEmpty());
    }
    @Test 
    public void testLoadValidCourses(){
        ArrayList<Account> accounts = DataLoader.getAccounts();
        assertNotNull("Account list shouldn't be null", accounts);
    }
    public void testLoadvalidMeasures(){
        ArrayList<Song> songs = DataLoader.getSongs();
        assertNotNull("song list shouldn't be null",songs);
        assertFalse("song list shouldn't be empty", songs.isEmpty());

        Song song = songs.get(0);
        assertNotNull("First song should not be null", song);
        assertFalse("Song should have some kind of measure",song.getMeasures().isEmpty());

        Measure measure = song.getMeasures().get(0);
        assertNotNull("First measure shouldn't be null", measure);
        assertTrue("Measure should have valid time signature", measure.getTimeSignatureTop()>0);
        assertNotNull("Measure should have valid key signature", measure.getKeySignature());
    }
    @Test 
    public void testNoEmptyPasswords(){
        ArrayList<Account> accounts = DataLoader.getAccounts();
        for (Account account : accounts){
            assertTrue("Password should not be empty",account.getPassword().length()>0);
        }
    }
    @Test void testTeacherAccountsHaveCourses(){
        ArrayList <Account> accounts = DataLoader.getAccounts();
        long teachersWithCourses = accounts.stream().filter( a -> a instanceof Teacher).map(a ->(Teacher)a).filter(t -> !t.getCourses().isEmpty()).count();
        assertEquals("All teacher should have at least one course", teachersWithCourses);
    }
    @Test
    public void testCourseLoading(){
        ArrayList<Account> accounts = DataLoader.getAccounts();
        boolean hasCourses = accounts.stream().anyMatch(a -> a instanceof Student && !((Student)a).getCourses().isEmpty());
          assertTrue("At least one student should be enrolled in course", hasCourses);
        }

    @Test
    public void testQuestionAnswerNotEmpty(){
        ArrayList<Question> questions = DataLoader.getQuestions();
        for (Question q : questions){
            assertFalse("Question should have anseer choices",q.getAnswerChoices().isEmpty());
        }
    }
  
}

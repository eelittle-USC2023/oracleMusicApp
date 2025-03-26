package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class DataWriterTest {
    public static void main (String [] args){
        testCreateAccount();
        testSaveAccounts();
        testSaveSongs();
        testSaveLessons();
        testSaveQuestions();
    }
    public static void testCreateAccount(){
        AccountList accountList = AccountList.getInstance();
        boolean succesfully = accountList.addAccount("jake&finn,", "PPassword","Student");
        succesfully = accountList.addAccount("ffred","ffredpassword", "Student");
        accountList.save();
        System.out.println("Accounts saved successfully");
    }
    public static void testSaveAccounts(){
        System.out.println("Testing of saving Accounts");
        AccountList accountList = AccountList.getInstance();
        accountList.addAccount("TestUser1", "password123","Student");
        accountList.addAccount("TestTeacher1","Goodpassword1","Teacher");
        accountList.save();
        System.out.println("Accounts successfully saved");
    }
    public static void testSaveSongs(){
        System.out.println("Testing of saved songs");
        SongList songList = SongList.getInstance();
        Instrument guitar = new Guitar();
        ArrayList<Measure> measures = new ArrayList<>();
        measures.add(new Measure(4,4,"C Major", new ArrayList<>()));
        songList.addSong("Test Song", "Test Artist", "Easy", "Rock", guitar, measures);
        songList.save();
        System.out.println("Songs successfully saved");
        System.out.println("Saving songs...");
        for (Song song : songList.getSongs()) {
            System.out.println("Song: " + song.getTitle());
        }
    }
    public static void testSaveLessons(){
        System.out.println("Testing of save lessons");
        LessonList lessonList = LessonList.getInstance();
        UUID question1 = UUID.randomUUID();
        UUID question2 = UUID.randomUUID();
        ArrayList<UUID> questionIDs = new ArrayList<>();
        questionIDs.add(question1);
        questionIDs.add(question2);

        ArrayList<String> text = new ArrayList<>();
        text.add("Example lesson on reading sheet music");
        text.add("Notes to represent pitch");
        Lesson newLesson = new Lesson(UUID.randomUUID(),"Reading Sheet Music", new ArrayList<>(), text);
        lessonList.getLessons().add(newLesson);
        lessonList.save();
        System.out.println("Lessons saved successfully");
      
    }
    public static void testSaveQuestions(){
        System.out.println("Testing of saved questions");
        QuestionList questionList = QuestionList.getInstance();
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Answer A");
        choices.add("Answer B");
        choices.add("Answer C");
        Question newQuestion = new Question("Whats 3x3", choices, "Answer B", 10,"Good Job", "think about simple mutiplication");
        questionList.getQuestions().add(newQuestion);
        questionList.save();
        System.out.println("Question saved successfull");
    }
}

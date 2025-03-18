package com.model;
import java.util.ArrayList;

import javax.security.auth.login.AccountException;

public class OracleMusicAppFacade 
{
    private Account currentAccount;
    private AccountList accounts;
    private SongList songs;
    private LessonList lessons;
    private SongCreator songCreator;
    private MusicPlayer musicPlayer;
    private static OracleMusicAppFacade facade;

    private OracleMusicAppFacade()
    {

    }
    public static OracleMusicAppFacade getInstance()
    {
        OracleMusicAppFacade temp = new OracleMusicAppFacade();
        return temp;
    }
    public ArrayList<Account> getAccounts()
    {
        ArrayList<Account> temp = new ArrayList<Account>();
        return temp;
    }
    public ArrayList<Song> getSongs()
    {
        ArrayList<Song> temp = new ArrayList<Song>();
        return temp;
    }
    public ArrayList<Lesson> getLessons()
    {
        ArrayList<Lesson> temp = new ArrayList<Lesson>();
        return temp;
    }
    public Account createAccount(String username, String password)
    {
        Account temp = new Account();
        return temp;
    }
    public boolean login(String username, String password)
    {
        return true;
    }
    public void logout()
    {

    }
    public ArrayList<Song> songSearch(String keyword)
    {
        ArrayList<Song> temp = new ArrayList<Song>();
        return temp;
    }
    public void playSong()
    {

    }
    public Song makeSong()
    {
        Song temp = new Song();
        return temp;
    }
    public void viewLesson()
    {

    }
    public boolean answerQuestion()
    {
        return true;
    }
    public void createCourse()
    {

    }
    public void disbandCourse(Course course)
    {
    
    }
    public void inviteStudent(Student student)
    {

    }
    public void removeStudent(Student student)
    {

    }
    public void acceptInvite(Course course)
    {

    }
    public void declineInvite(Course course)
    {

    }
    public void assignStudentLesson(Lesson lesson)
    {

    }
    public void assignStudentSong(Song song)
    {

    }
    public void saveAll()
    {

    }
}

package com.model;
import java.util.ArrayList;

import javax.security.auth.login.AccountException;

public class OracleMusicAppFacade 
{
    private Account currentAccount;
    private AccountList accountList;
    private SongList songList;
    private LessonList lessonList;
    private QuestionList questionList;
    private SongCreator songCreator;
    private MusicPlayer musicPlayer;
    private static OracleMusicAppFacade facade;

    /**
     * Private constructor called when getInstance is called for the first time.
     * Sets up all the lists.
     * @author Ethan Little
     */
    private OracleMusicAppFacade()
    {
        questionList = QuestionList.getInstance();
        lessonList = LessonList.getInstance();
        songList = SongList.getInstance();
        accountList = AccountList.getInstance();
    }
    public static OracleMusicAppFacade getInstance()
    {
        if (facade == null) {
           facade = new OracleMusicAppFacade();
        }
        return facade;
    }
    public Account getCurrentAccount() {
        return currentAccount;
    }
    
    public boolean createAccount(String username, String password, String role){
        boolean successful = accountList.addAccount(username, password, role);
        return successful;
    }
    public boolean login(String username, String password)
    {
        Account account = accountList.getAccount(username);
        if (account == null) {
            return false;
        }
        return account.isCorrectPassword(password);
    }
    public void logout(){
        //Call all list save methods
    }
    public ArrayList<Song> songSearch(String keyword)
    {
        ArrayList<Song> temp = new ArrayList<Song>();
        return temp;
    }
    public void playSong()
    {

    }
    public void makeSong(String title) {

    }
    //Everything below this point won't be implemented this sprint
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
}

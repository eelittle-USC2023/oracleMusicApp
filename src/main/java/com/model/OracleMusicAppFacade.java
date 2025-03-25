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
    public boolean createAccount(String username, String password, String role){
        boolean successful = accountList.addAccount(username, password, role);

        if (successful){
            System.out.println("Your account has successfully been created!");
            return true;
        }else {
            System.out.println("This username alreadys exists, please choose a different one.");
            return false;
        }
    }
    public boolean login(String username, String password)
    {
        Account account = accountList.getAccount(username);
        if (account != null && account.getPassword().equals(password)){
            System.out.println("Your login was successful welcome ," + username);
            return true;
        }
        System.out.println("Invalid username or password.");
        return false;
    }
    public void logout(){
        if (currentAccount != null) {
            System.out.println("You've logged out:" + currentAccount.getUsername());
            currentAccount = null;
        } else {
            System.out.println("There is no user currently logged in.");
        }
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
        Guitar guitar = new Guitar();
        Song temp = new Song("title", guitar, "", "", "");
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
    public void saveAll(){
        DataWriter.savedAccounts(accountList);
        DataWriter.savedSongs(songList);
        DataWriter.savedLessons(lessonList);
        System.out.println("All your changes are saved!");
    }
}

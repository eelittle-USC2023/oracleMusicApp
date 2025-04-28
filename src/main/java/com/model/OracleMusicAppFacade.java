package com.model;
import java.util.ArrayList;

public class OracleMusicAppFacade 
{
    private Account currentAccount;
    private AccountList accountList;
    private SongList songList;
    private LessonList lessonList;
    private QuestionList questionList;
    private Song selectedSong;
    private MusicPlayer musicPlayer;
    private static OracleMusicAppFacade facade;

    /**
     * This is called when getInstance is called for the first time.
     * Sets up all the lists, then makes sure the currentAccount, musicPlayer and selectedSong are null 
     * because no one has logged in or made a song.
     * @author Ethan Little
     */
    private OracleMusicAppFacade()
    {
        currentAccount = null;
        selectedSong = null;
        musicPlayer = null;
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
    /**
     * Returns the current selected song
     * @return the selected song
     * @author Ally Blackwell
     */
    public Song getSelectedSong() {
        return selectedSong;
    }
    
    public boolean createAccount(String username, String password, String role){
        boolean successful = accountList.addAccount(username, password, role);
        if(successful){
            currentAccount = accountList.getAccount(username);
        }
        return successful;
    }
    /**
     * This method calls the getAccount method from the accountList. 
     * If getAccount returns null, then the username is incorrect.
     * Otherwise, the getAccount returns an account. Then, this method calls the isCorrectPassword method on that account.
     * If that returns true, then the account becomes the current account and this method returns true. 
     * Otherwise, the method returns false. 
     * @param username The username that is attempting to login.
     * @param password The password that the username is attempting to use.
     * @return boolean value determining whether or not the login was succesful.
     * @author Ethan Little and James Lyles
     */
    public boolean login(String username, String password)
    {
        Account account = accountList.getAccount(username);
        if (account == null) {
            return false;
        }
        if (account.isCorrectPassword(password)) {
            currentAccount = account;
            return true;
        }
        return false;
    }
    /**
     * Alters the current title of the selected song
     * @param title the desired title
     * @author Ally Blackwell
     */
    public void setSelectedSongTitle(String title)
    {
        selectedSong.setTitle(title);
    }

    public void logout(){
        accountList.save();
        songList.save();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    }
    /**
     * Calls the songList searchSongs method.
     * @param field The field of the song being searched.
     * @param search What in the field is being searched for.
     * @return Songs matching the search.
     * @author Ethan Little
     */
    public ArrayList<Song> songSearch(String field, String search)
    {
        return songList.searchSongs(field, search);
    }
    /**
     * Plays the current selected song using JFugue
     * @author Ally Blackwell
     */
    public void playSong()
    {
        if (musicPlayer == null) {
            musicPlayer = new MusicPlayer(selectedSong);
        }
        musicPlayer.setSong(selectedSong);
        musicPlayer.playSong();
    }

    public void createNewSong(String title) {
        selectedSong = songList.addSong(title, currentAccount.getUsername());
        ((Student)currentAccount).addSavedSong(selectedSong);
    }
    public void addMeasure(int timeSignatureTop, int timeSignatureBottom, String keySignature) {
        selectedSong.addMeasure(timeSignatureTop, timeSignatureBottom, keySignature);
    }
    public void addNote(int measureIndex, String name, int octave, double length, double position) {
        selectedSong.addNoteToMeasure(measureIndex, name, octave, length, position);
    }
    /**
     * Selects the note button and sends the notes of the song to the designated file
     * @author Ally Blackwell
     */
    public void displayNotes()
    {
        if (musicPlayer == null) {
            musicPlayer = new MusicPlayer(selectedSong);
        }
        musicPlayer.pressNoteButton();
        musicPlayer.showSong();
    }
    /**
     * Selects the tab button and sends the songs tabs to the designated file
     * @author Ally Blackwell
     */
    public void displayTabs()
    {
        if(musicPlayer == null)
        {
            musicPlayer = new MusicPlayer(selectedSong);
        }
        musicPlayer.pressTabButton();
        musicPlayer.showSong();
    }
    /**
     * Sets the selected song to the inputed song
     * @param s the inputted song
     * @author Ally Blackwell
     */
    public void setSelectedSong(Song s)
    {
        this.selectedSong = s;
        if(musicPlayer == null)
        {
            musicPlayer = new MusicPlayer(selectedSong);
        }
        musicPlayer.setSong(selectedSong);
    }

    /**
     * Adds a 'strum' to the current song or overwrites at the current index
     * @param index the desired measure to place this strum
     * @param guitar the tabs of the strum
     * @author Ally Blackwell
     */
    public void addGuitarToMeasure(int index, Guitar guitar)
    {
        selectedSong.addTabToMeasure(index, guitar);
    }
    /**
     * Returns the pure string tab list for the play screen
     * @return the array list of array list of tabs
     * @author Ally Blackwell
     */
    public ArrayList<ArrayList<String>> getTabs()
    {
        return musicPlayer.getTabs();
    }
    /**
     * Gets the current songs title
     * @return the current songs title
     * @author Ally Blackwell
     */
    public String getSelectedSongTitle()
    {
        return selectedSong.getTitle();
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
    public void printTabsOfCurrentSong() {

    }
}

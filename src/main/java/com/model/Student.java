package com.model;
import java.util.ArrayList;

public class Student extends Account
{
    private ArrayList<Song> savedSongs;//Needs to saved into the Accounts json only for students 
    private ArrayList<Lesson> savedLessons;
    private ArrayList<Assignment> assignments;
    private ArrayList<Achievement> achievements;
    private ArrayList<Song> songsPlayed;
    private ArrayList<Lesson> completedLessons;
    private ArrayList<Course> courses;
    private ArrayList<Course> coursesInvitedTo;

    /**
     * Method used in DataLoader to construct using JSON data.
     * @param username The username of the student
     * @param password The password of the student
     * @param savedSongs The saved Songs of the student
     * @param savedLessons The saved Lessons of the student
     * @param assignments The assignments of the student
     * @param achievements The achievements of the student
     * @param songsPlayed The songsPlayed of the student
     * @param completedLessons The completedLessons of the student
     * @author Ethan Little
     */
    public Student(String username, String password,  ArrayList<Song> savedSongs, ArrayList<Lesson> savedLessons, 
                ArrayList<Assignment> assignments, ArrayList<Achievement> achievements, ArrayList<Song> songsPlayed,
                ArrayList<Lesson> completedLessons) {
        super(username, password);
        this.savedSongs = savedSongs;
        this.savedLessons = savedLessons;
        this.assignments = assignments;
        this.achievements = achievements;
        this.songsPlayed = songsPlayed;
        this.courses = new ArrayList<Course>();
        this.completedLessons = completedLessons;
        this.coursesInvitedTo = new ArrayList<Course>();
    }
    /**
     * Constructor to be used when a new Student is made, not being loaded in from JSON.
     * @param username Username of the new Student.
     * @param password Password of the new Student.
     * @author Ethan Little
     */
    public Student (String username, String password) {
        super(username, password);
        savedSongs = new ArrayList<Song>();
        savedLessons = new ArrayList<Lesson>();
        assignments = new ArrayList<Assignment>();
        achievements = new ArrayList<Achievement>();
        songsPlayed = new ArrayList<Song>();
        completedLessons = new ArrayList<Lesson>();
        courses = new ArrayList<Course>();
        coursesInvitedTo = new ArrayList<Course>();
    }
    /**
     * Adds the passed course to courses if it is valid to do so.
     * To be valid, the course must not already exist in the in the list of courses or list of coursesInvitedTo.
     * @param course The course to be added.
     * @return A boolean value determining if the course was successfully added.
     * @author Ethan Little
     */
    public boolean addCourse(Course course) {
        boolean add = true;
        for (Course c : courses) {
            if (c.getCourseID().equals(course.getCourseID())) {
                add = false;
            }
        }
        for(Course c : coursesInvitedTo) {
            if (c.getCourseID().equals(course.getCourseID())) {
                add = false;
            }
        }
        if (add) {
            courses.add(course);
        }
        return add;
    }
    /**
     * Adds the passed course to coursesInvitedTo if it is valid to do so.
     * To be valid, the course must not already exist in the list of coursesInvitedTo or in the list of courses.
     * @param course The course to be added.
     * @return A boolean value determining if the course was successfully added.
     * @author Ethan Little
     */
    public boolean addCourseInvitedTo(Course course) {
        boolean add = true;
        for(Course c : coursesInvitedTo) {
            if (c.getCourseID().equals(course.getCourseID())) {
                add = false;
            }
        }
        for (Course c : courses) {
            if (c.getCourseID().equals(course.getCourseID())) {
                add = false;
            }
        }
        if (add) {
            coursesInvitedTo.add(course);
        }
        return add;
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }
    public void addSavedSong(Song song) {
        savedSongs.add(song);
    }

    public void acceptInvite(Course course) {
        
    }
    public void declineInvite(Course course) {
        
    }
    @Override
    public String toString() {
        String ret = super.getUsername() + " ";
        if (courses != null && !courses.isEmpty()) {
            for (Course c : courses) {
                ret = ret + c + " ";
            }
        }
        return ret;
    }
    public ArrayList<Song> getSavedSongs(){
        return savedSongs;
    }
}

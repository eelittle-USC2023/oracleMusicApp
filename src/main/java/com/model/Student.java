package com.model;
import java.util.ArrayList;

public class Student extends Account
{
    private ArrayList<Song> savedSongs;
    private ArrayList<Lesson> savedLessons;
    private ArrayList<Assignment> assignments;
    private ArrayList<Achievement> achievements;
    private ArrayList<Song> songsPlayed;
    private ArrayList<Song> completedSongs;
    private ArrayList<Lesson> completedLessons;
    private ArrayList<Course> courses;
    private ArrayList<Course> coursesInvitedTo;

    /**
     * Method used in DataLoader to construct using JSON data.
     * @param username
     * @param password
     * @param savedSongs
     * @param savedLessons
     * @param assignments
     * @param achievements
     * @param songsPlayed
     * @param completedSongs
     * @param completedLessons
     * @author Ethan Little
     */
    public Student(String username, String password,  ArrayList<Song> savedSongs, ArrayList<Lesson> savedLessons, 
                   ArrayList<Assignment> assignments, ArrayList<Achievement> achievements, ArrayList<Song> songsPlayed,
                   ArrayList<Song> completedSongs, ArrayList<Lesson> completedLessons) {
        super(username, password);
        this.savedSongs = savedSongs;
        this.savedLessons = savedLessons;
        this.assignments = assignments;
        this.achievements = achievements;
        this.songsPlayed = songsPlayed;
        this.courses = new ArrayList<Course>();
        this.completedSongs = completedSongs;
        this.completedLessons = completedLessons;
        this.coursesInvitedTo = new ArrayList<Course>();
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
}

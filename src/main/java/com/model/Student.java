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
        this.courses = new ArrayList<Course>();
    }

    public void addCourse(Course course) {
        courses.add(course);
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
        String ret = super.getUsername();
        if (courses != null && !courses.isEmpty()) {
            for (Course c : courses) {
                ret = ret + c;
            }
        }
        return ret;
    }
}

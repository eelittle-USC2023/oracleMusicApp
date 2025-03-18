package com.model;
import java.util.ArrayList;

public class Student extends Account
{
    private ArrayList<Song> savedSongs;
    private ArrayList<Lesson> savedLessons;
    private ArrayList<Assignment> assignments;
    private ArrayList<Achievement> achievements;
    private ArrayList<Song> songsPlayed;
    private ArrayList<Course> courses;
    private ArrayList<Song> completedSongs;
    private ArrayList<Lesson> completedLessons;

    public Student(String username, String password,  ArrayList<Song> savedSongs, ArrayList<Lesson> savedLessons, 
                   ArrayList<Assignment> assignments, ArrayList<Achievement> achievements, ArrayList<Song> songsPlayed,
                   ArrayList<Song> completedSongs, ArrayList<Lesson> completedLessons) {
        super(username, password);
        this.savedSongs = savedSongs;
        this.savedLessons = savedLessons;
        this.assignments = assignments;
        this.achievements = achievements;
        this.songsPlayed = songsPlayed;
        this.completedSongs = completedSongs;
        this.completedLessons = completedLessons;
    }

    public void acceptInvite()
    {

    }
    public void declineInvite()
    {
        
    }
    public void addCourse(Course course) {
        courses.add(course);
    }
}

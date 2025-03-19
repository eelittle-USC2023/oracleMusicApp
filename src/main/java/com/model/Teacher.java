package com.model;
import java.util.ArrayList;

public class Teacher extends Account 
{
    private ArrayList<Course> courses;

    public Teacher(String username, String password) {
        super(username, password);
        courses = new ArrayList<Course>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void CreateCourse(Course course)
    {

    }
    public void disbandCourse(Course course)
    {
        
    }
    @Override
    public String toString() {
        return super.getUsername();
    }
}
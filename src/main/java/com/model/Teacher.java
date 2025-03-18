package com.model;
import java.util.ArrayList;

public class Teacher extends Account 
{
    private ArrayList<Course> courses;

    public Teacher(String username, String password) {
        super(username, password);
    }

    public void CreateCourse(Course course)
    {

    }
    public void disbandCourse(Course course)
    {
        
    }
}
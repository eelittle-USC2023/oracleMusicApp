package com.model;

import java.util.ArrayList;
import java.util.UUID;
//Not relevant to this sprint
public class Teacher extends Account {
    private ArrayList<Course> courses;

    public Teacher(String username, String password) {
        super(username, password);
        courses = new ArrayList<Course>();
    }

    public Course createCourse(String courseName) {
        Course newCourse = new Course(courseName, UUID.randomUUID(), new ArrayList<Student>(), new ArrayList<Assignment>(), this, new ArrayList<Student>());
        courses.add(newCourse);
        return newCourse;
    }

    public void disbandCourse(Course course) {
        courses.remove(course);
    }
    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getUsername() {
        return super.getUsername();
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

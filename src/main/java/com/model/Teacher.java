package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Teacher extends Account {
    private ArrayList<Course> courses;

    public Teacher(String username, String password) {
        super(username, password, "Teacher");
        this.courses = new ArrayList<>();
    }

    public Course createCourse(String courseName) {
        Course newCourse = new Course(courseName, UUID.randomUUID(), this);
        courses.add(newCourse);
        return newCourse;
    }

    public void disbandCourse(Course course) {
        courses.remove(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getTeacherID() {
        return getUsername();
    }

    @Override
    public String toString() {
        return "Teacher: " + getUsername() + " (Courses: " + courses.size() + ")";
    }
}

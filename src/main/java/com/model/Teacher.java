package com.model;
import java.util.ArrayList;

public class Teacher extends Account {
    private ArrayList<Course> courses;

    public Teacher(String username, String password) {
        super(username, password);
        courses = new ArrayList<Course>();
    }

    public void createCourse (String courseName, String courseId) {
        Course newCourse = new Course(courseName, courseId, this);
        courses.add(newCourse);
    }

    public void disbandCourse(Course course){
        courses.remove(course);
    }
    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getTeacherID(){
        return getUsername();
    }

    @Override
    public String toString() {
        return "Teacher:" + getUsername() + "(Courses:" + courses.size() + ")";
    }
}

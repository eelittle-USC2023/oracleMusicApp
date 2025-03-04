package com.model;
import java.util.ArrayList;


public class Course {
    private String courseName;
    private String courseID;
    private ArrayList<Student> students;
    private ArrayList <Assignment> assignment;
    private Teacher teacher;

    public Course (String courseName, String courseId, Teacher teacher){
        this.courseName = courseName;
        this.courseID = courseID;
        this.assignment = new ArrayList<>();
        this.teacher = teacher;
        this.students = new ArrayList<>();

    }
    public void addStudent(Student student){
        students.add(student);
    }
    public void removeStudent(Student student){
        students.remove(student);
    }
    public void addAssignment(Assignment Assignment){
        assignment.add(Assignment);
    }
    public String getCourseName(){
        return courseName;
    }
    public String getCourseID(){
        return courseID;
    }
    public Teacher getTeacher(){
        return teacher;
    }
    public ArrayList<Student> getStudents(){
        return students;
    }
    public ArrayList<Assignment> getAssignments(){
        return assignment;
    }
    @Override
    public String toString(){
        return "Course:" + courseName + " (ID:" + courseID + ")";
    }
}
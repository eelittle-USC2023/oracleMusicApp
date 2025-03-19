package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private String courseName;
    private UUID id;
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments;
    private Teacher teacher;

    public Course(String courseName, UUID id, ArrayList<Student> students, ArrayList<Assignment> assignments, Teacher teacher) {
        this.courseName = courseName;
        this.id = id;
        this.students = students;
        this.assignments = assignments;
        this.teacher = teacher;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public void removeStudent(Student student){
        students.remove(student);
    }
    public void addAssignment(Assignment Assignment){
        assignments.add(Assignment);
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
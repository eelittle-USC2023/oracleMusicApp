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
    @Override
    public String toString() {
        return courseName;
    }
}
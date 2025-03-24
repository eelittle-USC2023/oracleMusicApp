package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private String courseName;
    private UUID id;
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments;
    private Teacher teacher;
    private ArrayList<Student> invitedStudents;

    /**
     * Constructor for use in DataLoader. 
     * Since all the data is being loaded in, the constructor simply sets all the members to their respective parameters.
     * @param courseName Name of the course.
     * @param id UUID of the course.
     * @param students Students that are enrolled in the course.
     * @param assignments All assignments that have been given in this course.
     * @param teacher Teacher of the course.
     * @param invitedStudents Students that have been invited, but not yet accepted or decline the invitation.
     * @author Ethan Little
     */
    public Course(String courseName, UUID id, ArrayList<Student> students, ArrayList<Assignment> assignments, Teacher teacher, ArrayList<Student> invitedStudents) {
        this.courseName = courseName;
        this.id = id;
        this.students = students;
        this.assignments = assignments;
        this.teacher = teacher;
        this.invitedStudents = invitedStudents;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    /**
     * Adds the passed student if it is valid to do so.
     * To be valid, the student must not already exist in the list of invitedStudents or in the list of students.
     * @param student The student to be added.
     * @return A boolean value determining if the student was successfully added.
     * @author Ethan Little
     */
    public boolean addInvitedStudent(Student student) {
        boolean add = true;
        for(Student s: invitedStudents) {
            if (s.getUsername().equals(student.getUsername())) {
                add = false;
            }
        }
        for(Student s: students) {
            if (s.getUsername().equals(student.getUsername())) {
                add = false;
            }
        }
        if (add) {
            invitedStudents.add(student);
        }
        return add;
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
    public UUID getCourseID(){
        return id;
    }
    public Teacher getTeacher(){
        return teacher;
    }
    public ArrayList<Student> getStudents(){
        return students;
    }
    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }
    @Override
    public String toString(){
        return "Course:" + courseName + " (ID:" + id + ")";
    }
}
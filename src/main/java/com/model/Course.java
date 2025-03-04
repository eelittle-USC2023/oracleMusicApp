package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private ArrayList<Student> students;

    public Course(UUID id) {
        this.id = id;
        students = new ArrayList<Student>();
    }
}
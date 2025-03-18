package com.model;

import java.util.ArrayList;

public class LessonAssignment extends Assignment {
    private Lesson lesson;
    private String difficultyLevel;
    private ArrayList<Lesson> recommendedLesson; 

    public LessonAssignment(Lesson lesson) {
        this.lesson = lesson;
        difficultyLevel = "";
        recommendedLesson = new ArrayList<Lesson>();
    }
}
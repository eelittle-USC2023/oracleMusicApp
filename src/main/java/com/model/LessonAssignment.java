package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class LessonAssignment extends Assignment {
    private Lesson lesson;
    private String difficultyLevel;
    private ArrayList<Lesson> recommendedLessons; 

    public LessonAssignment(String title, String description, Lesson lesson) {
        super(title, description);
        this.lesson = lesson;
        difficultyLevel = "";
        recommendedLessons = new ArrayList<Lesson>();
    }
    public LessonAssignment(String title, String description, boolean isComplete, Lesson lesson, String difficultyLevel, ArrayList<Lesson> recommendedLessons) {
        super(title, description, isComplete);
        this.lesson = lesson;
        difficultyLevel = "";
        recommendedLessons = new ArrayList<Lesson>();
    }
    public UUID getLessonID(){
        return lesson.getID();
    }
    public String getType(){
        return "Lesson";
    }
}
package com.model;

import java.util.ArrayList;
import java.util.UUID;
//Not relevant to this sprint
public class LessonAssignment extends Assignment {
    private Lesson lesson;
    private String difficultyLevel;
    private ArrayList<Lesson> recommendedLessons; 

    /**
     * Used to construct lessonAssignments when they are loaded in. 
     * Since all the data is acquired from the JSON, the members are simply set to their respective params.
     * @param title Title of the lesson.
     * @param description Description of the lesson.
     * @param isComplete Whether or not the assignment has been completed.
     * @param lesson The lesson that is assigned to the student.
     * @param difficultyLevel The difficulty level of the lesson assignment.
     * @param recommendedLessons Other lessons that are recommended for the student to complete.
     * @author Ethan Little
     */
    public LessonAssignment(String title, String description, boolean isComplete, Lesson lesson, 
                String difficultyLevel, ArrayList<Lesson> recommendedLessons) {
        super(title, description, isComplete);
        this.lesson = lesson;
        this.difficultyLevel = difficultyLevel;
        this.recommendedLessons = recommendedLessons;
    }
    
    public UUID getLessonID() {
        return lesson.getID();
    }
    public String getType(){
        return "Lesson";
    }
}
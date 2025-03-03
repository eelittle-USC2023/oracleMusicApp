package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class Lesson 
{
    private UUID id;
    private String title;
    private ArrayList<Question> questions;
    private ArrayList<String> text;

    public Lesson(String title, ArrayList<Question> questions, ArrayList<String> text)
    {

    }
    public Lesson(UUID id, String title, ArrayList<Question> questions, ArrayList<String> text)
    {
        this.id = id;
        this.title = title;
        this.questions = questions;
        this.text = text;
    }
    public void getPercentQuestionsCorrect()
    {
        
    }
}


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
        this.title = title;
        this.questions = questions;
        this.text = text;

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
    public String getTitle(){
        return title;
    }
    public ArrayList<Question> getQuestions(){
        return questions;
    }
    public ArrayList<String> getText(){
        return text;
    }
}
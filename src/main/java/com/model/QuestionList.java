package com.model;
import java.util.ArrayList;

public class QuestionList 
{
    private ArrayList<Question> questions;
    private static QuestionList questionList;

    private QuestionList()
    {

    }
    public static QuestionList getInstance()
    {
        QuestionList temp = new QuestionList();
        return temp;
    }
    public Question getQuestion()
    {
        Question temp = new Question();
        return temp;
    }
    public boolean addQuestion(String question, String studentAnswer, int points, String correctAnswer, String feedback, String hint)
    {
        return true;
    }
    public boolean editQuestion(Question question)
    {
        return true;
    }
    public boolean deleteQuestion(Question question)
    {
        return true;
    }
    public void save()
    {
        
    }
}

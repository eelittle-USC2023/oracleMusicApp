package com.model;
import java.util.ArrayList;

public class Question 
{
    private String question;
    private String studentAnswer;
    private ArrayList<String> answerChoices;
    private int points;
    private String correctAnswer;
    private String feedback;
    private String hint;

    public boolean isCorrect()
    {
        return true;
    }
    public boolean answeredQuestion()
    {
        return true;
    }
    public int getPoints()
    {
        return 1;
    }
    public void StudentAnswer(String answer)
    {

    }
    public String getFeedback()
    {
        return "";
    }
    public String getHint()
    {
        return "";
    }
}

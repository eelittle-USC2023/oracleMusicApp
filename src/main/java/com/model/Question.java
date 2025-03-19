package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Question 
{
    private UUID id;
    private String question;
    private String studentAnswer;
    private ArrayList<String> answerChoices;
    private int points;
    private String correctAnswer;
    private String feedback;
    private String hint;

    public Question(UUID id, String question, String studentAnswer, ArrayList<String> answerChoices, int points, String correctAnswer, String feedback, String hint) {
        this.id = id;
        this.question = question;
        this.studentAnswer = studentAnswer;
        this.answerChoices = answerChoices;
        this.points = points;
        this.correctAnswer = correctAnswer;
        this.feedback = feedback;
        this.hint = hint;
    }

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
    public UUID getID() {
        return id;
    }
    @Override
    public String toString() {
        String ret = id + question + studentAnswer;
        if (answerChoices != null) {
            for (String s : answerChoices) {
                ret = ret + s;
            }
        }
        ret = ret + points + correctAnswer + feedback + hint;
        return ret;
    }
}

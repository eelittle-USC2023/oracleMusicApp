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


    public Question(String question, ArrayList<String> answerChoices, String correctAnswer, int points, String feedback, String hint){
        this.question = question;
        this.answerChoices = answerChoices;
        this.correctAnswer = correctAnswer;
        this.points = points;
        this.feedback = feedback;
        this.hint = hint;
        this.studentAnswer = "";

    }

   public ArrayList<String> getAnswerChoices(){
    return answerChoices;
   }
   public String getCorrectAnswer(){
    return correctAnswer;
   }
   public int getPoints(){
    return points;
   }
   public String getFeedback(){
    return feedback;
   }
   public String getHint(){
    return hint;
   }
   public String getQuestionText(){
    return question;
   }
    public boolean isCorrect()
    {
        return true;
    }
    public boolean answeredQuestion()
    {
        return true;
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

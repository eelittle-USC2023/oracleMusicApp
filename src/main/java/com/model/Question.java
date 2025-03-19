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
}

package com.model;
import java.util.ArrayList;
import java.util.UUID;

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
    public Question getQuestion(UUID id)
    {
        /*
        for (Question q : questions) {
            if (q.getID() == id) {
                return q;
            }
        }
        */
        return new Question(id, null, null, null, 0, null, null, null);
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

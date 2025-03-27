package com.model;

import java.util.ArrayList;
import java.util.UUID;
//Not relevant this sprint
public class QuestionList {
    private ArrayList<Question> questions;
    private static QuestionList instance;

    /**
     * Private constructor used when getInstance is called for the first time. Gets the questions from the DataLoader.
     * @author Ethan Little
     */
    private QuestionList() {
        questions = DataLoader.getQuestions();
    }
    /**
     * GetInstance method to maintain Singleton design. 
     * Constructs the instance of QuestionList if it does not already exist, then returns the instance.
     * @return The current instance of QuestionList.
     * @author Ethan Little
     */
    public static QuestionList getInstance() {
      if(instance == null){
        instance = new QuestionList(); 
      }
      return instance;
    }

    /**
     * Finds and returns the question with the id that matches the parameter.
     * @param id ID of the question that is being searched for.
     * @return The question with the ID that matches the param. If there is no question with a matching ID, returns null.
     * @author Ethan Little
     */
    public Question getQuestion(UUID id) {
        for (Question q : questions) {
            if (q.getID().equals(id)) {
                return q;
            }
        }
        return null;
    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public boolean addQuestion(String question, ArrayList<String> answerChoices, int points, String correctAnswer, String feedback, String hint) {
        Question newQuestion = new Question(question, answerChoices, correctAnswer, points, feedback, hint);
        return questions.add(newQuestion);
    }
    public boolean editQuestion(Question question) {
        return true;
    }
    public boolean deleteQuestion(Question question) {
        return true;
    }
    public void save() {
        
    }
    

}

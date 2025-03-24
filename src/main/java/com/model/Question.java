package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Question {
    private UUID id;
    private String question;
    private ArrayList<String> answerChoices;
    private String correctAnswer;
    private String studentAnswer;
    private int points;
    private String feedback;
    private String hint;
    /**
     * Constructor for use in DataLoader.
     * Since all the data is being loaded in, the constructor simply sets all the members to their respective parameters.
     * @param id UUID of the question.
     * @param question Question text of the question.
     * @param answerChoices The answer choices to the question.
     * @param correctAnswer The correct answer of the answer choices to the question.
     * @param studentAnswer What the student gave as the answer to the question.
     * @param points How many points the question is worth.
     * @param feedback Feedback for answering the question correctly.
     * @param hint Hint to help answer the question correctly.
     * @author Ethan Little
     */
    public Question(UUID id, String question, ArrayList<String> answerChoices, String correctAnswer,
            String studentAnswer, int points, String feedback, String hint) {
        this.id = id;
        this.question = question;
        this.answerChoices = answerChoices;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
        this.feedback = feedback;
        this.hint = hint;
    }

    public Question(String question, ArrayList<String> answerChoices, String correctAnswer, int points, String feedback,
            String hint) {
        this.id = UUID.randomUUID();
        this.question = question;
        this.answerChoices = answerChoices;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = "";
        this.points = points;
        this.feedback = feedback;
        this.hint = hint;
    }

    public ArrayList<String> getAnswerChoices() {
        return answerChoices;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public int getPoints() {
        return points;
    }
    public String getFeedback() {
        return feedback;
    }
    public String getHint() {
        return hint;
    }
    public String getQuestionText() {
        return question;
    }

    public boolean isCorrect() {
        return true;
    }

    public boolean answeredQuestion() {
        return true;
    }

    public void StudentAnswer(String answer) {

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

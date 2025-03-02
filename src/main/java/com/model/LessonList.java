package com.model;
import java.util.ArrayList;

public class LessonList 
{
    private ArrayList<Lesson> lessons;
    private static LessonList lessonList;

    private LessonList()
    {

    }
    public static LessonList getInstance()
    {
        LessonList temp = new LessonList();
        return temp;
    }
    public Lesson getLesson()
    {
        ArrayList<Question> temp1 = new ArrayList();
        ArrayList<String> temp2 = new ArrayList();
        Lesson temp = new Lesson("title", temp1 , temp2);
        return temp;
    }
    public boolean addLesson(String title, String studentAnswer, int points, String correctAnswer, String feedback, String hint)
    {
        return true;
    }
    public boolean editLesson(Lesson lesson)
    {
        return true;
    }
    public boolean deleteLesson(Lesson lesson)
    {
        return true;
    }
    public void save()
    {

    }


}

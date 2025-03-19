package com.model;
import java.util.ArrayList;

public class LessonList 
{
    private ArrayList<Lesson> lessons;
    private static LessonList instance;

    private LessonList()
    {
        lessons = new ArrayList<>();

    }
    public static LessonList getInstance()
    {
      if (instance==null){
        instance = new LessonList();
      }
      return instance;
    }
    public Lesson getLessons(String title)
    {
        for (Lesson lesson : lessons){
            if (lesson.getTitle().equalsIgnoreCase(title)){
                return lesson;
            }
        }
     return null;
    }
    public ArrayList<Lesson> getLessons(){
        return lessons;
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

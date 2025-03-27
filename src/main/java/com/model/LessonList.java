package com.model;
import java.util.ArrayList;
import java.util.UUID;
//Not relevant to this sprint
public class LessonList 
{
    private ArrayList<Lesson> lessons;
    private static LessonList instance;

    /**
     * Private constructor used when getInstance is called for the first time. Gets the lessons from the DataLoader.
     * @author Ethan Little
     */
    private LessonList()
    {
        lessons = DataLoader.getLessons();
    }
    /**
     * GetInstance method to maintain Singleton design. 
     * Constructs the instance of LessonList if it does not already exist, then returns the instance.
     * @return The current instance of LessonList.
     * @author Ethan Little
     */
    public static LessonList getInstance()
    {
      if (instance == null){
        instance = new LessonList();
      }
      return instance;
    }

    public ArrayList<Lesson> getLessons(){
        return lessons;
    }
    /**
     * Finds and returns the Lesson with the id that matches the parameter.
     * @param id ID of the lesson that is being searched for.
     * @return The lesson with the ID that matches the param. If there is no lesson with a matching ID, returns null.
     * @author Ethan Little
     */
    public Lesson getLesson(UUID id)
    {
        for (Lesson lesson : lessons) {
            if (lesson.getID().equals(id)) {
                return lesson;
            }
        }
        return null;
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

package com.model;

public class Assignment{
    private String title;
    private String description;
    private boolean completed;

    public Assignment(String title, String description){
        this.title = title;
        this.description = description;
        this.completed = false;
    }
    public void markCompleted(){
        this.completed = true;
    }
    public boolean isCompleted(){
        return completed;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
    return description;
    }

    @Override
    public  String toString(){
        return title + description + "Completed:" + completed;
    }
}
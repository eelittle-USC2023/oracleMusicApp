package com.model;

//Not relevant to this sprint
public abstract class Assignment{
    private String title;
    private String description;
    private boolean complete;

    public Assignment(String title, String description){
        this.title = title;
        this.description = description;
        this.complete = false;
    }
    public Assignment(String title, String description, boolean complete) {
        this.title = title;
        this.description = description;
        this.complete = complete;
    }
    public void markComplete(){
        this.complete = true;
    }
    public boolean isComplete(){
        return complete;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
    return description;
    }

    @Override
    public  String toString(){
        return title + description + "Completed:" + complete;
    }
}
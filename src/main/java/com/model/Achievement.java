package com.model;

public class Achievement{
    private String name;
    private String description;
    private boolean unlocked;

    public Achievement(String name, String description){
        this.name = name;
        this.description = description;
        this.unlocked = false;
    }
    public void unlock(){
        this.unlocked = true;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public boolean isUnlocked(){
        return unlocked;
    }
    @Override
    public String toString(){
        return name + ":" + description + "(Unlocked:" + unlocked + ")";
    }
}
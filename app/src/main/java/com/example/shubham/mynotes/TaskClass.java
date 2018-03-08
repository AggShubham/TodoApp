package com.example.shubham.mynotes;

/**
 * Created by Shubham on 15-02-2018.
 */

public class TaskClass {

    String name;
    int taskcost;
    String description;
    String time;
    String date;
    int id;

    public TaskClass(String name, int taskcost, String description, String time, String date, int id) {
        this.name = name;
        this.taskcost = taskcost;
        this.description = description;
        this.time = time;
        this.date = date;
        this.id = id;
    }

    public TaskClass(String name, int taskcost , String description) {
        this.name = name;
        this.taskcost = taskcost;
        this.description = description;
        this.id=-1;
    }

    public TaskClass(String name, int taskcost, String description, int id) {
        this.name = name;
        this.taskcost = taskcost;
        this.description = description;
        this.id = id;
    }

    public TaskClass(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaskcost() {
        return taskcost;
    }

    public void setTaskcost(int taskcost) {
        this.taskcost = taskcost;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

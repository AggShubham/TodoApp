package com.example.shubham.mynotes;

/**
 * Created by Shubham on 03-03-2018.
 */

public class CommentsClass {
    int id;
    String comment;
    int taskid;

    public CommentsClass(int id, String comment, int taskid) {
        this.id = id;
        this.comment = comment;
        this.taskid = taskid;
    }

    public CommentsClass(String comment, int taskid) {
        this.comment = comment;
        this.taskid = taskid;
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }
}

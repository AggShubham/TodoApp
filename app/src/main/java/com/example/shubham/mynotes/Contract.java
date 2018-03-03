package com.example.shubham.mynotes;

/**
 * Created by Shubham on 17-02-2018.
 */

public class Contract {
    public static final String DATABASE_NAME = "tasks_db";
    public static final int VERSION = 1;


    static class TaskClass {

        public static final String TABLE_NAME = "Tasks";
        public static final String ID = "id";
        public static final String TITLE = "Taskname";
        public static final String DESCRIPTION = "desc";
        public static final String COST = "Taskcost";

    }

    static class Comments {

        public static final String TABLE_NAME = "comments";
        public static final String ID = "id";
        public static final String COMMENT = "comment";
        public static final String TASK_ID ="task_id";
    }
}

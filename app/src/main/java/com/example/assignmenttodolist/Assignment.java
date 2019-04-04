package com.example.assignmenttodolist;

public class Assignment {

    public Assignment(Integer ID, String Name, String DDate, Integer Priority, String Note) {
        ASSIGNMENT_ID = ID;
        ASSIGNMENT_NAME = Name;
        ASSIGNMENT_DDATE = DDate;
        ASSIGNMENT_PRIORITY = Priority;
        ASSIGNMENT_NOTE = Note;
    }
    public Integer ASSIGNMENT_ID;

    public String ASSIGNMENT_NAME;

    public String ASSIGNMENT_DDATE;

    public Integer ASSIGNMENT_PRIORITY;

    public String ASSIGNMENT_NOTE;
}

//  FILE        :	Assignment.java
//  PROJECT     :   PROG3150-A2
//  PROGRAMMERs :	Oloruntoba Lagunju
//                  Gabriel Stewart
//                  Connor Lynch
//  DATE        :	April 1, 2019
//  DESCRIPTION :   This file contains the source code for the Assignment class

package com.example.assignmenttodolist;

// Class   	    : Assignment
// Description	: This class represents an Assignment object
// Programmer	: Gabriel Stewart
//              : https://conestoga.desire2learn.com/d2l/le/content/244302/viewContent/5220636/View
// Methods 	    :
public class Assignment {

    // Class Constructor
    public Assignment(Integer ID, String Name, String DDate, Integer Priority, String Note) {
        Assignment_ID = ID;
        Assignment_Name = Name;
        Assignment_DDate = DDate;
        Assignment_Priority = Priority;
        Assignment_Note = Note;
    }

    // Public data members
    public Integer Assignment_ID;
    public String Assignment_Name;
    public String Assignment_DDate;
    public Integer Assignment_Priority;
    public String Assignment_Note;


}

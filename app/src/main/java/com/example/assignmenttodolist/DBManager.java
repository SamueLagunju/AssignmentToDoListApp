//  FILE        :	DBHelper.java
//  PROJECT     :   PROG3150-A2
//  PROGRAMMERs :	Oloruntoba Lagunju
//                  Gabriel Stewart
//                  Connor Lynch
//  DATE        :	April 1, 2019
//  DESCRIPTION :   This file contains the source code for the DBHelper and DBManager classes
package com.example.assignmenttodolist;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


// Class   	    : DBManager
// Description	: This class manages to operation of the SQLLite database
// Programmer	: Gabriel Stewart
// Sources      : Marc Bueno Sample Code
//              : https://conestoga.desire2learn.com/d2l/le/content/244302/viewContent/5220636/View
// Methods 	    :
public class DBManager
{
    // Helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // Database Constants
    public static final String DB_NAME = "Assignment.db";
    public static final int DB_VERSION = 1;

    // Assignment Table Constants
    public static final String ASSIGNMENT_TABLE = "Assignment";

    public static final String ASSIGNMENT_ID = "AssignmentID";
    public static final int ASSIGNMENT_ID_COL = 0;

    public static final String ASSIGNMENT_NAME = "AssignmentName";
    public static final int ASSIGNMENT_NAME_COL = 1;

    public static final String ASSIGNMENT_DDATE = "DueDate";
    public static final int ASSIGNMENT_DDATE_COL = 2;

    public static final String ASSIGNMENT_PRIORITY = "PRIORITY";
    public static final int ASSIGNMENT_PRIORITY_COL = 3;

    public static final String ASSIGNMENT_NOTE = "NOTE";
    public static final int ASSIGNMENT_NOTE_COL = 4;

    // Table CREATE and DROP Constants
    public static final String CREATE_ASSIGNMENT_TABLE = "CREATE TABLE " + ASSIGNMENT_TABLE + " (" +
          //  ASSIGNMENT_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ASSIGNMENT_NAME     + " TEXT    NOT NULL, " +
            ASSIGNMENT_DDATE    + " TEXT    NOT NULL, " +
            ASSIGNMENT_PRIORITY + " INTEGER NOT NULL, " +
            ASSIGNMENT_NOTE     + " TEXT);";

    public static final String DROP_ASSIGNMENT_TABLE =
            "DROP TABLE IF EXISTS " +  ASSIGNMENT_TABLE;

    // Class constructor
    public DBManager(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // Private methods
    public void openReadableDB() { db = dbHelper.getReadableDatabase(); }
    public void openWriteableDB() { db = dbHelper.getWritableDatabase(); }
    private void closeDB() {
        if (db!= null) {
            db.close();
        }
    }

    // Class   	    : DBHelper
    // Description	: This is a helper class to manage database creation operations
    // Programmer	: Gabriel Stewart
    // Sources      : Marc Bueno Sample Code
    //              : https://conestoga.desire2learn.com/d2l/le/content/244302/viewContent/5220636/View
    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create table
            db.execSQL(CREATE_ASSIGNMENT_TABLE);

            // Insert a default assignment
            //db.execSQL("INSERT INTO Assignment VALUES (1, 'Example', 'March 5th, 2019', 1, 'This is an example')");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("Assignment Table", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(DROP_ASSIGNMENT_TABLE);
        }
    }

/*    // Method		: getAssignments
    // Description	: Returns ArrayList of Assignments matching condition
    // Parameters	: String condition - Optional condition for returned assignments
    // Returns		: ArrayList<Assignment> - Contains list of corresponding assignments
    // Sources      : Marc Bueno Sample Code
    //              : https://conestoga.desire2learn.com/d2l/le/content/244302/viewContent/5220636/View
    public ArrayList<Assignment> getAssignments(String condition) {
        this.openReadableDB();
        Cursor cursor = db.query(ASSIGNMENT_TABLE, null, null, null, null, null, null);
        ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
        while (cursor.moveToNext()) {
            Assignments.add(getAssignmentFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return Assignments;
    }*/

/*
    // Method		: getAssignmentFromCursor
    // Description	: Uses cursor to return Assignment
    // Parameters	: Cursor - Cursor that reads through result of query
    // Returns		: Assignment - Assignnment class instance containing requested assignment data
    // Sources      : Marc Bueno Sample Code
    //              : https://conestoga.desire2learn.com/d2l/le/content/244302/viewContent/5220636/View
    private static Assignment getAssignmentFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        else {
            try {
                Assignment assignment = new Assignment(
                        */
/*cursor.getInt(ASSIGNMENT_ID_COL),*//*

                        cursor.getString(ASSIGNMENT_NAME_COL),
                        cursor.getString(ASSIGNMENT_DDATE_COL),
                        cursor.getInt(ASSIGNMENT_PRIORITY_COL),
                        cursor.getString(ASSIGNMENT_NOTE_COL));
                return assignment;

            }
            catch (Exception e) {
                return null;
            }
        }
    }
*/

    // Method		: insertAssignment
    // Description	: Inserts assignment passed as parameter into table
    // Parameters	: Assignment - Assignment to add to table
    // Returns		: Long rowID = Row of assignment added to table
    // Sources      : Marc Bueno Sample Code
    //              : https://conestoga.desire2learn.com/d2l/le/content/244302/viewContent/5220636/View
    public long insertAssignment(Assignment assignment) {
        ContentValues cv = new ContentValues();
        //cv.put(ASSIGNMENT_ID, assignment.Assignment_ID);
        cv.put(ASSIGNMENT_NAME, assignment.Assignment_Name);
        cv.put(ASSIGNMENT_DDATE, assignment.Assignment_DDate);
        cv.put(ASSIGNMENT_PRIORITY, assignment.Assignment_Priority);
        cv.put(ASSIGNMENT_NOTE, assignment.Assignment_Note);

        this.openWriteableDB();
        long rowID = db.insert(ASSIGNMENT_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    // Method		: updateAssignment
    // Description	: Updates assignment in table corresponding to passed assignment ID
    // Parameters	: Assignment - Assignment to replace existing assignment in table
    // Returns		: Int rowCount - Number of rows modified. Should be one if changing one assignment
    // Sources      : Marc Bueno Sample Code
    //              : https://conestoga.desire2learn.com/d2l/le/content/244302/viewContent/5220636/View
    public int updateAssignment(Assignment assignment) {
        ContentValues cv = new ContentValues();
        //cv.put(ASSIGNMENT_ID, assignment.Assignment_ID);
        cv.put(ASSIGNMENT_NAME, assignment.Assignment_Name);
        cv.put(ASSIGNMENT_DDATE, assignment.Assignment_DDate);
        cv.put(ASSIGNMENT_PRIORITY, assignment.Assignment_Priority);
        cv.put(ASSIGNMENT_NOTE, assignment.Assignment_Note);

        String where = ASSIGNMENT_NAME + "= ?";
        String[] whereArgs = {String.valueOf(assignment.Assignment_Name)};

        this.openWriteableDB();
        int rowCount = db.update(ASSIGNMENT_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    // Method		: deleteAssignment
    // Description	: Deletes assignment in table corresponding to passed assignment ID
    // Parameters	: long id - Assignment ID to delete
    // Returns		: Int rowCount - Number of rows modified. Should be one if changing one assignment
    // Sources      : Marc Bueno Sample Code
    //              : https://conestoga.desire2learn.com/d2l/le/content/244302/viewContent/5220636/View
    public int deleteAssignment (String name) {
        String where = ASSIGNMENT_NAME + "= ?";
        String[] whereArgs = { String.valueOf(name) };

        this.openWriteableDB();
        int rowCount = db.delete(ASSIGNMENT_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }





    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Method		: deleteAllAssignments()
    // Description	: Deletes all assignment in table
    // Parameters	: N/A
    // Returns		: Int rowCount - Number of rows modified.
    // Source       : http://android-er.blogspot.com/2011/06/simple-example-using-androids-sqlite_02.html
    public int deleteAllAssignments()
    {
        this.openWriteableDB();
        int rowCount = db.delete(ASSIGNMENT_TABLE, null, null);
        this.closeDB();
        return rowCount;
    }



    public Cursor viewData(String whatOrder)
    {
        Cursor cursor;
        if(whatOrder == "Date")
        {
            this.openReadableDB();
            String query = "Select * from " + ASSIGNMENT_TABLE + " ORDER BY DueDate";
            cursor = db.rawQuery(query, null);
        }
        else if(whatOrder == "Priority")
        {
            this.openReadableDB();
            String query = "Select * from " + ASSIGNMENT_TABLE + " ORDER BY PRIORITY";
            cursor = db.rawQuery(query, null);
        }

        else
        {
            this.openReadableDB();
            String query = "Select * from " + ASSIGNMENT_TABLE + " ORDER BY AssignmentName";
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }


}
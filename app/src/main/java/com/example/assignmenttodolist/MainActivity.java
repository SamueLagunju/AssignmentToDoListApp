package com.example.assignmenttodolist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * FILE:			MainActivity.java
 * PROJECT:		    assignmentApp
 * PROGRAMMER:	    Oloruntoba Lagunju.
 *                  Gabriel Stewart
 *                  Connor Lynch
 * DATE:			April 5th 2019
 * DESCRIPTION:     This is a file that supports code for the main activity of the app
 *                  developed in java
 */

public class MainActivity extends AppCompatActivity {

    DBManager databaseManager;
    ArrayList<String> assignmentArrayList;

    ArrayAdapter listAdapter;
    ListView assignmentList;

    /*
     *  Function        :   protected void onCreate(Bundle savedInstanceState)
     *  Description     :   Creates the main activity
     *  Parameters      :   Bundle savedInstanceState
     *  Returns         :   N/A
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assignmentArrayList = new ArrayList<>();
        assignmentList = findViewById(R.id.assignment_list_view);

        viewData(3);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.SortByDateMenuOption: {
                //Clearing the old list
                listAdapter.clear();
                viewData(1);
                break;
            }

            case R.id.SortByPriorityMenuOption: {
                //Clearing the old list
                listAdapter.clear();
                viewData(2);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     *  Function        :   public void onClick(View whatButton)
     *  Description     :   Does a set of instructions based on what button was clicked
     *  Parameters      :   View whatButton
     *  Returns         :   N/A
     */
    public void onClick(View whatButton) {
        //If the button to add an assignment was clicked.
        //If the button to view the about activity was clicked
        if (whatButton == findViewById(R.id.viewSettings)) {
            //Creating an intent to start an activity
            Intent addingAssIntent = new Intent(this, AboutActivity.class);
            startActivity(addingAssIntent); //Starting the activity
        }


        if (whatButton == findViewById(R.id.AddAssignmentFloatingBtn)) {
            Log.i("User Action:", "User is adding an assignment");
            try {
                Intent addingNewAssignmentIntent = new Intent(this, AddingNewAssignmentActivity.class);
                startActivityForResult(addingNewAssignmentIntent, 1);
            } catch (Exception error) {
                Toast.makeText(getApplicationContext(), "Error, Cannot add an assignment at the time, try again.", Toast.LENGTH_LONG).show();
                Log.e("Error", "Received an exception " + error.getMessage());
            }
        }

    }

    /*
     *  Function        :   onActivityResult(int requestCode, int codeResult, Intent intentData)
     *  Description     :   Processes results from started activities
     *  Parameters      :   int requestCode
     *                      int codeResult
     *                      Intent intentData
     *  Returns         :   N/A
     */
    protected void onActivityResult(int requestCode, int codeResult, Intent intentData) {
        super.onActivityResult(requestCode, codeResult, intentData);

        if (requestCode == 1) {
            if (codeResult == RESULT_OK) {
                //Clearing the old list
                listAdapter.clear();
                viewData(3);
            } else {
                Log.i("User Action:", "Operation Cancelled");
                //Displaying that adding a new assignment activity was cancelled
                Toast.makeText(getApplicationContext(),
                        "Operation Cancelled",
                        Toast.LENGTH_LONG).show();


            }
        }
    }


    private void viewData (int whatOrder) {
        databaseManager = new DBManager(getApplicationContext());
        Cursor cursor;

        switch (whatOrder) {
            case 1: {
                cursor = databaseManager.viewData("Date");

                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "No data to show", Toast.LENGTH_LONG).show();
                } else {
                    while (cursor.moveToNext()) {
                        assignmentArrayList.add(cursor.getString(0)
                                + "\n\nDue Date: " + cursor.getString(1)
                                + "\n\nPriority: " + cursor.getString(2));
                    }
                }
                break;
            }

            case 2: {
                cursor = databaseManager.viewData("Priority");

                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "No data to show", Toast.LENGTH_LONG).show();
                } else {
                    while (cursor.moveToNext()) {
                        assignmentArrayList.add(cursor.getString(0)
                                + "\n\nDue Date: " + cursor.getString(1)
                                + "\n\nPriority: " + cursor.getString(2));
                    }
                }
                break;
            }
            case 3: {
                cursor = databaseManager.viewData("AssignmentName");

                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "No data to show", Toast.LENGTH_LONG).show();
                } else {
                    while (cursor.moveToNext()) {
                        assignmentArrayList.add(cursor.getString(0)
                                + "\n\nDue Date: " + cursor.getString(1)
                                + "\n\nPriority: " + cursor.getString(2));
                    }
                }
                break;
            }
        }

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, assignmentArrayList);

        assignmentList.setAdapter(listAdapter);
    }


    /*
     *  Function        :   public void ExportToTxt(View view)
     *  Description     :   button that exports list items to a text file in downloads
     *  Parameters      :   View view
     *  Returns         :   N/A
     */
    public void ExportToTxt(View view) {
        final Context currentContext = getApplicationContext();
        Toast.makeText(currentContext, "Beginning Export", Toast.LENGTH_SHORT).show();


        new Thread(new Runnable() {
            public void run() {

                // Get the directory for the user's public pictures directory.
                final File path =
                        Environment.getExternalStoragePublicDirectory
                                (
                                        //Environment.DIRECTORY_PICTURES
                                        Environment.DIRECTORY_DOWNLOADS
                                );

                // Make sure the path directory exists.
                if (!path.exists()) {
                    // Make it, if it doesn't exit
                    path.mkdirs();
                }

                final File file = new File(path, "TodoListExport.txt");

                // Save your stream, don't forget to flush() it before closing it.

                try {
                    file.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    //put formatted data between the parenthesis
                    myOutWriter.append("test");

                    myOutWriter.close();

                    fOut.flush();
                    fOut.close();
                } catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }

            }

        }).start();
    }


}




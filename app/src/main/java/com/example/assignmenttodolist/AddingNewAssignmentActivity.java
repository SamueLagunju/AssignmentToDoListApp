package com.example.assignmenttodolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;


public class AddingNewAssignmentActivity extends AppCompatActivity {

    //Buffer variables to acquire the user's input
    String assignmentNameBuffer;
    String dueDateBuffer;

    String extraNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_assignment);

    }

    /*
     *  Function        :   public void saveOrCancel(View whatButton)
     *  Description     :   Depending on if the Save or Cancel button was pressed, it goes back to
     *                      main activity, and sends a result code
     *  Parameters      :   View whatButton
     *  Returns         :   N/A
     */
    public void saveOrCancel(View whatButton){

        //If the cancel button was clicked.
        if(whatButton == findViewById(R.id.CancelAssignmentBtn))
        {
            //Creating an intent to go back to the main activity
            Intent cancelledIntent = new Intent();
            //Setting an cancelled result code to the intent
            setResult(RESULT_CANCELED, cancelledIntent);
            finish();   //Finishing the activity
        }

        //If the save button was pressed
        if(whatButton == findViewById(R.id.SaveAssignmentBtn))
        {
            try
            {
                //Verify data here.
                assignmentNameBuffer = findViewById(R.id.assignmentField).toString();
                dueDateBuffer = findViewById(R.id.dueDateField).toString();
                extraNotes = findViewById(R.id.extraNoteWidget).toString();
                Assignment newAssignment = new Assignment(assignmentNameBuffer, dueDateBuffer, 3, extraNotes);
                DBManager databaseManager = new DBManager(getApplicationContext());

                databaseManager.deleteAllAssignments();
                Toast.makeText(getApplicationContext(), "Database deleted", Toast.LENGTH_LONG).show();

                /*databaseManager.insertAssignment(newAssignment);
                Toast.makeText(getApplicationContext(), "Insertion completion", Toast.LENGTH_LONG).show();*/
                //Put a try block here.
                //Creating an intent to go back to the main activity
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();   //Rerunning back to the main activity

            }
            catch (Exception error)
            {
                Log.e("Error","Received an exception " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }

}

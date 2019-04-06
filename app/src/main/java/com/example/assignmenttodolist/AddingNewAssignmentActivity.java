package com.example.assignmenttodolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * FILE:			addAssActivity.java
 * PROJECT:		    assignmentApp
 * PROGRAMMER:	    Oloruntoba Lagunju.
 *                  Gabriel Stewart
 *                  Connor Lynch
 * DATE:			April 5th 2019
 * DESCRIPTION:     This is a file that supports code for the adding an
 *                  assignment activity to a database.
 */


public class AddingNewAssignmentActivity extends AppCompatActivity {
    //Variable for the date picker dialog
    private DatePickerDialog.OnDateSetListener datePickerListener;

    //Buffer variables to acquire the user's input
    EditText assignmentNameBuffer;
    EditText dueDateBuffer;
    EditText extraNotes;
    RadioButton selectedRadioButton;
    RadioGroup priorityRadioGroup;


    /*
     *  Function        :   protected void onCreate(Bundle savedInstanceState)
     *  Description     :   Creates the activity
     *  Parameters      :   Bundle savedInstanceState
     *  Returns         :   N/A
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_assignment);

        //Setting up the dialog box window for selecting a due date
        dueDateBuffer = findViewById(R.id.dueDateField);
        //OnclickListener for the due date text view widget
        dueDateBuffer.setOnClickListener(new View.OnClickListener(){
            @Override
            /*
             *  Function        :   public void onClick(View view)
             *  Description     :   Conducts certain instructions
             *                      when interacting with the dialog box
             *  Parameters      :   View view
             *  Returns         :   N/A
             */
            public void onClick(View view){
                //Getting the reference to the calendar object
                Calendar dueDateCal = Calendar.getInstance();
                int year = dueDateCal.get(Calendar.YEAR);   //Year variable
                int month = dueDateCal.get(Calendar.MONTH); //Month variable
                int day = dueDateCal.get(Calendar.DAY_OF_MONTH);    //Day variable

                //DatePicker to select due date of the assignment
                DatePickerDialog dueDateDialog = new DatePickerDialog(
                        AddingNewAssignmentActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        datePickerListener, year, month, day);
                dueDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
                dueDateDialog.show();   //Displaying the dialog boz
            }
        });

        datePickerListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            /*
             *  Function        :   public void onDateSet(DatePicker pickedDate, int year,
             *                                            int month, int day)
             *  Description     :   Formats the date selected from the user
             *  Parameters      :   Bundle savedInstanceState
             *  Returns         :   N/A
             */
            public void onDateSet(DatePicker pickedDate, int year, int month, int day){
                month += 1; //Acquiring the selected date
                //Formatting the selected date
                String selectedDate = month + "/" + day + "/" + year;
                dueDateBuffer.setText(selectedDate);    //Setting the formatted date to the buffer
            }
        };
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
        else if(whatButton == findViewById(R.id.SaveAssignmentBtn))
        {
            if((!validateFields()))
            {
                //Notifying the user that there is an error using toast
                Toast.makeText(AddingNewAssignmentActivity.this,"Error: One or more field is empty",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    //Saving the user's input to the database.
                    String assignmentName = assignmentNameBuffer.getText().toString();
                    String dueDate = dueDateBuffer.getText().toString();
                    String extraNote = extraNotes.getText().toString();
                    Integer priorityLevel = Integer.valueOf(selectedRadioButton.getText().toString());

                    //Instantiating a new assignment class filled with the user's input
                    Assignment newAssignment = new Assignment(assignmentName, dueDate, priorityLevel, extraNote);

                    //Instantiating a new DBManager to insert the new data to the database
                    DBManager databaseManager = new DBManager(getApplicationContext());
                    databaseManager.insertAssignment(newAssignment);
                    //Creating an intent to go back to the main activity
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();   //Rerunning back to the main activity

                }
                catch (Exception error)
                {
                    Log.e("Error","Received an exception " + error.getMessage());
                    //If there are an exceptions, close the activity and go back to the main activity with a cancelled result
                    Intent cancelledIntent = new Intent();
                    setResult(RESULT_CANCELED, cancelledIntent);
                    finish();   //Rerunning back to the main activity
                }
            }
        }

    }

    /*
     *  Method      :   boolean validateFields(View view)
     *  Description :   Validating the fields
     *  Parameters  :   N/A
     *  Returns     :   N/A
     */
    public boolean validateFields()
    {
        //Buffer variables to acquire the user's input
        assignmentNameBuffer = findViewById(R.id.assignmentField);
        dueDateBuffer = findViewById(R.id.dueDateField);
        priorityRadioGroup = findViewById(R.id.PriorityRadioBtns);
        extraNotes = findViewById(R.id.extraNoteWidget);


        //If the assignment name is empty
        if(assignmentNameBuffer.getText().toString().isEmpty())
        {
            assignmentNameBuffer.requestFocus();    //Setting focus to the blank field
            assignmentNameBuffer.setError("Field cannot be blank"); //Notifying the user the error
            return false;
        }
        //If the due date field is empty
        else if(dueDateBuffer.getText().toString().isEmpty())
        {
            dueDateBuffer.requestFocus();   //Setting focus to the blank field
            dueDateBuffer.setError("Field cannot be blank");    //Notifying the user to the error
            return false;
        }
        //If no radio button are checked
        if(priorityRadioGroup.getCheckedRadioButtonId() == -1)
        {
            return false;
        }
        else
        {
            //Getting the selected radio from radioGroup
            int selectedID = priorityRadioGroup.getCheckedRadioButtonId();
            //Obtaining the radiobutton by returned id
            selectedRadioButton = findViewById(selectedID);
        }
        return true;
    }
}

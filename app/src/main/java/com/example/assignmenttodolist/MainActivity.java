package com.example.assignmenttodolist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBManager databaseManager;

    List<Assignment> assignmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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


        return super.onOptionsItemSelected(item);
    }

    /*
     *  Function        :   public void onClick(View whatButton)
     *  Description     :   Does a set of instructions based on what button was clicked
     *  Parameters      :   View whatButton
     *  Returns         :   N/A
     */
    public void onClick(View whatButton){
        //If the button to add an assignment was clicked.
        if(whatButton == findViewById(R.id.AddAssignmentFloatingBtn))
        {
            Log.i("User Action:", "User is adding an assignment");
            try
            {
                Intent addingNewAssignmentIntent = new Intent(this, AddingNewAssignmentActivity.class);
                startActivityForResult(addingNewAssignmentIntent, 1);            }
            catch(Exception error)
            {
                Toast.makeText(getApplicationContext(), "Error, Cannot add an assignment at the time, try again.", Toast.LENGTH_LONG).show();
                Log.e("Error","Received an exception " + error.getMessage());
            }
        }

        if(whatButton == findViewById(R.id.displayList))
        {
            databaseManager = new DBManager(getApplicationContext());
            assignmentList = new ArrayList<>();
            assignmentList = databaseManager.getAllAssignments();
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
    protected  void onActivityResult(int requestCode, int codeResult, Intent intentData){
        super.onActivityResult(requestCode, codeResult, intentData);

        if(requestCode == 1)
        {
            if(codeResult == RESULT_OK)
            {
                //Variable that contains message from the addAssActivity
                String assignmentData = intentData.getStringExtra("firstReturnMessage");


                //Temporary ListView for the taskView widget
              /*  ListView assignmentList = findViewById(R.id.taskView);
                //Array adapter for the temporary list view
                ArrayAdapter<String> listAdapter = new ArrayAdapter<>
                        (this, android.R.layout.simple_list_item_1, assignmentItems);

                assignmentList.setAdapter(listAdapter); //Setting the listView to the adapter
                //Adding data inputted from the user to the taskView widget
                listAdapter.add(assignmentData);*/
            }
            else
            {
                Log.i("User Action:", "Operation Cancelled");
                //Displaying that adding a new assignment activity was cancelled
                Toast.makeText(getApplicationContext(),
                        "Operation Cancelled",
                        Toast.LENGTH_LONG).show();


            }
        }
    }

    /*
     *  Function        :   public void VisitPage(View view)
     *  Description     :   takes the user to the econestoga website for our favourite course, Mobile Application Development
     *  Parameters      :   View view
     *  Returns         :   N/A
     */
    public void VisitPage(View view) {
        String link = "https://conestoga.desire2learn.com/d2l/home/244302";
        Uri viewURI = Uri.parse(link);
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, viewURI);
        startActivity(viewIntent);
    }

    //I am incomplete
    public void PopulateList(ArrayList<Assignment> AssignmentList)
    {
       /* ListView AssignmntListView = findViewById(R.id.taskView);
        ArrayAdapter<Assignment> arrayAdapter = new ArrayAdapter<Assignment>(
                this, android.R.layout.simple_list_item_1, AssignmentList
        );
        AssignmntListView.setAdapter(arrayAdapter);*/
    }
}

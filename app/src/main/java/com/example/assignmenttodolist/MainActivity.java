package com.example.assignmenttodolist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import java.net.URI;
import java.util.ArrayList;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PopulateList(getAssignments(""));

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
                startActivity(addingNewAssignmentIntent);
            }
            catch(Exception error)
            {
                Toast.makeText(getApplicationContext(), "Error, Cannot add an assignment at the time, try again.", Toast.LENGTH_LONG).show();
                Log.e("Error","Received an exception " + error.getMessage());
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
        ListView AssignmntListView = findViewById(R.id.List);
        ArrayAdapter<Assignment> arrayAdapter = new ArrayAdapter<Assignment>(
                this, android.R.layout.simple_list_item_1, AssignmentList
        );
        AssignmntListView.setAdapter(arrayAdapter);
    }
}

package com.example.assignmenttodolist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
 * FILE:			AboutActivity.java
 * PROJECT:		    assignmentApp
 * PROGRAMMER:	    Oloruntoba Lagunju.
 *                  Gabriel Stewart
 *                  Connor Lynch
 * DATE:			April 5th 2019
 * DESCRIPTION:     This is a file that supports code for the about activity of the app
 *                  developed in java
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
}

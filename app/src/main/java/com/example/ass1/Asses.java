package com.example.ass1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ass1.MyAsyncTask.showAssesmentAsync;
import com.example.ass1.MyAsyncTask.getAssesmentAsync;

import java.lang.ref.WeakReference;

public class Asses extends AppCompatActivity implements View.OnClickListener {

    // Data
    String current = null;
    String currentCourse = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesments);
        //Third way to implement button click
        findViewById(R.id.btUpdate).setOnClickListener(this);






        // Loading Data
        Intent i = getIntent();
        if (i.hasExtra("current") && i.hasExtra("currentCourse")) {
            Bundle b = i.getExtras();
            try {

                current = (b.getString("current"));
                currentCourse = (b.getString("currentCourse"));
                new showAssesmentAsync(new WeakReference<Activity>(this),current,currentCourse).execute();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Data can not be loaded", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

    @Override
    public void onClick(View v) {






        // Acquired can increase total to accomadate bonus
        Intent i = getIntent();
        if (i.hasExtra("current")  && i.hasExtra("currentCourse")) {
            Bundle b = i.getExtras();
            try {
                  current = (b.getString("current"));
                  currentCourse = (b.getString("currentCourse"));
                  new getAssesmentAsync(new WeakReference<Activity>(this),current,currentCourse).execute();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Data can not be saved", Toast.LENGTH_SHORT).show();
            }






        }

    }
}

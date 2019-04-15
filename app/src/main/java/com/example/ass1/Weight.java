package com.example.ass1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.MyAsyncTask.getWeightAsync;
import com.example.ass1.MyAsyncTask.showWeightsAsync;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;

public class Weight extends AppCompatActivity implements View.OnClickListener {

    String current = null;
    String currentCourse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        findViewById(R.id.btUpdate).setOnClickListener(this);


        // On loading the screen Reload all the past weights
        Intent i = getIntent();
        if (i.hasExtra("current") && i.hasExtra("currentCourse")) {
            Bundle b = i.getExtras();
            try {
                current = (b.getString("current"));
                currentCourse = (b.getString("currentCourse"));
                new showWeightsAsync(new WeakReference<Activity>(this),current,currentCourse).execute();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Data can not be loaded", Toast.LENGTH_SHORT).show();
                return;
            }


        }

    }

    @Override
    public void onClick(View v) {


        // On loading the screen Reload all the past weights
        Intent i = getIntent();
        if (i.hasExtra("current") && i.hasExtra("currentCourse")) {
            Bundle b = i.getExtras();
            try {
                current = (b.getString("current"));
                currentCourse = (b.getString("currentCourse"));
                new getWeightAsync(new WeakReference<Activity>(this),current,currentCourse).execute();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Data can not be saved", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

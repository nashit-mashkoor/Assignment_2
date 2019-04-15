package com.example.ass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.Interfaces.loadStudentHome;
import com.example.ass1.MyAsyncTask.loadAbsoluteMarks;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.util.HashMap;
import java.util.HashSet;

public class StudentHome extends AppCompatActivity implements View.OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Student s = null;
        final String current;
        final String currentCourse;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);


        // Hooking the Links
        findViewById(R.id.tvAssLink).setOnClickListener(this);
        findViewById(R.id.tvWeightLink).setOnClickListener(this);



        // Checking if student has been added
        Intent i = getIntent();
        if(i.hasExtra("current") && i.hasExtra("currentCourse")) {
            Bundle b = i.getExtras();


            current = b.getString("current");
            currentCourse = b.getString("currentCourse");



            new loadAbsoluteMarks(this,current,currentCourse).execute();

       }

    }
    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        Intent past = getIntent();
        Bundle b = past.getExtras();

        String current ;
        String currentCourse;

        current = (b.getString("current"));
        currentCourse = (b.getString("currentCourse"));

        if(tv.getText().toString().equals("Assessments")) {
            Intent i = new Intent(this, Asses.class);
            i.putExtra ("current",current);
            i.putExtra ("currentCourse",currentCourse);
            startActivity(i);
        }
        else if(tv.getText().toString().equals("Weightages"))   {
            Intent i = new Intent(this, Weight.class);
            i.putExtra ("current",current);
            i.putExtra ("currentCourse",currentCourse);

            startActivity(i);

        }

    }


}

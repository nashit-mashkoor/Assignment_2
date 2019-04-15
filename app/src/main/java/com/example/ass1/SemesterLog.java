package com.example.ass1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass1.MyAsyncTask.addStudentAsync;
import com.example.ass1.MyAsyncTask.addStudentCourse;
import com.example.ass1.MyAsyncTask.checkingAsync;
import com.example.ass1.MyAsyncTask.deleteStudentCourseAsync;
import com.example.ass1.MyAsyncTask.loadStudentCourses;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SemesterLog extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    @Override
    protected void onStart() {
        super.onStart();
//        if (rv != null) {
//
//            if (data.get(current).getAllCourse().isEmpty()) {
//                Toast toast = Toast.makeText(this, "No courses to show. Add below", Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                toast.show();
//            }
//        }
    }


    String current;
    Student s;
    HashMap<String, Student> data;
    HashSet<String> studentKey;
    RecyclerView rv;
    CourseAdapter cAdapter;
    GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_log);
        loadCourse();
        this.findViewById(R.id.btAddCourse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = SemesterLog.this.getIntent();
                if (i.hasExtra("current")) {


                    String n = ((EditText) SemesterLog.this.findViewById(R.id.etCourseInput)).getText().toString();

                    Log.d("myAsync", "Course added");
                    if( n != null &&!n.isEmpty()) {
                        new addStudentCourse(SemesterLog.this.getApplicationContext(), current, n, cAdapter,(EditText) SemesterLog.this.findViewById(R.id.etCourseInput)).execute();
                    }
                }
            }
        });

        // FOr a list not working
//        ((SearchView)findViewById(R.id.svSearch)).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                cAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });


    }


    public void loadCourse() {
        // Checking if student has been added
        Intent i = getIntent();
        if (i.hasExtra("current")) {
            Bundle b = i.getExtras();
            current = b.getString("current");

            rv = this.findViewById(R.id.rv);

            // loading Async Task
            Log.d("myAsync", "Activity loaded");

            gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                    View child = rv.findChildViewUnder(e.getX(),e.getY());
                    if (child != null) {

                        int num = rv.getChildAdapterPosition(child);

                        try {
                            Course c = ((CourseAdapter)rv.getAdapter()).getCourse(num);
                            new deleteStudentCourseAsync(c,SemesterLog.this,(CourseAdapter) rv.getAdapter()).execute();
                        }
                        catch (Exception error) {

                        }

                    }
                    return ;

                }




                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    //      Toast.makeText(c,"onSingleTap",Toast.LENGTH_SHORT).show();

                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    if (child != null) {

                        int num = rv.getChildAdapterPosition(child);
                        Intent j = new Intent(getApplicationContext(), StudentHome.class);



                        // Transferring data to summary activity
                        j.putExtra("current", current);
                        j.putExtra("currentCourse", ((CourseAdapter)rv.getAdapter()).getCourse(num).getName());

                        startActivity(j);

                    }
                    return true;
                }
            });

            rv = this.findViewById(R.id.rv);

            cAdapter = new CourseAdapter(this,  new ArrayList<Course>());

            rv.setAdapter(cAdapter);

            rv.setLayoutManager(new LinearLayoutManager(this));

            rv.addOnItemTouchListener(this);

            new loadStudentCourses(cAdapter, current, new WeakReference<Activity>(this), gd, rv).execute();

        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        gd.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

}





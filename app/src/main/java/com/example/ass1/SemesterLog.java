package com.example.ass1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SemesterLog extends AppCompatActivity implements RecyclerView.OnItemTouchListener{
    @Override
    protected void onStart() {
        super.onStart();
        if(rv != null)  {

            if(data.get(current).getAllCourse().isEmpty()) {
                Toast toast = Toast.makeText(this, "No courses to show. Add below", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }
        }
    }

    String current;
    Student s;
    HashMap<String,Student> data;
    HashSet<String> studentKey;
    RecyclerView rv;
    CourseAdapter cAdapter;
    GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_log);
        loadCourse();

        findViewById(R.id.btAddCourse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i = getIntent();
                 if (i.hasExtra("current") && i.hasExtra("data") && i.hasExtra("studentKey"))
                    {

                        try {

                            String n = ((EditText) findViewById(R.id.etCourseInput)).getText().toString();
                            boolean found = false;
                            ArrayList<Course> iterator = s.getAllCourse();
                            for (int j =0 ; j < iterator.size();j++)    {
                                String m = iterator.get(j).getName();
                                if(m.equals(n) || n.isEmpty()) {
                                    throw new Exception();
                                }
                            }
                            if(iterator.size() == 0 &&  n.isEmpty()){
                                throw new Exception();
                            }

                            s.getAllCourse().add(new Course (n));
                            Toast.makeText(getApplicationContext(), "Course successfully added", Toast.LENGTH_SHORT).show();
                            cAdapter.notifyDataSetChanged();
                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(), "Course cannot be added", Toast.LENGTH_SHORT).show();
                            return;
                        }
                }
            }
        });

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








    public void loadCourse()    {
        // Checking if student has been added
        Intent i = getIntent();
        if(i.hasExtra("current") && i.hasExtra("data") &&  i.hasExtra("studentKey")) {
            Bundle b = i.getExtras();

            data = (HashMap<String, Student>) b.getSerializable("data");

            studentKey = (HashSet<String>) b.getSerializable("studentKey");
            current = b.getString("current");
            s = (data.get(b.getString("current")));


             gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    //      Toast.makeText(c,"onSingleTap",Toast.LENGTH_SHORT).show();

                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    if(child != null)
                    {

                        int num = rv.getChildAdapterPosition(child);
                        Intent j = new Intent(getApplicationContext(), StudentHome.class);

                        s.setCourse(s.getAllCourse().get(num));

                        // Transferring data to summary activity
                        j.putExtra("current", current);
                        j.putExtra("data", data);
                        j.putExtra("studentKey", studentKey);

                        startActivity(j);

                    }
                    return true;
                }
            });

            rv = this.findViewById(R.id.rv);

            cAdapter = new CourseAdapter(this,s.getAllCourse());

            rv.setAdapter(cAdapter);

            rv.setLayoutManager( new LinearLayoutManager(this));

            rv.addOnItemTouchListener(this);


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

package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass1.CourseAdapter;
import com.example.ass1.R;
import com.example.ass1.SemesterLog;
import com.example.ass1.StudentHome;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;
import java.util.List;

public class loadStudentCourses extends AsyncTask<Void, Void, Void> {

    CourseAdapter cAdapter;
    String current;
    WeakReference<Activity> c;
    GestureDetector gd;
    RecyclerView rv;
    AppDatabase db;
    Student s;
    List<Course> cList;
    Integer error;

    public loadStudentCourses(CourseAdapter cAdapter, String current, WeakReference<Activity> c, GestureDetector gd, RecyclerView rv) {
        this.cAdapter = cAdapter;
        this.current = current;
        this.c = c;
        this.gd = gd;
        this.rv = rv;
        db = AppDatabase.getAppDatabase(c.get().getApplicationContext());
        error = 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("myAsync", "dO IN BACKGROUND CALLED");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Database call

        try {

            s = (db.studentDao().getStudent(current));
            cList = db.courseDao().getStudentCourse(current);


        } catch (Exception e) {
            error = 1;

        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (error == 0) {
            if (cList.size() == 0) {
                Toast.makeText(c.get(), "No courses to show", Toast.LENGTH_SHORT).show();
            }
            else {
                cAdapter.updateData(cList);
                cAdapter.notifyDataSetChanged();
            }
        }
        else {
            Toast.makeText(c.get(), "Course List not loaded", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.ass1.StudentHome;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;

public class saveAssesmentAsync extends AsyncTask<Void, Void, Void> {

    WeakReference<Activity> ca;
    String current;
    String currentCourse;
    Student s;
    Course c;
    Boolean error ;

    public saveAssesmentAsync(WeakReference<Activity> ca, Course c, Boolean er) {
        this.ca = ca;
        this.c = c;
        error = er;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Going to the next Activity

        try {
            if (!this.error) {
                Intent j = new Intent(ca.get(), StudentHome.class);
                j.putExtra("current", c.getFuserName());
                j.putExtra("currentCourse", c.getName());
                Toast.makeText(ca.get(), "Updated ", Toast.LENGTH_SHORT).show();
                ca.get().startActivity(j);
            }

        } catch (Exception e) {
            Toast.makeText(ca.get(), "Problem loading application ", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {

        RoomDatabase db = AppDatabase.getAppDatabase(ca.get());
        ((AppDatabase) db).courseDao().updateCourse(c);
        Log.d("Course 1: ", c.getName() + " / " + String.valueOf(c.getaAss1()) + " / " + String.valueOf(c.gettAss1()));
        for (Course c : ((AppDatabase) db).courseDao().getAllCourse()) {
            Log.d("Course 2: ", c.getName() + " / " + String.valueOf(c.getaAss2()) + " / " + String.valueOf(c.gettAss2()));
        }
        return null;
    }
}

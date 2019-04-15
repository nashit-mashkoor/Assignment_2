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

public class saveWeightsAsync extends AsyncTask<Void, Void, Void> {

        WeakReference<Activity> ca;
        String current;
        String currentCourse;
        Student s;
        Course c;

    public saveWeightsAsync(WeakReference<Activity> ca, Course c) {
        this.ca = ca;
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            Intent j = new Intent(ca.get(), StudentHome.class);
            j.putExtra("current", c.getFuserName());
            j.putExtra("currentCourse",c.getName());
            Toast.makeText(ca.get(), "Updated ", Toast.LENGTH_SHORT).show();
            ca.get().startActivity(j);
        } catch (Exception e) {
            Toast.makeText(ca.get(), "Problem loading application ", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {


        RoomDatabase db = AppDatabase.getAppDatabase(ca.get());
        ((AppDatabase) db).courseDao().updateCourse(c);
        Log.d("CourseWsave: ", c.getName() + " / " + String.valueOf(c.getwAssignment()) + " / " + String.valueOf(c.getwMid_1()));

        return null;
    }
}
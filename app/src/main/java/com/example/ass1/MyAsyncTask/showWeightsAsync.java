package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.R;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;

public class showWeightsAsync extends AsyncTask<Void, Void, Void> {

    WeakReference<Activity> c;
    String current;
    String currentCourse;
    Student s;
    Course cr;

    public showWeightsAsync(WeakReference<Activity> c, String current, String currentCourse) {
        this.c = c;
        this.current = current;
        this.currentCourse = currentCourse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        TextView etAss = null;
        TextView etProject = null;
        TextView etMid_1 = null;
        TextView etMid_2 = null;
        TextView etFinal = null;
        // Loading all te views
        try {
            etAss = (TextView) c.get().findViewById(R.id.etAss);
            etProject = (TextView) c.get().findViewById(R.id.etProject);
            etMid_1 = (TextView) c.get().findViewById(R.id.etMid_1);
            etMid_2 = (TextView) c.get().findViewById(R.id.etMid_2);
            etFinal = (TextView) c.get().findViewById(R.id.etFinal);

        } catch (Exception e) {
            Toast.makeText(c.get(), "View cannot be loaded ", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            // Loading the data
            etAss.setText(String.valueOf(cr.getwAssignment()));
            etFinal.setText(String.valueOf(cr.getwFinal()));
            etProject.setText(String.valueOf(cr.getwProject()));
            etMid_1.setText(String.valueOf(cr.getwMid_1()));
            etMid_2.setText(String.valueOf(cr.getwMid_2()));

        } catch (Exception e) {
            Toast.makeText(c.get(), "View cannot be loaded ", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    protected Void doInBackground(Void... voids) {
         try {
            RoomDatabase db = AppDatabase.getAppDatabase(c.get());
            s = ((AppDatabase) db).studentDao().getStudent(current);
            cr = ((AppDatabase) db).courseDao().getCourse(currentCourse,current);
             Log.d("CourseWshow: ", cr.getName() + " / " + String.valueOf(cr.getwAssignment()) + " / " + String.valueOf(cr.getwMid_1()));
        }
        catch   (Exception e)   {

            Toast.makeText(c.get(),"Weights can not be loaded", Toast.LENGTH_SHORT);
        }
        return null;
    }
}

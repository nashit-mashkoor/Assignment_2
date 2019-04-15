package com.example.ass1.MyAsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass1.CourseAdapter;
import com.example.ass1.SemesterLog;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;

import java.lang.ref.WeakReference;
import java.util.List;

public class addStudentCourse extends AsyncTask<Void, Void,Void> {

    String current;
    String currentCourse;
    CourseAdapter cAdapter;
    Integer error;
    WeakReference<Context> c;
    AppDatabase db;
    List<Course> cList;
    EditText et;
    public addStudentCourse(Context C, String current, String currentCourse, CourseAdapter cAdapter, EditText et) {
        c =  new WeakReference<Context>(C);
        this.current = current;
        this.currentCourse = currentCourse;
        this.cAdapter = cAdapter;
        this.error = 0;
        this.et = et;
        db = AppDatabase.getAppDatabase(c.get().getApplicationContext());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(Void... voids) {

        try {
            if(db.courseDao().isCoursePresent(currentCourse,current) == 0){
                  db.courseDao().addCourse( new Course(currentCourse,current));
                  cList = db.courseDao().getStudentCourse(current);
            }
            else {
                error = 1;
            }
        }
        catch(Exception e)  {
            error = 1;

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(error == 0)   {

            Log.d("myAsync",String.valueOf(cAdapter.hashCode()));
            Toast.makeText(c.get(), "Course successfully added", Toast.LENGTH_SHORT).show();

            et.setText("");
            cAdapter.updateData(cList);
        }
        else {
            Toast.makeText(c.get(), "Course cannot be added", Toast.LENGTH_SHORT).show();
        }
    }

}

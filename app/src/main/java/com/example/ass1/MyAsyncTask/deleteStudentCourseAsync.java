package com.example.ass1.MyAsyncTask;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ass1.CourseAdapter;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;

import java.lang.ref.WeakReference;
import java.util.List;

public class deleteStudentCourseAsync extends AsyncTask<Void, Void, Void> {
    Course course;
    WeakReference<Context> c;
    CourseAdapter cAdapter;
    List<Course> cList;
    public deleteStudentCourseAsync(Course course, Context c, CourseAdapter cAdapter) {

        this.course = course;
        this.c =  new WeakReference<>(c);
        this.cAdapter = cAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        cAdapter.updateData(cList);
        Toast.makeText(c.get(),"The course is deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            AppDatabase.getAppDatabase(c.get()).courseDao().deleteCourse(course);
            cList = AppDatabase.getAppDatabase(c.get()).courseDao().getStudentCourse(course.getFuserName());


        }
        catch(Exception e)   {
            Toast.makeText(c.get(),"The course could not be deleted", Toast.LENGTH_SHORT).show();

        }
        return null;
    }
}

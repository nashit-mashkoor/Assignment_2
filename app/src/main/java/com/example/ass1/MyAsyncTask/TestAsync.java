package com.example.ass1.MyAsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

public class TestAsync extends AsyncTask <Void,Void,Void>  {

    Context c;
    Course s;
    public TestAsync(Context C, Course S) {
        c = C;
        this.s =S;

    }
    protected Void doInBackground(Void... voids) {

          AppDatabase db = AppDatabase.getAppDatabase(c);
          db.clearAllTables();


        return null;

    }
}

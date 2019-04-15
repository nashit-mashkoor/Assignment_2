package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass1.R;
import com.example.ass1.SemesterLog;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;

public class checkStudentAsync extends AsyncTask<Void, Void, Void> {

    WeakReference<Activity> c;
    AppDatabase db;
    String etUser;
    String etPass;

    Integer error;

    public checkStudentAsync(WeakReference<Activity> c, String un, String pw) {
        this.c = c;
        db = AppDatabase.getAppDatabase(c.get().getApplicationContext());
        etUser = un;
        etPass = pw;
        error = 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    protected Void doInBackground(Void... voids) {

        try {
            if (db.studentDao().isStudentPresent(etUser) != 0) {
                if (db.studentDao().isStudentPresent(etUser, etPass) != 0) {
                    error = 0;
                }
                else {
                    error = 1;
                }
            }
            else {
                error = 2;

            }
        } catch (Exception e) {

            error = 3;

        }

        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        db = null;
        if (error == 0) {
            //Intent j = new Intent(getApplicationContext(), StudentHome.class);
            Intent j = new Intent(c.get().getApplicationContext(), SemesterLog.class);

            // Transferring data to summary activity
            j.putExtra("current", etUser);
//                j.putExtra("data", data);
//                j.putExtra("studentKey", studentKey);

            // Storing the last user login in  the application using shared preferences
            SharedPreferences sharedPref = c.get().getSharedPreferences("lastLogin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("lastEmail", etUser);
            editor.putString("lastPassword", etPass);
            editor.commit();


            c.get().startActivity(j);
        }
        else if (error == 1) {
            Toast.makeText(c.get(), "Incorrect Password", Toast.LENGTH_SHORT).show();
        }
        else if (error == 2) {
            Toast.makeText(c.get(), "Student not found", Toast.LENGTH_SHORT).show();
        }
        else if (error == 3) {
            Toast.makeText(c.get(), "Database not accessible", Toast.LENGTH_SHORT).show();
        }
    }


}

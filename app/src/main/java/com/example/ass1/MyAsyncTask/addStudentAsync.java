package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass1.LogIn;
import com.example.ass1.R;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

public class addStudentAsync extends AsyncTask<Void, Void, Void> {


    WeakReference<Activity> c;
    Intent i;

    AppDatabase db;

    Boolean error = false;
    String etRoll;
    String etBatch;
    String etName;
    String etRePass;
    String etPass;
    String etUser;
    Student s;

    public addStudentAsync(WeakReference<Activity> C) {


        c = C;
        db = AppDatabase.getAppDatabase(c.get().getApplicationContext());

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        etUser = ((EditText) (c.get().findViewById(R.id.etUsername))).getText().toString();
        etPass = ((EditText) c.get().findViewById(R.id.etPass)).getText().toString();
        etRePass = ((EditText) c.get().findViewById(R.id.etRePass)).getText().toString();
        etName = ((EditText) c.get().findViewById(R.id.etName)).getText().toString();
        etBatch = ((EditText) c.get().findViewById(R.id.etBatch)).getText().toString();
        etRoll = ((EditText) c.get().findViewById(R.id.etRoll)).getText().toString();

        if (etUser.isEmpty() || etPass.isEmpty() || etRePass.isEmpty() || etName.isEmpty() || etBatch.isEmpty() || etRoll.isEmpty()) {
            Toast.makeText(c.get(), "Fields are empty", Toast.LENGTH_SHORT).show();
            error = true;
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etUser).matches()) {
            Toast.makeText(c.get(), "Enter a valid email", Toast.LENGTH_SHORT).show();
            error = true;
            return;
        }
        if (!(Pattern.compile("^[a-zA-Z ]+$").matcher(etName).matches())) {
            Toast.makeText(c.get(), "Name can only be alphabets", Toast.LENGTH_SHORT).show();
            error = true;
            return;
        }
        if (!etPass.equals(etRePass)) {
            Toast.makeText(c.get(), "Enter the same password", Toast.LENGTH_SHORT).show();
            error = true;
            return;
        }
        if (!(Pattern.compile("^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$").matcher(etBatch).matches())) {
            Toast.makeText(c.get(), "Batch  can only  be numerical", Toast.LENGTH_SHORT).show();

            error = true;
            return;
        }

    }

    @Override
    protected Void doInBackground(Void... Void) {
        if (!error) {
            try {
                if (db.studentDao().isStudentPresent(etUser) != 0) {
                    Toast.makeText(c.get(), "User Name already taken", Toast.LENGTH_SHORT).show();
                    error = true;
                    return null;
                }
                s = new Student(etUser, etPass, etName, etRoll, etBatch);
                // Adding to the database
                db.studentDao().addStudent(s);
            } catch (Exception e) {
                error = true;
            }
        }
        // Creating student and adding it to data
        // The student is being created here


        //        data.put(etUser,s);
        //        studentKey.add(etUser);
        return null;

    }







//            i.putExtra("data",data);
//            i.putExtra("studentKey",studentKey);



    ;


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        db = null;
        if(!error)  {
            i = new Intent(c.get(),LogIn.class);
            i.putExtra("new",s);
            Toast.makeText(c.get(),"Student created",Toast.LENGTH_SHORT).show();
            c.get().startActivity(i);

        }
        else    {
            Toast.makeText(c.get(),"User name already taken",Toast.LENGTH_SHORT).show();

        }


    }
}

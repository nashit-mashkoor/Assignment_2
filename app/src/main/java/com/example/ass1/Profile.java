package com.example.ass1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass1.MyAsyncTask.addStudentAsync;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


    }

    public void createProfile(View v) {
//        Bundle b = this.getIntent().getExtras();
//        HashSet studentKey = (HashSet) b.getSerializable("studentKey");
//        HashMap data = (HashMap) b.getSerializable("data");

        new addStudentAsync (new WeakReference<Activity>(this)).execute();
    }
}

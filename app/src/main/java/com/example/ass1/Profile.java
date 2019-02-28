package com.example.ass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass1.data.Student;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


    }

    public void createProfile(View v)   {
        Bundle b = this.getIntent().getExtras();
        HashSet studentKey = (HashSet) b.getSerializable("studentKey");
        HashMap data = (HashMap) b.getSerializable("data");


        String etUser   = ((EditText)(findViewById(R.id.etUsername))).getText().toString();
        String etPass   = ((EditText)findViewById(R.id.etPass)).getText().toString();
        String etRePass = ((EditText)findViewById(R.id.etRePass)).getText().toString();
        String etName   = ((EditText)findViewById(R.id.etName)).getText().toString();
        String etBatch  = ((EditText)findViewById(R.id.etBatch)).getText().toString();
        String etRoll   = ((EditText)findViewById(R.id.etRoll)).getText().toString();

        if(etUser.isEmpty() || etPass.isEmpty() || etRePass.isEmpty() || etName.isEmpty() || etBatch.isEmpty() || etRoll.isEmpty()) {
            Toast.makeText(this,"Fields are empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(studentKey.contains(etUser)) {
            Toast.makeText(this,"User Name already taken",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(etUser).matches())    {
            Toast.makeText(this,"Enter a valid email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(Pattern.compile("^[a-zA-Z ]+$").matcher(etName).matches())){
            Toast.makeText(this,"Name can only be alphabets",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!etPass.equals(etRePass)) {
            Toast.makeText(this,"Enter the same password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(Pattern.compile("^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$").matcher(etBatch).matches())){
            Toast.makeText(this,"Batch  can only  be numerical",Toast.LENGTH_SHORT).show();
            return;
        }


        // Creating student and adding it to data
        // The student is being created here

        Student s = new Student(etUser,etPass,etName,etRoll,etBatch);
        data.put(etUser,s);
        studentKey.add(etUser);

        Toast.makeText(this,"Student created",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, LogIn.class);

        i.putExtra("new",s);
        i.putExtra("data",data);
        i.putExtra("studentKey",studentKey);


        startActivity(i);

    }
}

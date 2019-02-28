package com.example.ass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.data.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LogIn extends AppCompatActivity {
     HashSet<String> studentKey = new HashSet<>();
     HashMap<String, Student>  data = new HashMap<>();
     String current = null;
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);




        EditText etUser = findViewById(R.id.etUser);
        EditText etPass = findViewById(R.id.etPass);
        TextView link = findViewById(R.id.tvRegLink);



        // Second Way to implement button listener
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Profile.class);
                i.putExtra("data",  data);
                i.putExtra ("studentKey",studentKey);
                startActivity(i);
            }

        });

        // Code run when return from user generated
        current = null;

        if(getIntent().hasExtra("new")) {
            Bundle b = this.getIntent().getExtras();


            String u =((Student)b.getSerializable("new")).getUsername() ;
            String p =((Student)b.getSerializable("new")).getPassword() ;
            etUser.setText(u);
            etPass.setText(p);
            current = u;
        }
        if(getIntent().hasExtra("data")) {
            Bundle b = this.getIntent().getExtras();
            data =((HashMap<String,Student>)b.getSerializable("data"));

        }
        if(getIntent().hasExtra("studentKey")) {
            Bundle b = this.getIntent().getExtras();
            studentKey =((HashSet<String>)b.getSerializable("studentKey"));

        }

    }

    public void login( View v)  {

        String etUser = ((EditText)findViewById(R.id.etUser)).getText().toString();
        String etPass = ((EditText)findViewById(R.id.etPass)).getText().toString();
        if(etUser.equals("") || etPass.equals(""))    {
            Toast.makeText(this,"Fields are empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(etUser).matches())    {
            Toast.makeText(this,"Enter a valid email",Toast.LENGTH_SHORT).show();
            return;
        }


        if(studentKey.contains(etUser))     {


            Student s = data.get(etUser);
            if(s.getPassword().equals(etPass)) {
                //Intent j = new Intent(getApplicationContext(), StudentHome.class);
                Intent j = new Intent(getApplicationContext(), SemesterLog.class);

                // Transferring data to summary activity
                j.putExtra("current", current);
                j.putExtra("data", data);
                j.putExtra("studentKey", studentKey);

                startActivity(j);
            }
            else {

                Toast.makeText(this,"Incorrect Password",Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(this,"Student not found",Toast.LENGTH_SHORT).show();
        }



    }
}

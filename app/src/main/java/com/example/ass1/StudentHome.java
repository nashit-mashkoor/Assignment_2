package com.example.ass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudentHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        // Hooking the Links
        findViewById(R.id.tvAssLink).setOnClickListener(this);
        findViewById(R.id.tvWeightLink).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        if(tv.getText().toString().equals("Assessments")) {
            Intent i = new Intent(this, Asses.class);
            startActivity(i);
        }
        else if(tv.getText().toString().equals("Weightages"))   {
            Intent i = new Intent(this, Weight.class);
            startActivity(i);

        }

    }
}

package com.example.ass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Weight extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        findViewById(R.id.btUpdate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent( this, StudentHome.class);
        startActivity(i);

    }
}

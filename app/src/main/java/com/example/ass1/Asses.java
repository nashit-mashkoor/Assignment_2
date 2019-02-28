package com.example.ass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.util.HashMap;
import java.util.HashSet;

public class Asses extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesments);
        //Third way to implement button click
        findViewById(R.id.btUpdate).setOnClickListener(this);

        // Data
        String current = null;
        HashMap<String, Student> data = null;
        HashSet<String> studentKey;

        TextView etGAss1 = (TextView) findViewById(R.id.etGAss1);
        TextView etGAss2 = (TextView) findViewById(R.id.etGAss2);
        TextView etGAss3 = (TextView) findViewById(R.id.etGAss3);
        TextView etGMid_1 = (TextView) findViewById(R.id.etGMid_1);
        TextView etGMid_2 = (TextView) findViewById(R.id.etGMid_2);
        TextView etGProj = (TextView) findViewById(R.id.etGProj);
        TextView etGFinal = (TextView) findViewById(R.id.etGFinal);

        TextView etTAss1 = (TextView) findViewById(R.id.etTAss1);
        TextView etTAss2 = (TextView) findViewById(R.id.etTAss2);
        TextView etTAss3 = (TextView) findViewById(R.id.etTAss3);
        TextView etTMid_1 = (TextView) findViewById(R.id.etTMid_1);
        TextView etTMid_2 = (TextView) findViewById(R.id.etTMid_2);
        TextView etTProj = (TextView) findViewById(R.id.etTProj);
        TextView etTFinal = (TextView) findViewById(R.id.etTFinal);


        // Loading Data
        Intent i = getIntent();
        if (i.hasExtra("current")) {
            Bundle b = i.getExtras();
            try {
                data = (HashMap<String, Student>) b.getSerializable("data");
                ;
                studentKey = (HashSet<String>) b.getSerializable("studentKey");
                current = (b.getString("current"));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Data can not be loaded", Toast.LENGTH_SHORT).show();
                return;
            }
            // Loading the data
            if (current != null && data != null) {
                Course c = data.get(current).getCourse();

                try {
                    if (c.getaAss1() != -1.0) {
                        etGAss1.setText(String.valueOf(c.getaAss1()));
                    }
                    if (c.getaAss2() != -1.0) {
                        etGAss2.setText(String.valueOf(c.getaAss2()));
                    }
                    if (c.getaAss3() != -1.0) {
                        etGAss3.setText(String.valueOf(c.getaAss3()));
                    }
                    if (c.gettAss1() != -1.0) {
                        etTAss1.setText(String.valueOf(c.gettAss1()));
                    }
                    if (c.gettAss2() != -1.0) {
                        etTAss2.setText(String.valueOf(c.gettAss2()));
                    }
                    if (c.gettAss3() != -1.0) {
                        etTAss3.setText(String.valueOf(c.gettAss3()));
                    }

                    if (c.getaProject() != -1.0) {
                        etGProj.setText(String.valueOf(c.getaProject()));

                    }
                    if (c.gettProject() != -1.0) {
                        etTProj.setText(String.valueOf(c.gettProject()));

                    }

                    if (c.getaMid_1() != -1.0) {
                        etGMid_1.setText(String.valueOf(c.getaMid_1()));
                    }
                    if (c.gettMid_1() != -1.0) {
                        etTMid_1.setText(String.valueOf(c.gettMid_1()));
                    }
                    if (c.getaMid_2() != -1.0) {
                        etGMid_2.setText(String.valueOf(c.getaMid_2()));
                    }
                    if (c.gettMid_2() != -1.0) {
                        etTMid_2.setText(String.valueOf(c.gettMid_2()));
                    }

                    if (c.getaProject() != -1.0) {
                        etGProj.setText(String.valueOf(c.getaProject()));

                    }
                    if (c.gettProject() != -1.0) {
                        etTProj.setText(String.valueOf(c.gettProject()));

                    }

                    if (c.getaFinal() != -1.0) {
                        etGFinal.setText(String.valueOf(c.getaFinal()));

                    }
                    if (c.gettFinal() != -1.0) {
                        etTFinal.setText(String.valueOf(c.gettFinal()));

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "View cannot be loaded ", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent j = new Intent(getApplicationContext(), StudentHome.class);

        // Data
        String current = null;
        HashMap<String, Student> data = null;
        HashSet<String> studentKey = null;


        // Acquired can increase total to accomadate bonus
        Intent i = getIntent();
        if (i.hasExtra("current")) {
            Bundle b = i.getExtras();
            try {
                data = (HashMap<String, Student>) b.getSerializable("data");

                studentKey = (HashSet<String>) b.getSerializable("studentKey");
                current = (b.getString("current"));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Data can not be saved", Toast.LENGTH_SHORT).show();
            }
            // Loading all te views


                Course c = data.get(current).getCourse();

                TextView etGAss1 = (TextView) findViewById(R.id.etGAss1);
                TextView etGAss2 = (TextView) findViewById(R.id.etGAss2);
                TextView etGAss3 = (TextView) findViewById(R.id.etGAss3);
                TextView etGMid_1 = (TextView) findViewById(R.id.etGMid_1);
                TextView etGMid_2 = (TextView) findViewById(R.id.etGMid_2);
                TextView etGProj = (TextView) findViewById(R.id.etGProj);
                TextView etGFinal = (TextView) findViewById(R.id.etGFinal);

                TextView etTAss1 = (TextView) findViewById(R.id.etTAss1);
                TextView etTAss2 = (TextView) findViewById(R.id.etTAss2);
                TextView etTAss3 = (TextView) findViewById(R.id.etTAss3);
                TextView etTMid_1 = (TextView) findViewById(R.id.etTMid_1);
                TextView etTMid_2 = (TextView) findViewById(R.id.etTMid_2);
                TextView etTProj = (TextView) findViewById(R.id.etTProj);
                TextView etTFinal = (TextView) findViewById(R.id.etTFinal);


            // Updating data
            try {

                if(!etGAss1.getText().toString().isEmpty())  {
                    Double t =Double.valueOf(etGAss1.getText().toString()) ;
                    if(t < 0)   {
                        throw new Exception();
                    }
                    if(!etTAss1.getText().toString().isEmpty())  {
                        Double t1 =Double.valueOf(etTAss1.getText().toString()) ;
                        if(t1 < 0 || t > t1)   {
                            throw new Exception();
                        }
                        c.settAss1(t1);
                        c.setaAss1(t);
                        c.updateAllAssign();
                    }
                    else {
                        throw new Exception();
                    }


                }
                if(!etGAss2.getText().toString().isEmpty())  {
                    Double t =Double.valueOf(etGAss2.getText().toString()) ;
                    if(t < 0)   {
                        throw new Exception();
                    }
                    if(!etTAss2.getText().toString().isEmpty())  {
                        Double t1 = Double.valueOf(etTAss2.getText().toString());
                        if(t1 < 0 || t>t1)   {
                            throw new Exception();
                        }
                        c.settAss2(t1);
                        c.setaAss2(t);
                        c.updateAllAssign();
                    }
                    else    {
                        throw new Exception();
                    }

                }
                if(!etGAss3.getText().toString().isEmpty())  {
                    Double t =Double.valueOf(etGAss3.getText().toString()) ;
                    if(t < 0)   {
                        throw new Exception();
                    }
                    if(!etTAss3.getText().toString().isEmpty())  {
                        Double t1 = Double.valueOf(etTAss3.getText().toString());
                        if(t1 < 0 || t > t1)   {
                            throw new Exception();
                        }
                        c.settAss3(t1);
                        c.setaAss3(t);
                        c.updateAllAssign();
                    }
                    else    {
                        throw new Exception();
                    }


                }




                if(!etGProj.getText().toString().isEmpty()) {
                    Double t = Double.valueOf(etGProj.getText().toString());
                    if(t < 0)   {
                        throw new Exception();
                    }
                    if(!etTProj.getText().toString().isEmpty()) {
                        Double t1 = Double.valueOf(etTProj.getText().toString());
                        if(t1 < 0 || t > t1)   {
                            throw new Exception();
                        }
                        c.settProject(t1);
                        c.setaProject(t);
                    }
                    else {
                        throw new Exception();
                    }

                }


                if(!etGMid_1.getText().toString().isEmpty()) {
                    Double t = Double.valueOf(etGMid_1.getText().toString());
                    if(t < 0)   {
                        throw new Exception();
                    }
                    if(!etTMid_1.getText().toString().isEmpty()) {
                        Double t1 = Double.valueOf(etTMid_1.getText().toString());
                        if(t1 < 0 || t > t1)   {
                            throw new Exception();
                        }
                        c.settMid_1(t1);
                        c.setaMid_1(t);
                    }
                    else {
                        throw new Exception();
                    }

                }

                if(!etGMid_2.getText().toString().isEmpty()) {
                    Double t = Double.valueOf(etGMid_2.getText().toString());
                    if(t < 0)   {
                        throw new Exception();
                    }
                    if(!etTMid_2.getText().toString().isEmpty()) {
                        Double t1 = Double.valueOf(etTMid_2.getText().toString());
                        if(t1 < 0 || t > t1)   {
                            throw new Exception();
                        }
                        c.settMid_2(t1);
                        c.setaMid_2(t);
                    }
                    else {
                        throw new Exception();
                    }

                }


                if(!etGFinal.getText().toString().isEmpty()) {
                    Double t = Double.valueOf(etGFinal.getText().toString());
                    if(t < 0)   {
                        throw new Exception();
                    }
                    if(!etTFinal.getText().toString().isEmpty()) {
                        Double t1 =Double.valueOf(etTFinal.getText().toString());
                        if(t1 < 0 || t > t1)   {
                            throw new Exception();
                        }
                        c.settFinal(t1);
                        c.setaFinal(t);
                    }
                    else    {
                        throw new Exception();
                    }

                }


            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Invalid Input ", Toast.LENGTH_SHORT).show();
                return;

            }

            // Going to the next Activity

            try {
                j.putExtra("current", current);
                j.putExtra("data", data);
                j.putExtra("studentKey", studentKey);
                startActivity(j);
                Toast.makeText(getApplicationContext(), "Updated ", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Problem loading application ", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }
}

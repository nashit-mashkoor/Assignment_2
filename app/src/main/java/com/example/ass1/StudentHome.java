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

public class StudentHome extends AppCompatActivity implements View.OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Student s;
        final HashMap<String,Student> data;
        final HashSet<String> studentKey;
        final String current;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);


        // Hooking the Links
        findViewById(R.id.tvAssLink).setOnClickListener(this);
        findViewById(R.id.tvWeightLink).setOnClickListener(this);



        // Checking if student has been added
        Intent i = getIntent();
        if(i.hasExtra("current") && i.hasExtra("data") &&  i.hasExtra("studentKey")) {
            Bundle b = i.getExtras();

            data = (HashMap<String , Student>) b.getSerializable("data");
            studentKey = (HashSet<String>) b.getSerializable("studentKey");
            current = b.getString("current");
            s = (data.get(b.getString("current")));


            ((TextView)findViewById(R.id.tvName)).setText(s.getName());
            ((TextView)findViewById(R.id.tvRoll)).setText(s.getBatch());
            ((TextView)findViewById(R.id.tvBatch)).setText(s.getRollNum());

            // Updating the absolute marks
             try  {
                 if(s != null)   {
                     Course c = s.getCourse();
                     c.calculateAbsolutes();

                     // Declaration of te different Text Fields

                     TextView tvAss    = (TextView) findViewById(R.id.tvAss);
                     TextView tvProj   = (TextView) findViewById(R.id.tvProj);
                     TextView tvMid_1  = (TextView) findViewById(R.id.tvMid_1);
                     TextView tvMid_2 = (TextView) findViewById(R.id.tvMid_2);
                     TextView tvFinal  = (TextView) findViewById(R.id.tvFinal);
                     TextView tvCourseName = (TextView) findViewById(R.id.tvCourseName);
                     tvCourseName.setText(c.getName());

                     findViewById(R.id.btBack).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Intent j = new Intent(getApplicationContext(), SemesterLog.class);

                             // Transferring data to summary activity
                             j.putExtra("current", current);
                             j.putExtra("data", data);
                             j.putExtra("studentKey", studentKey);

                             startActivity(j);
                         }
                     });

                     // UPdating the values on gui
                     if(c.getAbsAssignment() != -1.0) {
                         tvAss.setText(String.valueOf(c.getAbsAssignment()) + " / "+String.valueOf(c.getwAssignment()) );

                     }
                     else if(c.getAbsAssignment() == -1.0 && c.getwAssignment() != 0)  {
                         tvAss.setText("-" + " / "+String.valueOf(c.getwAssignment()) );
                     }
                     else  {
                         tvAss.setText("-/-");
                     }

                     if(c.getAbsProject()!= -1.0) {
                         tvProj.setText(String.valueOf(c.getAbsProject()) + " / "+String.valueOf(c.getwProject()));

                     }
                     else if(c.getAbsProject() == -1.0 && c.getwProject() != 0)  {
                         tvProj.setText("-" + " / "+ String.valueOf(c.getwProject()));
                     }
                     else  {
                         tvProj.setText("-/-");
                     }

                     if(c.getAbsMid_1() != -1.0) {
                         tvMid_1.setText(String.valueOf(c.getAbsMid_1()) + " / "+String.valueOf(c.getwMid_1()) );

                     }
                     else if(c.getAbsMid_1() == -1.0 && c.getwMid_1() != 0)  {
                         tvMid_1.setText("-" + " / "+String.valueOf(c.getwMid_1()) );

                     }
                     else  {
                         tvMid_1.setText("-/-");
                     }

                     if(c.getAbsMid_2() != -1.0) {
                         tvMid_2.setText(String.valueOf(c.getAbsMid_2()) + " / "+String.valueOf(c.getwMid_2()) );

                     }
                     else if(c.getAbsMid_2() == -1.0 && c.getwMid_2() != 0)  {
                         tvMid_2.setText("-" + " / "+String.valueOf(c.getwMid_2()) );

                     }
                     else  {
                         tvMid_2.setText("-/-");
                     }

                     if(c.getAbsFinal() != -1.0) {
                         tvFinal.setText(String.valueOf(c.getAbsFinal()) + " / "+String.valueOf(c.getwFinal()) );

                     }
                     else if(c.getAbsFinal() == -1.0 && c.getwFinal() != 0)  {
                         tvFinal.setText("-"+ " / "+String.valueOf(c.getwFinal()) );
                     }
                     else  {
                         tvFinal.setText("-/-");
                     }
                 }
            }
            catch(Exception e)  {
                 Toast.makeText(getApplicationContext(),"Exception caught", Toast.LENGTH_SHORT).show();
            }
       }

    }
    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        Intent past = getIntent();
        Bundle b = past.getExtras();

        String current ;
        HashMap<String,Student> data;
        HashSet<String> studentKey;

        data = (HashMap<String , Student>) b.getSerializable("data");;
        studentKey = (HashSet<String>) b.getSerializable("studentKey");
        current = (b.getString("current"));


        if(tv.getText().toString().equals("Assessments")) {
            Intent i = new Intent(this, Asses.class);
            i.putExtra ("current",current);
            i.putExtra("data",data);
            i.putExtra ("studentKey",studentKey);
            startActivity(i);
        }
        else if(tv.getText().toString().equals("Weightages"))   {
            Intent i = new Intent(this, Weight.class);
            i.putExtra ("current",current);
            i.putExtra("data",data);
            i.putExtra ("studentKey",studentKey);
            startActivity(i);

        }

    }

    private void showMarks(Student s)    {

        // Update the Marks scored by the student



    }
}

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

public class Weight extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        findViewById(R.id.btUpdate).setOnClickListener(this);

        String current = null ;
        HashMap<String,Student> data = null;
        HashSet<String> studentKey;

        TextView etAss     = null;
        TextView etProject = null;
        TextView etMid_1   = null;
        TextView etMid_2   = null;
        TextView etFinal   = null;
        // Loading all te views
        try {
                     etAss     = (TextView) findViewById(R.id.etAss);
                     etProject = (TextView) findViewById(R.id.etProject);
                     etMid_1   = (TextView) findViewById(R.id.etMid_1);
                     etMid_2   = (TextView) findViewById(R.id.etMid_2);
                     etFinal   = (TextView) findViewById(R.id.etFinal);

        }
        catch(Exception e)  {
            Toast.makeText(getApplicationContext(),"View cannot be loaded ",Toast.LENGTH_SHORT).show();
            return;
        }
        // On loading the screen Reload all the past weights
        Intent i = getIntent();
        if(i.hasExtra("current"))   {
            Bundle b = i.getExtras();
            try {
                data = (HashMap<String , Student>) b.getSerializable("data");;
                studentKey = (HashSet<String>) b.getSerializable("studentKey");
                current = (b.getString("current"));
            }
            catch(Exception e)  {
                Toast.makeText(getApplicationContext(),"Data can not be loaded",Toast.LENGTH_SHORT).show();
                return;
            }
            // Loading the data
            if(current != null && data != null)  {
                Course c = data.get(current).getCourse();

                try {
                    etAss.setText(String.valueOf(c.getwAssignment()));
                    etFinal.setText(String.valueOf(c.getwFinal()));
                    etProject.setText(String.valueOf(c.getwProject()));
                    etMid_1.setText(String.valueOf(c.getwMid_1()));
                    etMid_2.setText(String.valueOf(c.getwMid_2()));

                }
                catch(Exception e)  {
                    Toast.makeText(getApplicationContext(),"View cannot be loaded ",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        }

    }

    @Override
    public void onClick(View v) {
        Intent j = new Intent(this, StudentHome.class);

        String current = null;
        HashMap<String, Student> data = null;
        HashSet<String> studentKey =null;

        TextView etAss = null;
        TextView etProject = null;
        TextView etMid_1 = null;
        TextView etMid_2 = null;
        TextView etFinal = null;


        // On loading the screen Reload all the past weights
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
            try {
                etAss     = (TextView) findViewById(R.id.etAss);
                etProject = (TextView) findViewById(R.id.etProject);
                etMid_1   = (TextView) findViewById(R.id.etMid_1);
                etMid_2   = (TextView) findViewById(R.id.etMid_2);
                etFinal   = (TextView) findViewById(R.id.etFinal);

            }
            catch(Exception e)  {
                Toast.makeText(getApplicationContext(),"Data cannot be saved ",Toast.LENGTH_SHORT).show();
            }

            //Check that the value is not greater than hundred and no negative weights
            if(current != null && data != null)  {
                Course c = data.get(current).getCourse();

                try {
                   Double Ass = Double.valueOf(etAss.getText().toString());
                    Double Proj = Double.valueOf(etProject.getText().toString());
                    Double Mid_1 = Double.valueOf(etMid_1.getText().toString());
                    Double Mid_2 = Double.valueOf(etMid_2.getText().toString());
                    Double Final = Double.valueOf(etFinal.getText().toString());

                    if(Ass < 0 || Mid_1 < 0 || Mid_2 < 0 || Proj < 0 || Final < 0)  {
                        Toast.makeText(getApplicationContext(),"Check your input No negative number ",Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if(Ass+Proj+Mid_1+Mid_2+Final > 100)    {
                        findViewById(R.id.tvError).setVisibility(View.VISIBLE);
                        return;
                    }
                    c.setwAssignment(Ass);
                    c.setwFinal(Final);
                    c.setwMid_1(Mid_1);
                    c.setwProject(Proj);
                    c.setwMid_2(Mid_2);

                }
                catch(Exception e)  {
                    Toast.makeText(getApplicationContext(),"Check your input ",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            try{
            j.putExtra ("current",current);
            j.putExtra("data",data);
            j.putExtra ("studentKey",studentKey);
            startActivity(j);
            Toast.makeText(getApplicationContext(), "Updated ", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Problem loading application ",Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }
}

package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.R;
import com.example.ass1.StudentHome;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;

public class getAssesmentAsync extends AsyncTask<Void, Void, Void> {

    WeakReference<Activity> ca;
    String current;
    String currentCourse;
    Student s;
    Course c;
    Boolean er = false;

    public getAssesmentAsync(WeakReference<Activity> ca, String current, String currentCourse) {

        this.ca = ca;
        this.current = current;
        this.currentCourse = currentCourse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Loading all te views
        TextView etGAss1 = (TextView) ca.get().findViewById(R.id.etGAss1);
        TextView etGAss2 = (TextView) ca.get().findViewById(R.id.etGAss2);
        TextView etGAss3 = (TextView) ca.get().findViewById(R.id.etGAss3);
        TextView etGMid_1 = (TextView) ca.get().findViewById(R.id.etGMid_1);
        TextView etGMid_2 = (TextView) ca.get().findViewById(R.id.etGMid_2);
        TextView etGProj = (TextView) ca.get().findViewById(R.id.etGProj);
        TextView etGFinal = (TextView) ca.get().findViewById(R.id.etGFinal);

        TextView etTAss1 = (TextView) ca.get().findViewById(R.id.etTAss1);
        TextView etTAss2 = (TextView) ca.get().findViewById(R.id.etTAss2);
        TextView etTAss3 = (TextView) ca.get().findViewById(R.id.etTAss3);
        TextView etTMid_1 = (TextView) ca.get().findViewById(R.id.etTMid_1);
        TextView etTMid_2 = (TextView) ca.get().findViewById(R.id.etTMid_2);
        TextView etTProj = (TextView) ca.get().findViewById(R.id.etTProj);
        TextView etTFinal = (TextView) ca.get().findViewById(R.id.etTFinal);


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

            new saveAssesmentAsync(ca,c,er).execute();
        }
        catch (Exception e) {
            Toast.makeText(ca.get(), "Invalid Input ", Toast.LENGTH_SHORT).show();
            er = true;
            return;

        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);



    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            RoomDatabase db = AppDatabase.getAppDatabase(ca.get());
            s = ((AppDatabase) db).studentDao().getStudent(current);
            c = ((AppDatabase) db).courseDao().getCourse(currentCourse,current);
            Log.d("Course 0: ",c.getName()+" / "+String.valueOf(c.getaAss1())+" / "+String.valueOf(c.gettAss1()));




        }
        catch   (Exception e)   {

            Toast.makeText(ca.get(),"Assesments can not be loaded", Toast.LENGTH_SHORT);
        }
        return null;
    }
}

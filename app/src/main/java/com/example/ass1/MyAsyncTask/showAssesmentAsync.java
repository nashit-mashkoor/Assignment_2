package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.R;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;

public class showAssesmentAsync extends AsyncTask<Void, Void, Void> {

    WeakReference<Activity> c;
    String current;
    String currentCourse;
    Student s;
    Course cr;

    public showAssesmentAsync(WeakReference<Activity> c, String current, String currentCourse) {
        this.c = c;
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


        TextView etGAss1 = (TextView) c.get().findViewById(R.id.etGAss1);
        TextView etGAss2 = (TextView) c.get().findViewById(R.id.etGAss2);
        TextView etGAss3 = (TextView) c.get().findViewById(R.id.etGAss3);
        TextView etGMid_1 = (TextView) c.get().findViewById(R.id.etGMid_1);
        TextView etGMid_2 = (TextView) c.get().findViewById(R.id.etGMid_2);
        TextView etGProj = (TextView) c.get().findViewById(R.id.etGProj);
        TextView etGFinal = (TextView) c.get().findViewById(R.id.etGFinal);

        TextView etTAss1 = (TextView) c.get().findViewById(R.id.etTAss1);
        TextView etTAss2 = (TextView) c.get().findViewById(R.id.etTAss2);
        TextView etTAss3 = (TextView) c.get().findViewById(R.id.etTAss3);
        TextView etTMid_1 = (TextView) c.get().findViewById(R.id.etTMid_1);
        TextView etTMid_2 = (TextView) c.get().findViewById(R.id.etTMid_2);
        TextView etTProj = (TextView) c.get().findViewById(R.id.etTProj);
        TextView etTFinal = (TextView) c.get().findViewById(R.id.etTFinal);

        // Loading the data
        if (current != null && currentCourse != null) {


            try {
                if (cr.getaAss1() != -1.0) {
                    etGAss1.setText(String.valueOf(cr.getaAss1()));
                }
                if (cr.getaAss2() != -1.0) {
                    etGAss2.setText(String.valueOf(cr.getaAss2()));
                }
                if (cr.getaAss3() != -1.0) {
                    etGAss3.setText(String.valueOf(cr.getaAss3()));
                }
                if (cr.gettAss1() != -1.0) {
                    etTAss1.setText(String.valueOf(cr.gettAss1()));
                }
                if (cr.gettAss2() != -1.0) {
                    etTAss2.setText(String.valueOf(cr.gettAss2()));
                }
                if (cr.gettAss3() != -1.0) {
                    etTAss3.setText(String.valueOf(cr.gettAss3()));
                }

                if (cr.getaProject() != -1.0) {
                    etGProj.setText(String.valueOf(cr.getaProject()));

                }
                if (cr.gettProject() != -1.0) {
                    etTProj.setText(String.valueOf(cr.gettProject()));

                }

                if (cr.getaMid_1() != -1.0) {
                    etGMid_1.setText(String.valueOf(cr.getaMid_1()));
                }
                if (cr.gettMid_1() != -1.0) {
                    etTMid_1.setText(String.valueOf(cr.gettMid_1()));
                }
                if (cr.getaMid_2() != -1.0) {
                    etGMid_2.setText(String.valueOf(cr.getaMid_2()));
                }
                if (cr.gettMid_2() != -1.0) {
                    etTMid_2.setText(String.valueOf(cr.gettMid_2()));
                }

                if (cr.getaProject() != -1.0) {
                    etGProj.setText(String.valueOf(cr.getaProject()));

                }
                if (cr.gettProject() != -1.0) {
                    etTProj.setText(String.valueOf(cr.gettProject()));

                }

                if (cr.getaFinal() != -1.0) {
                    etGFinal.setText(String.valueOf(cr.getaFinal()));

                }
                if (cr.gettFinal() != -1.0) {
                    etTFinal.setText(String.valueOf(cr.gettFinal()));

                }

            } catch (Exception e) {
                Toast.makeText(c.get(), "View cannot be loaded ", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            RoomDatabase db = AppDatabase.getAppDatabase(c.get());
            s = ((AppDatabase) db).studentDao().getStudent(current);
            cr = ((AppDatabase) db).courseDao().getCourse(currentCourse,current);
        }
        catch   (Exception e)   {

            Toast.makeText(c.get(),"Assesments can not be loaded", Toast.LENGTH_SHORT);
        }
        return null;
    }
}

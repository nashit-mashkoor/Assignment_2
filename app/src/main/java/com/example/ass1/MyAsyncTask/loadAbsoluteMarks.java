package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.Interfaces.loadStudentHome;
import com.example.ass1.R;
import com.example.ass1.SemesterLog;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;

public class loadAbsoluteMarks extends AsyncTask<Void , Void, Void> {

    WeakReference<Activity> c;
    String current;
    String currentCourse;
    Student s;
    Course course;


    public loadAbsoluteMarks(Activity c, String current, String currentCourse) {
        this.c =  new WeakReference<>(c);
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
        // Updating the absolute marks

        try  {
            if(current != null)   {

                ((TextView)c.get().findViewById(R.id.tvName)).setText(s.getName());
                ((TextView)c.get().findViewById(R.id.tvRoll)).setText(s.getBatch());
                ((TextView)c.get().findViewById(R.id.tvBatch)).setText(s.getRollNum());
                course.calculateAbsolutes();

                // Declaration of te different Text Fields

                TextView tvAss    = (TextView) c.get().findViewById(R.id.tvAss);
                TextView tvProj   = (TextView) c.get().findViewById(R.id.tvProj);
                TextView tvMid_1  = (TextView) c.get().findViewById(R.id.tvMid_1);
                TextView tvMid_2 = (TextView) c.get().findViewById(R.id.tvMid_2);
                TextView tvFinal  = (TextView) c.get().findViewById(R.id.tvFinal);
                TextView tvCourseName = (TextView) c.get().findViewById(R.id.tvCourseName);
                tvCourseName.setText(course.getName());

                c.get().findViewById(R.id.btBack).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent j = new Intent(c.get(), SemesterLog.class);

                        // Transferring data to summary activity
                        j.putExtra("current", current);
                        j.putExtra("currentCourse",currentCourse);

                        c.get().startActivity(j);
                    }
                });

                // UPdating the values on gui
                if(course.getAbsAssignment() != -1.0) {
                    tvAss.setText(String.valueOf(course.getAbsAssignment()) + " / "+String.valueOf(course.getwAssignment()) );

                }
                else if(course.getAbsAssignment() == -1.0 && course.getwAssignment() != 0)  {
                    tvAss.setText("-" + " / "+String.valueOf(course.getwAssignment()) );
                }
                else  {
                    tvAss.setText("-/-");
                }

                if(course.getAbsProject()!= -1.0) {
                    tvProj.setText(String.valueOf(course.getAbsProject()) + " / "+String.valueOf(course.getwProject()));

                }
                else if(course.getAbsProject() == -1.0 && course.getwProject() != 0)  {
                    tvProj.setText("-" + " / "+ String.valueOf(course.getwProject()));
                }
                else  {
                    tvProj.setText("-/-");
                }

                if(course.getAbsMid_1() != -1.0) {
                    tvMid_1.setText(String.valueOf(course.getAbsMid_1()) + " / "+String.valueOf(course.getwMid_1()) );

                }
                else if(course.getAbsMid_1() == -1.0 && course.getwMid_1() != 0)  {
                    tvMid_1.setText("-" + " / "+String.valueOf(course.getwMid_1()) );

                }
                else  {
                    tvMid_1.setText("-/-");
                }

                if(course.getAbsMid_2() != -1.0) {
                    tvMid_2.setText(String.valueOf(course.getAbsMid_2()) + " / "+String.valueOf(course.getwMid_2()) );

                }
                else if(course.getAbsMid_2() == -1.0 && course.getwMid_2() != 0)  {
                    tvMid_2.setText("-" + " / "+String.valueOf(course.getwMid_2()) );

                }
                else  {
                    tvMid_2.setText("-/-");
                }

                if(course.getAbsFinal() != -1.0) {
                    tvFinal.setText(String.valueOf(course.getAbsFinal()) + " / "+String.valueOf(course.getwFinal()) );

                }
                else if(course.getAbsFinal() == -1.0 && course.getwFinal() != 0)  {
                    tvFinal.setText("-"+ " / "+String.valueOf(course.getwFinal()) );
                }
                else  {
                    tvFinal.setText("-/-");
                }
            }
        }
        catch(Exception e)  {
            Toast.makeText(c.get(),"Exception caught", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            RoomDatabase db = AppDatabase.getAppDatabase(c.get());
             s = ((AppDatabase) db).studentDao().getStudent(current);
             course = ((AppDatabase) db).courseDao().getCourse(currentCourse,current);
        }
        catch (Exception e) {
            Toast.makeText(c.get(),"Student not found",Toast.LENGTH_SHORT);
        }
        return null;
    }
}

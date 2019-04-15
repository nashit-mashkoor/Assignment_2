package com.example.ass1.MyAsyncTask;

import android.app.Activity;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ass1.R;
import com.example.ass1.data.AppDatabase;
import com.example.ass1.data.Course;
import com.example.ass1.data.Student;

import java.lang.ref.WeakReference;


public class getWeightAsync extends AsyncTask<Void, Void, Void> {

    WeakReference<Activity> c;
    String current;
    String currentCourse;
    Student s;
    Course cr;

    public getWeightAsync(WeakReference<Activity> c, String current, String currentCourse) {
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
        TextView etAss = null;
        TextView etProject = null;
        TextView etMid_1 = null;
        TextView etMid_2 = null;
        TextView etFinal = null;

        // Loading all te views
        try {
            etAss = (TextView) c.get().findViewById(R.id.etAss);
            etProject = (TextView) c.get().findViewById(R.id.etProject);
            etMid_1 = (TextView) c.get().findViewById(R.id.etMid_1);
            etMid_2 = (TextView) c.get().findViewById(R.id.etMid_2);
            etFinal = (TextView) c.get().findViewById(R.id.etFinal);

        } catch (Exception e) {
            Toast.makeText(c.get(), "Data cannot be saved ", Toast.LENGTH_SHORT).show();
        }
        //Check that the value is not greater than hundred and no negative weights
        try {
            Double Ass = Double.valueOf(etAss.getText().toString());
            Double Proj = Double.valueOf(etProject.getText().toString());
            Double Mid_1 = Double.valueOf(etMid_1.getText().toString());
            Double Mid_2 = Double.valueOf(etMid_2.getText().toString());
            Double Final = Double.valueOf(etFinal.getText().toString());

            if (Ass < 0 || Mid_1 < 0 || Mid_2 < 0 || Proj < 0 || Final < 0) {
                Toast.makeText(c.get(), "Check your input No negative number ", Toast.LENGTH_SHORT).show();
                return;

            }
            if (Ass + Proj + Mid_1 + Mid_2 + Final > 100) {
                c.get().findViewById(R.id.tvError).setVisibility(View.VISIBLE);
                return;
            }
            cr.setwAssignment(Ass);
            cr.setwFinal(Final);
            cr.setwMid_1(Mid_1);
            cr.setwProject(Proj);
            cr.setwMid_2(Mid_2);

            // c and cr  as variable name are messed up
            new saveWeightsAsync(c,cr).execute();
        } catch (Exception e) {
            Toast.makeText(c.get(), "Check your input ", Toast.LENGTH_SHORT).show();
            return;
        }
        return;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            RoomDatabase db = AppDatabase.getAppDatabase(c.get());
            s = ((AppDatabase) db).studentDao().getStudent(current);
            cr = ((AppDatabase) db).courseDao().getCourse(currentCourse,current);
            Log.d("CourseWget: ",cr.getName()+" / "+String.valueOf(cr.getwAssignment())+" / "+String.valueOf(cr.getwMid_1()));




        }
        catch   (Exception e)   {

            Toast.makeText(c.get(),"Weights can not be loaded", Toast.LENGTH_SHORT);
        }

        return null;
    }
}

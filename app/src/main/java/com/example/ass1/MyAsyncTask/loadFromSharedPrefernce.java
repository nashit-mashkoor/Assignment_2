package com.example.ass1.MyAsyncTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;

import java.lang.ref.WeakReference;

public class loadFromSharedPrefernce extends AsyncTask<Void,Void,Void> {


    WeakReference<EditText> etUser;
    WeakReference<EditText> etPass;
    WeakReference<Context> context;

    public loadFromSharedPrefernce(WeakReference<EditText> etUser, WeakReference<EditText> etPass, WeakReference<Context> context) {
        this.etUser = etUser;
        this.etPass = etPass;
        this.context = context;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        //Checking  for last Login attempted using shared preference
        SharedPreferences sharedPref = context.get().getSharedPreferences("lastLogin",Context.MODE_PRIVATE);
        String lastEmail = sharedPref.getString("lastEmail",null);
        String lastPassword = sharedPref.getString("lastPassword",null);
        if(lastEmail != null && lastPassword != null)   {

            etUser.get().setText(lastEmail);
            etPass.get().setText(lastPassword);

        }

        return null;
    }
}

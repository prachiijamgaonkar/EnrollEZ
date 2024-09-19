package com.example.admissionexamform.classes;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "College_Admission";
    public static final String Username_Ph = "usernameKey";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void createLoginSession(String phno){
        editor.putString(Username_Ph, phno);
        editor.commit();
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }

    public String isLoggedIn(){
        return sharedpreferences.getString(Username_Ph,"");
    }
}

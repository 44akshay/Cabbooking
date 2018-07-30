package com.example.user.cabbooking;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 12/07/2018.
 */

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "Akshay";
    private static final String KEY_EMP_ID = "keyempid";
    private static final String KEY_EMP_NAME = "keyempname";
    private static final String KEY_PH_NO = "keyphnno";
    private static final String KEY_DESIGNATION = "keydesignation";
    private static final String KEY_JOBBAND = "keyjobband";
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMP_ID, user.getEmp_id());
        editor.putString(KEY_EMP_NAME, user.getEmp_name());
        editor.putString(KEY_PH_NO, user.getPh_no());
        editor.putString(KEY_DESIGNATION, user.getDesignation());
        editor.putString(KEY_JOBBAND,user.getJobband());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_EMP_ID, 0) != 0;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_EMP_ID, null),
                sharedPreferences.getString(KEY_EMP_NAME, null),
                sharedPreferences.getString(KEY_DESIGNATION, null),
                sharedPreferences.getString(KEY_PH_NO, null)
                ,sharedPreferences.getString(KEY_JOBBAND,null)
        );
    }
}
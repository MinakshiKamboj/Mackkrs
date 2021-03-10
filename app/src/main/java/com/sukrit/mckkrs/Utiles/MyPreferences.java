package com.sukrit.mckkrs.Utiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPreferences {

    private static MyPreferences preferences = null;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor editor;
    private String flag = "flag";
    private String isLogedIn = "isLogedIn";
    private String milkId = "id";
    private String marn = "marn";
    private String email = "email";




    public static MyPreferences getActiveInstance(Context context) {
        if (preferences == null) {
            preferences = new MyPreferences(context);
        }
        return preferences;
    }

    public MyPreferences(Context context) {
        setmPreferences(PreferenceManager.getDefaultSharedPreferences(context));
    }
    public SharedPreferences getmPreferences() {
        return mPreferences;
    }

    public void setmPreferences(SharedPreferences mPreferences) {
        this.mPreferences = mPreferences;
    }

    public boolean getIsLoggedIn() {
        return mPreferences.getBoolean(this.isLogedIn, false);
    }
    public void setIsLoggedIn(boolean isLoggedin) {
        editor = mPreferences.edit();
        editor.putBoolean(this.isLogedIn, isLoggedin);
        editor.commit();
    }

    public boolean getflag() {
        return mPreferences.getBoolean(this.flag, false);
    }

    public void setflag(boolean flag) {
        editor = mPreferences.edit();
        editor.putBoolean(this.flag, flag);
        editor.commit();
    }

    public String getMilkId() {
        return mPreferences.getString(this.milkId, "");
    }
    public void setMilkId(String milkId) {
        editor = mPreferences.edit();
        editor.putString(this.milkId, milkId);
        editor.commit();
    }

    public String getMarn() {
        return mPreferences.getString(this.marn, "");
    }
    public void setMarn(String marn) {
        editor = mPreferences.edit();
        editor.putString(this.marn, marn);
        editor.commit();
    }


      public String getEmail() {
        return mPreferences.getString(this.email, "");
    }
    public void setEmail(String email) {
        editor = mPreferences.edit();
        editor.putString(this.email, email);
        editor.commit();
    }


}

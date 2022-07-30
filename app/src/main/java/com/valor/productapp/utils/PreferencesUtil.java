package com.valor.productapp.utils;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtil {


    private static final String SHARED_PREF_NAME = "product";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME="user_name";
    public static final String WELCOME_VIEW_STATUS="welcome_status";


    public static void setValueString(Context context, String key, String value) {

        if (context == null) return;
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public static String getValueString(Context context, String key) {

        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, Activity.MODE_PRIVATE);
        return preferences.getString(key, null);


    }


    public static void setValueSInt(Context context, String key, int value) {

        if (context == null) return;
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();

    }

    public static int getValueInt(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, Activity.MODE_PRIVATE);
        return preferences.getInt(key, 0);

    }

    public static void remove(Context context, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();


    }

    public static void clearAll(Context context) {

        SharedPreferences removeRewardID = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = removeRewardID.edit();
        editor.clear();
        editor.commit();


    }
}

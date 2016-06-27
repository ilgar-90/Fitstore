package com.actonica.fitstore.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ilgar on 26.06.2016.
 */
public class SharedPrefsHelper {
    Context context;
    private static String PREFS_NAME = "JuiceFitPreferences";
    private static final String PREF_TOKEN = "saved_user_token";

    public SharedPrefsHelper(Context _ctx){
        context = _ctx;
    }

    public void saveToken(String token) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_TOKEN, token);
        editor.commit();
    }

    public String getSavedToken(){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedToken = sharedPref.getString(PREF_TOKEN, "");
        return savedToken;
    }
}

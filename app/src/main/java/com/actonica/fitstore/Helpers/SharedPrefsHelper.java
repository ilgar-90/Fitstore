package com.actonica.fitstore.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.actonica.fitstore.Models.Program;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ilgar on 26.06.2016.
 */
public class SharedPrefsHelper {
    private static final String PREFS_NAME = "JuiceFitPreferences";
    private static final String PREF_TOKEN = "saved_user_token";
    private static final String PREF_SAVED_PROGRAMS = "saved_user_programs";


    public static void saveToken(String token, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_TOKEN, token);
        editor.commit();
    }

    public static String getSavedToken(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedToken = sharedPref.getString(PREF_TOKEN, "");
        return savedToken;
    }

    public static void saveProgram(Program program, Context context){
        List<Program> savedPrograms;
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Program>>(){}.getType();
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedProgramsStr = sharedPref.getString(PREF_SAVED_PROGRAMS, null);
        if (savedProgramsStr == null){
            savedPrograms = new ArrayList<>();
        }
        else {
            savedPrograms = gson.fromJson(savedProgramsStr, listOfTestObject);
        }
        savedPrograms.add(program);
        String resultToSave = gson.toJson(savedPrograms, listOfTestObject);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_SAVED_PROGRAMS, resultToSave);
        editor.commit();
    }

    public static List<Program> getSavedPrograms(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedProgramsStr = sharedPref.getString(PREF_SAVED_PROGRAMS, null);
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Program>>(){}.getType();
        return gson.fromJson(savedProgramsStr, listOfTestObject);
    }

    public static void removeSavedProgram(int programId, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedProgramsStr = sharedPref.getString(PREF_SAVED_PROGRAMS, null);
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Program>>(){}.getType();
        List<Program> savedPrograms = gson.fromJson(savedProgramsStr, listOfTestObject);
        for (Iterator<Program> iter = savedPrograms.listIterator(); iter.hasNext(); ) {
            Program prog = iter.next();
            if (prog.getId() == programId) {
                iter.remove();
            }
        }

        String resultToSave = gson.toJson(savedPrograms, listOfTestObject);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_SAVED_PROGRAMS, resultToSave);
        editor.commit();
    }
}

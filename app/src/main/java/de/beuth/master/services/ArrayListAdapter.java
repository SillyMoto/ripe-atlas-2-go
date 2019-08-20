package de.beuth.master.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import de.beuth.master.classes.ApiKey;
import de.beuth.master.classes.Credit;
import de.beuth.master.classes.Measurement;

public class ArrayListAdapter {
    /**
     *     Save and get ArrayList in SharedPreference
     */

    public static void saveApiKeyArrayList(ArrayList<ApiKey> list, String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public static void saveMsmArrayList(ArrayList<Measurement> list, String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public static void saveCredit(Credit credit, String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(credit);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }


    public static ArrayList<ApiKey> getApiKeyArrayList(String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<ApiKey>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static ArrayList<Measurement> getMsmArrayList(String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Measurement>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static Credit getCredit(String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<Credit>() {}.getType();
        return gson.fromJson(json, type);
    }
}
/*
 * Copyright (C) 2019 SillyMoto authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * Save data to SharedPreference
 * and get saved data of SharedPreferences
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class ArrayListAdapter {

    /**
     * saves an ArrayList consist of API Keys.
     * @param list ArrayList consist of API Keys, which will be saved
     * @param key a String ID to save the data
     * @param context the actual context
     */
    public static void saveApiKeyArrayList(ArrayList<ApiKey> list, String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    /**
     * saves an ArrayList consist of measurements.
     * @param list ArrayList consist of measurements, which will be saved
     * @param key a String ID to save the data
     * @param context the actual context
     */
    public static void saveMsmArrayList(ArrayList<Measurement> list, String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    /**
     * saves a credit.
     * @param credit a credit, which will be saved
     * @param key a String ID to save the data
     * @param context the actual context
     */
    public static void saveCredit(Credit credit, String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(credit);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    /**
     * get the saved ArrayList consist of api keys.
     * @param key a String ID of the saved data
     * @param context the actual context
     * @return a ArrayList of the saved keys
     */
    public static ArrayList<ApiKey> getApiKeyArrayList(String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<ApiKey>>() {}.getType();
        return gson.fromJson(json, type);
    }

    /**
     * get the saved ArrayList consist of measurements.
     * @param key a String ID of the saved data
     * @param context the actual context
     * @return a ArrayList of the saved measurements
     */
    public static ArrayList<Measurement> getMsmArrayList(String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Measurement>>() {}.getType();
        return gson.fromJson(json, type);
    }

    /**
     * get the saved credit.
     * @param key a String ID of the saved data
     * @param context the actual context
     * @return the saved credit
     */
    public static Credit getCredit(String key, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<Credit>() {}.getType();
        return gson.fromJson(json, type);
    }
}
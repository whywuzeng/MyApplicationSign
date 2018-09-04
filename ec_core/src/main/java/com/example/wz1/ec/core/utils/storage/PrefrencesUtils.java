package com.example.wz1.ec.core.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.wz1.ec.core.app.ECApp;

/**
 * Created by Administrator on 2018-09-04.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.storage
 */

public class PrefrencesUtils  {

    public static final SharedPreferences SHARED_PREFERENCES= PreferenceManager.getDefaultSharedPreferences(ECApp.getApplicationContext());

    public static final String PREFERENCES_PROFILE="porfile";

    public static void setPreferenceProfile(String values){
        SHARED_PREFERENCES.edit().putString(PREFERENCES_PROFILE,values).apply();
    }

    public static String getPreferencesProfile()
    {
        return SHARED_PREFERENCES.getString(PREFERENCES_PROFILE,null);
    }
    public static void removePreference(String key){
        SHARED_PREFERENCES.edit().remove(key).apply();
    }

    public static void clearPreference(){
        SHARED_PREFERENCES.edit().clear().apply();
    }

    public static void setPreferences(String key,String values)
    {
        SHARED_PREFERENCES.edit().putString(key,values).apply();
    }

    public static String getPreferences(String key)
    {
        return SHARED_PREFERENCES.getString(key,null);
    }

    public static void setAppFlag(String key,boolean values)
    {
        SHARED_PREFERENCES.edit().putBoolean(key,values).apply();
    }

    public static boolean getAppFlag(String key)
    {
        return SHARED_PREFERENCES.getBoolean(key,false);
    }
}

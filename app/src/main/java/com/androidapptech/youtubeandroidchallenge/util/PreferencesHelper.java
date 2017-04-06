package com.androidapptech.youtubeandroidchallenge.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidapptech.youtubeandroidchallenge.Config;

import static android.R.attr.value;

/**
 * Created by Benjamin on 1/10/2017.
 */

public class PreferencesHelper {

    /**
     * Set a string shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceString(Context context, String key, String value){
        SharedPreferences settings = context.getSharedPreferences(Config.SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Set a integer shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceInt(Context context, String key, int value){
        SharedPreferences settings = context.getSharedPreferences(Config.SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Set a Boolean shared preference
     * @param key - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceBoolean(Context context, String key, boolean value){
        SharedPreferences settings = context.getSharedPreferences(Config.SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a string shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static String getSharedPreferenceString(Context context, String key, String defValue){
        SharedPreferences settings = context.getSharedPreferences(Config.SHARED_KEY, Context.MODE_PRIVATE);
        return settings.getString(key, defValue);
    }

    /**
     * Get a integer shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static int getSharedPreferenceInt(Context context, String key, int defValue){
        SharedPreferences settings = context.getSharedPreferences(Config.SHARED_KEY, Context.MODE_PRIVATE);
        return settings.getInt(key, defValue);
    }

    /**
     * Get a boolean shared preference
     * @param key - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue){
        SharedPreferences settings = context.getSharedPreferences(Config.SHARED_KEY, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defValue);
    }

    public static void removeKeyFromSharedPreference(Context context, String key){
        SharedPreferences settings = context.getSharedPreferences(Config.SHARED_KEY, Context.MODE_PRIVATE);
        settings.edit().remove(key).apply();
    }

    public static boolean isSharedPreferenceContainKey(Context context, String key){
        SharedPreferences settings = context.getSharedPreferences(Config.SHARED_KEY, Context.MODE_PRIVATE);
        if(settings.contains(key)){
            return true;
        }else{
            return false;
        }
    }
}

package com.runnzzerfitness.data;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.google.gson.Gson;


public class ProfileSettings {

    private static ProfileSettings profileSettings;
    private SharedPreferences sharedPreferences;

    private static final String name = "profile_settings";

    //data keys.
    private static final String _name_key = "name_key";
    private static final String _age_key = "age_key";
    private static final String _weight_key = "weight_key";//grams.
    private static final String _height_key = "height_key";//millimeters.
    private static final String _gender_key = "gender_key";//true -> male : false -> female.

    //default values.
    private static final String _name_value = "User Name";
    private static final int _age_value = 0;
    private static final int _weight_value = 0;
    private static final int _height_value = 0;
    private static final boolean _gender_value = true;



    private ProfileSettings(Context context) {
        this.sharedPreferences = context.getSharedPreferences(name , Context.MODE_PRIVATE);
    }



    public static ProfileSettings getInstance (Context context){
        if (profileSettings == null){
            profileSettings = new ProfileSettings(context);
        }
        return profileSettings;
    }



    public void editProfile (ProfileDataWrapper profileDataWrapper){
        sharedPreferences.edit()
                .putString(_name_key , profileDataWrapper.name)
                .putInt(_age_key , profileDataWrapper.age)
                .putInt(_weight_key , profileDataWrapper.weight)
                .putInt(_height_key , profileDataWrapper.height)
                .putBoolean(_gender_key , profileDataWrapper.gender)
                .apply();
    }



    public ProfileDataWrapper getProfile (){
        ProfileDataWrapper profileDataWrapper = new ProfileDataWrapper();
        //put data to wrapper.
        profileDataWrapper.name = sharedPreferences.getString(_name_key , _name_value);
        profileDataWrapper.age = sharedPreferences.getInt(_age_key , _age_value);
        profileDataWrapper.weight = sharedPreferences.getInt(_weight_key , _weight_value);
        profileDataWrapper.height = sharedPreferences.getInt(_height_key , _height_value);
        profileDataWrapper.gender = sharedPreferences.getBoolean(_gender_key , _gender_value);

        return profileDataWrapper;
    }


}

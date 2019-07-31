package com.runnzzerfitness.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.runnzzerfitness.fragments.dataPanels.DistancePanel;
import com.runnzzerfitness.fragments.dataPanels.DurationPanel;
import com.runnzzerfitness.fragments.dataPanels.ElevationPanel;
import com.runnzzerfitness.fragments.dataPanels.PacePanle;
import com.runnzzerfitness.fragments.dataPanels.SpeedPanel;


public class SettingsManager {

    private static SharedPreferences sharedPreferences;
    private static SettingsManager settingsManager = null;

    //Name for shared preferences.
    private static final String NAME = "settings";

    //Keys.
    private static final String AUDIO_KEY = "audio";
    private static final String UNITE_KEY = "distanceLiveData";



    //Default Values.
    private static boolean AUDIO_DEFAULT_VALUE = true;
    private static boolean UNITE_DEFAULT_VALUE = true;//true -> metrical false -> imperial .



    //com.runnzzerfitness.fragments reference.
    private Fragment[] fragments = {
            new DurationPanel(),//0
            new DistancePanel(),//1
            new SpeedPanel(),//2
            new PacePanle(),//3
            new ElevationPanel()//4
    };






    private SettingsManager() {
        //require empty private constructor to fit singleton pattern.
    }



    public static SettingsManager getSettingsManager (Context context){
        if (settingsManager == null){
            settingsManager = new SettingsManager();
            sharedPreferences = context.getSharedPreferences(NAME , Context.MODE_PRIVATE);
        }
        return settingsManager;
    }



    public boolean getAudioState (){
        return sharedPreferences.getBoolean(AUDIO_KEY , AUDIO_DEFAULT_VALUE);
    }



    public boolean changeAudioState (){
         if (getAudioState()){
             sharedPreferences.edit().putBoolean(AUDIO_KEY , false).apply();
         }else {
             sharedPreferences.edit().putBoolean(AUDIO_KEY , AUDIO_DEFAULT_VALUE).apply();
         }
         return getAudioState();
    }



    public boolean getUnite(){
        return sharedPreferences.getBoolean(UNITE_KEY, UNITE_DEFAULT_VALUE);
    }



    public boolean changeDistanceUnite (){
        if (getUnite()){
            sharedPreferences.edit().putBoolean(UNITE_KEY, false).apply();
        }else {
            sharedPreferences.edit().putBoolean(UNITE_KEY, UNITE_DEFAULT_VALUE).apply();
        }

        return getUnite();
    }





    private static final String FIRST_PANEL = "first_panel";
    private static final String SECOND_PANEL = "second_panel";
    private static final String THIRD_PANEL = "third_panel";
    private static final String FOURTH_PANEL = "fourth_panel";

    //panels default values.
    private static final int FIRST_PANEL_VALUE = 0;
    private static final int SECOND_PANEL_VALUE = 1;
    private static final int THIRD_PANEL_VALUE = 2;
    private static final int FOURTH_PANEL_VALUE = 4;

    public Fragment getFirstPanel(){
        return fragments[sharedPreferences.getInt(FIRST_PANEL , FIRST_PANEL_VALUE)];
    }

    public Fragment getSecondPanel(){
        return fragments[sharedPreferences.getInt(SECOND_PANEL , SECOND_PANEL_VALUE)];
    }

    public Fragment getThirdPanel(){
        return fragments[sharedPreferences.getInt(THIRD_PANEL , THIRD_PANEL_VALUE)];
    }

    public Fragment getFourthPanel(){
        return fragments[sharedPreferences.getInt(FOURTH_PANEL , FOURTH_PANEL_VALUE)];
    }


}

package com.runnzzerfitness.viewmodules;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.runnzzerfitness.data.SettingsManager;

public class MainActivityViewModule extends AndroidViewModel {


    private SettingsManager settingsManager;


    public MutableLiveData<Boolean> audioState = new MutableLiveData<Boolean>();

    public boolean distanceUnite;


    public MainActivityViewModule(@NonNull Application application) {
        super(application);
        settingsManager = SettingsManager.getSettingsManager(getApplication().getApplicationContext());

        audioState.setValue(settingsManager.getAudioState());
        distanceUnite = settingsManager.getUnite();
    }




    public void changeAudioState (){
        audioState.setValue(settingsManager.changeAudioState());
    }



    public void changeDistance (){
        distanceUnite = settingsManager.changeDistanceUnite();
    }



}

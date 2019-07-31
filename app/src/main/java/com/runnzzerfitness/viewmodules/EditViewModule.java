package com.runnzzerfitness.viewmodules;


import android.app.Application;

import com.runnzzerfitness.data.SessionData;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;



public abstract class EditViewModule extends AndroidViewModel {

    public EditViewModule(@NonNull Application application) {
        super(application);
    }

    @NonNull
    public abstract void editCalories (int cal);

    @NonNull
    public abstract void editDistance (double meters);

    @NonNull
    public abstract void editDuration (long ms);

    @NonNull
    public abstract void editTitle (String title);

    @NonNull
    public abstract SessionData getData ();

    @NonNull
    public abstract void saveData ();

}

package com.runnzzerfitness.viewmodules;

import android.app.Application;
import android.content.Context;

import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.SessionData;
import com.runnzzerfitness.utils.Converter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class AddSessionViewModule extends EditViewModule {

    public MutableLiveData<SessionData> sessionData = new MutableLiveData<>();
    private Context context;


    public AddSessionViewModule(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();

        //construct default session data wrapper to read and write from.
        SessionData data = new SessionData();
        data.title = Converter.getDateFormat();//current date.
        data.distance = 0;
        data.duration = 0;

        //insert default value.
        sessionData.setValue(data);
    }


    @Override
    public void editCalories(int cal) {

    }


    @Override
    public void editDistance(double meters) {
        getData().distance = meters;
        refreshLiveDate();
    }


    @Override
    public void editDuration(long ms) {
        getData().duration = ms;
        refreshLiveDate();
    }


    @Override
    public void editTitle(String title) {
        getData().title = title;
        refreshLiveDate();
    }


    @Override
    public SessionData getData() {
        return sessionData.getValue();
    }


    @Override
    public void saveData() {
        //insert data to the database.
        DBManager.getInstance(context).saveSession(sessionData.getValue());
    }


    private void refreshLiveDate (){
        sessionData.setValue(sessionData.getValue());
    }
}

package com.runnzzerfitness.viewmodules;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.SessionData;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


public class EditSessionViewModule extends EditViewModule {

    public MutableLiveData<SessionData> sessionData = new MutableLiveData<>();
    private int id;
    public Context context;


    public EditSessionViewModule(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }


    public void setId (int id){
        sessionData.setValue(DBManager.getInstance(context).getSessionById(this.id = id));
    }



    @Override
    public void editCalories(int cal) {
        //TODO edit cal
    }


    @Override
    public void editDistance(double meters) {
        sessionData.getValue().distance = meters;
        refreshLiveData();
    }


    @Override
    public void editDuration(long ms) {
        sessionData.getValue().duration = ms;
        refreshLiveData();
    }


    @Override
    public void editTitle(String title) {
        sessionData.getValue().title = title;
        refreshLiveData();
    }


    @NonNull
    @Override
    public SessionData getData() {
        return sessionData.getValue();
    }



    @Override
    public void saveData() {
        DBManager.getInstance(context).updateSession(id , sessionData.getValue());
    }



    private void refreshLiveData (){
        sessionData.setValue(sessionData.getValue());
    }


}

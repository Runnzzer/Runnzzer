package com.runnzzerfitness.utils;


import androidx.appcompat.app.AppCompatActivity;
import com.runnzzerfitness.R;
import com.runnzzerfitness.fragments.EditProfileFragment;


public class ProfileActivityBinder {

    private AppCompatActivity appCompatActivity;



    public ProfileActivityBinder(AppCompatActivity appCompatActivity ) {
        this.appCompatActivity = appCompatActivity;
    }



    public void showEditFragment (){
        appCompatActivity
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.profile_activity_root , new EditProfileFragment())
                .addToBackStack(null)
                .commit();
    }



    public void back (){
        appCompatActivity.onBackPressed();
    }
}

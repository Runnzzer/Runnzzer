package com.runnzzerfitness.utils;


import android.content.Context;
import android.content.Intent;
import com.runnzzerfitness.ui.activities.MainActivity;
import com.runnzzerfitness.ui.activities.PreviewRecentSessionActivity;



public class PreviewRecentSessionBinder {

    private PreviewRecentSessionActivity activity;
    private Context context;


    public PreviewRecentSessionBinder(PreviewRecentSessionActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }



    public void openMainActivity (){
        activity.finish();
        context.startActivity(new Intent(context , MainActivity.class));
    }

}

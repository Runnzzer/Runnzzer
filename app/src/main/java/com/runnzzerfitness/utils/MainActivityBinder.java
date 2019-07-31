package com.runnzzerfitness.utils;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.runnzzerfitness.R;
import com.runnzzerfitness.fragments.dialogs.DialogBuilder;
import com.runnzzerfitness.fragments.dialogs.DialogListener;
import com.runnzzerfitness.fragments.dialogs.SelectAddSession;
import com.runnzzerfitness.fragments.dialogs.SelectMapStyle;
import com.runnzzerfitness.ui.activities.AddSessionActivity;
import com.runnzzerfitness.ui.activities.MainActivity;
import com.runnzzerfitness.ui.activities.ProfileActivity;


public class MainActivityBinder {

    public static final int LOCATION_REQUEST_CODE = 0;

    private Context context;
    private MainActivity mainActivity;

    public MainActivityBinder(Context context, MainActivity mainActivity) {
        this.context = context;
        this.mainActivity = mainActivity;
    }



    public void showChoseDialog (){
        DialogBuilder dialogBuilder = new DialogBuilder(mainActivity, new SelectAddSession(), new DialogListener() {
            @Override
            public void getRespond(double val) {
                // 0 -> record activity 1 -> manually
                if (val == 0){
                    openTrackingActivity();
                }else{
                    openAddSessionActivity();
                }
            }
        });

        dialogBuilder.show(R.id.main_activity_root);
    }


    private void openTrackingActivity (){
        //TODO recheck condition checking process.
        if (Build.VERSION.SDK_INT >= 23){
            //permission not granted.
            mainActivity.requestPermissions(
                        new String[]{
                                Manifest.permission.ACCESS_COARSE_LOCATION ,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        MainActivityBinder.LOCATION_REQUEST_CODE //request code.
            );
        }else {
            mainActivity.openTrackingActivity();
        }
    }


    private void openAddSessionActivity(){
        Intent i = new Intent(context , AddSessionActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


    public void openProfileActivity(){
        Intent i = new Intent(context , ProfileActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


    public void showMapStyleDialog (){
        DialogBuilder dialogBuilder = new DialogBuilder(mainActivity, new SelectMapStyle(), new DialogListener() {
            @Override
            public void getRespond(double val) {
                //list index

            }
        });

        dialogBuilder.show(R.id.main_activity_root);
    }

}

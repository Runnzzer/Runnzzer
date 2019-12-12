package com.runnzzerfitness.utils;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import com.google.android.gms.maps.GoogleMap;
import com.runnzzerfitness.R;
import com.runnzzerfitness.data.SettingsManager;
import com.runnzzerfitness.fragments.dialogs.DialogBuilder;
import com.runnzzerfitness.fragments.dialogs.DialogListener;
import com.runnzzerfitness.fragments.dialogs.SelectAddSession;
import com.runnzzerfitness.fragments.dialogs.SelectMapStyle;

import com.runnzzerfitness.tracking.MainService;
import com.runnzzerfitness.tracking.TrackingFlags;
import com.runnzzerfitness.ui.activities.AddSessionActivity;
import com.runnzzerfitness.ui.activities.LiveTrackingActivity;
import com.runnzzerfitness.ui.activities.MainActivity;
import com.runnzzerfitness.ui.activities.ProfileActivity;


public class MainActivityBinder {

    public static final int LOCATION_REQUEST_CODE = 0;

    private Context context;
    private MainActivity activity;


    public MainActivityBinder(Context context, MainActivity activity) {
        this.context = context;
        this.activity = activity;
    }


    public void showChoseDialog (){
        DialogBuilder dialogBuilder = new DialogBuilder(activity, new SelectAddSession(), new DialogListener() {
            @Override
            public void getRespond(double val) {
                // 0 -> record activity 1 -> manually
                if (val == 0){
                    requestLocationPermission();
                }else{
                    openAddSessionActivity();
                }
            }
        });

        dialogBuilder.show(R.id.drawer_layout);
    }


    public void openTrackingActivity(){
        //permission granted successfully.
        activity.finish();
        Intent intent = new Intent(context, MainService.class);
        intent.putExtra(TrackingFlags.FLAG_KEY , TrackingFlags.START_TRACKING);
        activity.startService(intent);
        activity.startActivity(new Intent(context , LiveTrackingActivity.class));
    }


    private void requestLocationPermission (){
        //TODO recheck condition checking process.
        if (Build.VERSION.SDK_INT >= 23){
            //permission not granted.
            activity.requestPermissions(
                        new String[]{
                                Manifest.permission.ACCESS_COARSE_LOCATION ,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        MainActivityBinder.LOCATION_REQUEST_CODE //request code.
            );
        }else {
            //permission granted successfully.
            openTrackingActivity();
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
        DialogBuilder dialogBuilder = new DialogBuilder(activity, new SelectMapStyle(), new DialogListener() {
            @Override
            public void getRespond(double val) {
                //change map style.
                SettingsManager settingsManager = SettingsManager.getSettingsManager(context);

                switch((int)val){
                    case 0:
                        settingsManager.changeMapStyle(GoogleMap.MAP_TYPE_NORMAL);
                        break;

                    case 1:
                        settingsManager.changeMapStyle(GoogleMap.MAP_TYPE_SATELLITE);
                        break;

                    case 2:
                        settingsManager.changeMapStyle(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                }

            }
        });

        dialogBuilder.show(R.id.drawer_layout);
    }

}

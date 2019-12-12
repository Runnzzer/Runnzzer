package com.runnzzerfitness.tracking;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.runnzzerfitness.R;
import com.runnzzerfitness.ui.activities.LiveTrackingActivity;

import androidx.core.app.NotificationCompat;


public class MainService extends Service {


    //For Api 26 and above.
    private static final String NOTIFICATION_CHANEL_ID = "id";//Notification Channel ID
    public static final int notification_id = 0;//Notification channel ID



    public MainService() {
        //required empty constructor.
    }




    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getExtras() != null){
            //get respond.
            int respond = intent.getExtras().getInt(TrackingFlags.FLAG_KEY);

            if (respond  == TrackingFlags.START_TRACKING  && Tracker.INSTANCE == null){
                Tracker tracker = Tracker.getInstance();
                tracker.start(getBaseContext());
                startForeground();
            }

            else if (respond == TrackingFlags.STOP_TRACKING){
                stopForeground(true);
            }
        }
        return START_STICKY;
    }




    private void startForeground (){
        //Intent of the Map Activity
        Intent notificationIntent = new Intent(this, LiveTrackingActivity.class);

        //For Android Oreo and Higher Versions
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //Pending Intent For the Notification
            PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , notificationIntent , PendingIntent.FLAG_NO_CREATE);

            //setup and create Notification
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANEL_ID , "Notification" , NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Tracking Your Work-out");
            // Register the channel with the system
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);

            //Notification Builder
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANEL_ID)
                    //.setSmallIcon(R.mipmap.running_icon)
                    .setContentTitle("Runnzzer")
                    .setContentText("Tracking Your Work-out")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setSmallIcon(R.drawable.ic_directions_run)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent);
            //show Notification Manager in status bar
            notificationManager.notify( notification_id , mBuilder.build());

        }else {
            //Under Android Oreo Versions
            //Pending Intent and in Here we Gonna use our Intent
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , notificationIntent, 0);
            //Notification Builder
            Notification notification = new Notification.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Runnzzer")
                    .setContentText("Tracking your workout")
                    .setSmallIcon(R.drawable.ic_directions_run)
                    .build();
            //show notification bar in foreground
            startForeground(1 , notification);
        }
    }


}

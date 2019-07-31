package com.runnzzerfitness.tracking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.runnzzerfitness.utils.Converter;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.SessionData;
import com.runnzzerfitness.ui.activities.PreviewRecentSessionActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;


public class Tracker implements LocationListener,Runnable{

    public static Tracker INSTANCE = null;

    //duration counters.
    private long bt,stwp;//bt -> base time // stwp -> system time when paused.

    private double totalDistance = 0;
    private double maxElevation = 0;
    private double maxSpeed = 0;

    private static final int LOCATION_UPDATES_INTERVAL = 5000;
    private Location oldLocation;
    private LocationManager locationManager;
    private LocationUpdatesQueue locationUpdatesQueue = new LocationUpdatesQueue();
    private Thread locationUpdatesProcessor;
    public TrackGenerator trackGenerator;

    //live data.
    public MutableLiveData<Boolean> state = new MutableLiveData<>();
    public MutableLiveData<Double> distanceLiveData = new MutableLiveData<>();
    public MutableLiveData<Float> speedLiveData = new MutableLiveData<>();
    public MutableLiveData<Double> elevationLiveData = new MutableLiveData<>();
    public MutableLiveData<LatLng> currentLocation = new MutableLiveData<>();




    private Tracker() {
        //require empty private constructor to fit singleton pattern.
    }




    public static Tracker getInstance (){
        if (INSTANCE == null){
            INSTANCE = new Tracker();
        }
        return INSTANCE;
    }




    @SuppressLint("MissingPermission")
    public void start(Context context){
        //initialize time counters.
        bt = SystemClock.elapsedRealtime();

        //set default values for live data.
        state.setValue(true);
        distanceLiveData.setValue(totalDistance);
        speedLiveData.setValue(0.0f);
        elevationLiveData.setValue(0.0);

        //start locationUpdatesProcessor thread.
        locationUpdatesProcessor = new Thread(this);
        locationUpdatesProcessor.start();

        //location manager.
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //start requesting updates form location services (Gps or Network).
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    LOCATION_UPDATES_INTERVAL,
                    0 ,
                    this);

        }else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER ,
                    LOCATION_UPDATES_INTERVAL,
                    0 ,
                    this);
        }

        trackGenerator = new TrackGenerator(context ,this);
    }




    public void pause(){
        stwp = SystemClock.elapsedRealtime();
        state.setValue(false);

        oldLocation = null;
        Log.i("TrackerService" , "Tracker onPause");
    }




    public void resume(){
        //set new base time for the counter
        bt = SystemClock.elapsedRealtime() - (stwp - bt);
        state.setValue(true);

        Log.i("TrackerService" , "Tracker onResume");
    }




    public void showCurrentLocation(){
        if (currentLocation.getValue() != null){
            currentLocation.setValue(currentLocation.getValue());
        }
    }




    public void stop(Context context , FragmentActivity activity){
        //stop location updates.
        locationManager.removeUpdates(this);

        //block processing thread.
        locationUpdatesProcessor.interrupt();

        //insert all tracking data to the data wrapper object.
        SessionData sessionData = new SessionData();
        sessionData.title = Converter.getDateFormat();
        sessionData.distance = totalDistance;
        sessionData.duration = getDuration();
        sessionData.maxElevation = maxElevation;
        sessionData.maxSpeed = maxSpeed;
        sessionData.path = trackGenerator.getPath();
        //save into database.
        DBManager.getInstance(context).saveSession(sessionData);

        //clear this object from memory.
        INSTANCE = null;

        //tell service to remove notification bar via intent message.
        Intent intent = new Intent(context , MainService.class);
        intent.putExtra(TrackingFlags.FLAG_KEY , TrackingFlags.STOP_TRACKING);
        context.startService(intent);

        //start activity.
        Intent aIntent = new Intent(context , PreviewRecentSessionActivity.class);
        aIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(aIntent);

        //kill current activity.
        activity.finish();

        Log.i("TrackerService" , "Tracker Stopped Successfully!!");
    }




    public long getDuration(){
        if (getTrackingState()){
            return SystemClock.elapsedRealtime() - bt;
        }
        return SystemClock.elapsedRealtime() - (SystemClock.elapsedRealtime() - (stwp - bt));
    }




    private boolean getTrackingState(){
        return state.getValue();
    }




    @Override
    public void run(){
        while (!Thread.interrupted()){
            while (locationUpdatesQueue.size() > 0){
                Location location = locationUpdatesQueue.get();
                processLocationUpdates(location);
            }
        }
    }




    private void processLocationUpdates (Location location){
        elevationLiveData.postValue(location.getAltitude());
        speedLiveData.postValue(location.getSpeed());

        //calculate distance.
        if (oldLocation != null){
            totalDistance = totalDistance + location.distanceTo(oldLocation);
            distanceLiveData.postValue(totalDistance);
        }
        oldLocation = location;


        //calculate max speed.
        if (location.getSpeed() > maxSpeed){
            maxSpeed = location.getSpeed();
        }


        //calculate max elevation.
        if (location.getAltitude() > maxElevation){
            maxElevation = location.getAltitude();
        }

    }





    @Override
    public void onLocationChanged(Location location) {
        if (location != null){
            if (getTrackingState()){
                locationUpdatesQueue.add(location);//add the update to the Queue
            }
            currentLocation.setValue(new LatLng(location.getLatitude(), location.getLongitude()));
            trackGenerator.addNewPoint(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    public void onProviderEnabled(String provider) {

    }


    @Override
    public void onProviderDisabled(String provider) {

    }
}

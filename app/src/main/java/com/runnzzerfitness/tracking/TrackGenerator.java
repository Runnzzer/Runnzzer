package com.runnzzerfitness.tracking;


import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.runnzzerfitness.R;

import java.util.ArrayList;
import java.util.List;


public class TrackGenerator {

    private static final float path_width = 8f;

    private ArrayList<PolylineOptions> path = new ArrayList<>();
    private boolean state;
    private onPathChanged listener;
    private Context context;



    public TrackGenerator (Context context , Tracker tracker){
        this.context = context;
        tracker.state.observeForever(aBoolean -> {
            state = aBoolean;

            if(!path.isEmpty()){

                if (!path.get(path.size() - 1).getPoints().isEmpty()){
                    List<LatLng> latLngs = path.get(path.size() - 1).getPoints();

                    PolylineOptions polylineOptions = new PolylineOptions().add(latLngs.get(latLngs.size() - 1));

                    path.add(polylineOptions);
                }

            }

        });
    }



    public void addNewPoint (LatLng latLng){
        if (path.isEmpty()){
            //initialize first item on the list.
            path.add(new PolylineOptions());
        }

        path.get(path.size() - 1)
                .add(latLng)
                .color(getColor())
                .width(path_width);

        if (listener != null){
            listener.onPathChanged(path);
        }

        Log.i("Track" , path.size() -1 + " " + path.get(path.size() - 1).getPoints().size());
    }



    public void onPathChanged (onPathChanged listener){
        this.listener = listener;
    }



    private int getColor (){
        if (state){
            return context.getResources().getColor(R.color.colorAccent);
        }
        return context.getResources().getColor(R.color.grey);
    }



    public ArrayList<PolylineOptions> getPath (){
        return this.path;
    }

}

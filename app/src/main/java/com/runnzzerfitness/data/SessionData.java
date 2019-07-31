package com.runnzzerfitness.data;

import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class SessionData {
    public String title;
    public long duration;
    public double distance;
    public double maxElevation;
    public double maxSpeed;
    public ArrayList<PolylineOptions> path;
}

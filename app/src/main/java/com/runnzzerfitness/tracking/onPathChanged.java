package com.runnzzerfitness.tracking;

import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public interface onPathChanged {
    void onPathChanged(ArrayList<PolylineOptions> path);
}

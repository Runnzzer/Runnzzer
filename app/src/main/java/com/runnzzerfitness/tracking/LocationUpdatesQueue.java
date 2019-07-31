package com.runnzzerfitness.tracking;

import android.location.Location;
import java.util.ArrayList;

/**
 * com.runnzzerfitness.data structure to store location updates temporarylly before get processed.
 * based on first in first out (FIFO) principle.
 */

public class LocationUpdatesQueue {

    private ArrayList<Location> list;


    public LocationUpdatesQueue() {
        this.list = new ArrayList<>();
    }


    /*** @param location item to be added the list.*/
    public void add (Location location){
        list.add(location);
    }


    /*** @return first item on the list and delete it.*/
    public Location get (){
        if (list.size() > 0){
            Location location = list.get(0);
            list.remove(0);
            return location;
        }
        throw new NullPointerException ("location updates queue is empty !!");
    }


    public void clear (){
        list.clear();
    }


    /*** return current array size.*/
    public int size (){
        return list.size();
    }

}

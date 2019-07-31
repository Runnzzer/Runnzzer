package com.runnzzerfitness.data;

public class OverviewDataWrapper {

    public long duration = 0;
    public double distance = 0.0;
    //TODO add calories member and adder function.


    public void addDuration (long duration){
        this.duration = this.duration + duration;
    }


    public void addDistance (double distance){
        this.distance = this.distance + distance;
    }
}

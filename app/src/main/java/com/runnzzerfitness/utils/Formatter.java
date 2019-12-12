package com.runnzzerfitness.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.runnzzerfitness.R;
import com.runnzzerfitness.data.SettingsManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Formatter {
    //TODO use formatter to format texts

    public static String getTime(long time){
        long timeOnSeconds = time / 1000;//convert time from milliseconds to seconds
        return String.format( Locale.ENGLISH ,
                "%02d:%02d:%02d",//format 'hh:mm:ss'
                timeOnSeconds / 3600,//hours.
                (timeOnSeconds % 3600) / 60,//min.
                (timeOnSeconds % 60)//seconds.
        );
    }

    public static String getSpeed(Context context , float speed){
        return String.format(Locale.ENGLISH ,"%.1f %s", Converter.getSpeedVal(context , speed) , Converter.getSpeedSymbol(context));
    }

    public static String getDistance(Context context , double distanceOnMeters){
        return String.format(Locale.ENGLISH
                , "%.1f %s"
                , Converter.getDistanceVal(context , distanceOnMeters)
                , Converter.getDistanceSymbol(context));
    }

    public static String getPace (Context context , long duration , double distance){
        return String.format(Locale.ENGLISH , "%.1f %s" , Converter.getPaceVal(context , duration , distance) , Converter.getPaceSymbol(context));
    }

    public static String getAvgSpeed (Context context , long duration , double distance){
        //TODO add way to prevent Nan return.
        return String.format(Locale.ENGLISH , "%.1f %s" , Converter.getAvgSpeedVal(context , duration , distance) , Converter.getSpeedSymbol(context));
    }

    public static String getMaxElevation (double elevation){
        return String.format(Locale.ENGLISH , "%.1f m" , elevation);
    }

    public static String getHeight (Context context , int mm){
        return String.format(Locale.ENGLISH , "%.1f %s", Converter.getHeightVal(mm) , Converter.getHeightSymbol(context));
    }

    public static String getWeight (Context context , int gram){
        return String.format(Locale.ENGLISH , "%.1f %s", Converter.getWeightVal(context , gram) , Converter.getWeightSymbol(context));
    }

    public static String getAge (int age){
        return String.valueOf(age);
    }

    public static String getHeightSymbol (Context context){
        return context.getString(R.string.cm);
    }

    public static String getWeightSymbol (Context context){
        if (SettingsManager.getSettingsManager(context).getUnite()){
            return context.getString(R.string.kg);
        }
        return context.getString(R.string.pd);
    }

    public static String getGenderSymbol(boolean val){
        if (val){
            return "Male";
        }
        return "Female";
    }

    public static String getDistanceSymbol (Context context){
        if (SettingsManager.getSettingsManager(context).getUnite()){
            return context.getString(R.string.km);
        }else {
            return context.getString(R.string.mi);
        }
    }

    public static String getSpeedSymbol (Context context){
        if (SettingsManager.getSettingsManager(context).getUnite()){
            return context.getString(R.string.kmh);
        }else {
            return context.getString(R.string.mph);
        }
    }

    public static String getPaceSymbol (Context context){
        if (SettingsManager.getSettingsManager(context).getUnite()){
            return "min/km";
        }
        return "min/mi";
    }


    @SuppressLint("SimpleDateFormat")
    public static String getDateFormat(){
        return new SimpleDateFormat("dd.MMM.yyyy hh:mm aa")
                .format( Calendar.getInstance().getTime());
    }

    public static String monthIndex(int month){
        return months[month];
    }

    public static String getTimeFormat (int hour){
        if (hour > 12){
            return "PM";
        }
        return "AM";
    }

    //Time and date utils.
    private static String [] months = {
            "Jan" , "Feb" , "Mar" ,
            "Apr" , "May" ,"Jun" ,
            "Jul" , "Aug" , "Sep" ,
            "Oct" , "Nov" , "Dec"
    };
}

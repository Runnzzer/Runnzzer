package com.runnzzerfitness.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.runnzzerfitness.R;
import com.runnzzerfitness.data.SettingsManager;

public class Converter {



    //get formatted values on String format.
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





    //return Net Values.
    public static double getAvgSpeedVal (Context context , long duration , double distance){
        double hours =  (float) duration / (60*60*1000);
        double dis = Converter.getDistanceVal(context , distance);

        return dis/hours;
    }

    public static double getDistanceVal (Context context , double distanceOnMeters){
        if (SettingsManager.getSettingsManager(context).getUnite()){
            return distanceOnMeters / 1000;
        }else {
            return distanceOnMeters / 1609.34;
        }
    }

    public static double getHeightVal (int mm){
        return mm / 10d;
    }

    public static double getWeightVal (Context context , double grams){
        if (SettingsManager.getSettingsManager(context).getUnite()){
            return grams / 1000d ;
        }else {
            return grams * 0.00220462;
        }
    }

    public static double getPaceVal (Context context , long duration , double distance){
        if (distance == 0 || duration ==0 ) return 00;

        int minutes = (int) duration / 60000;
        double dis = distance / 1000;

        if (!SettingsManager.getSettingsManager(context).getUnite()){
            dis = distance / 1609.34;
        }

        return  minutes / dis;
    }

    public static double getSpeedVal (Context context , float speed){
        if (SettingsManager.getSettingsManager(context).getUnite()){
            return (speed * 3600) / 1000;
        }else {
            return (speed * 3600) / 1609.34;
        }
    }





    //Symbols
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








    public static int[] getDividedTime(long time){
        long timeOnSeconds = time / 1000;
        int[] array = new int [3];
        array [0] = (int) timeOnSeconds / 3600;
        array [1] = (int) (timeOnSeconds % 3600 / 60);
        array [2] = (int) timeOnSeconds % 60;
        return array;
    }

    public static int[] getDividedDistance(Context context , double distance){
        int [] array = new int [2];

        double distanceFactor = 1000;//km dis factor.
        if (!SettingsManager.getSettingsManager(context).getUnite()){
            distanceFactor = 1609.34;//mi dis factor.
        }

        double converterDistance = distance / distanceFactor;//distance from meters to current used distance unite.

        //put converted distance to the array.
        array[0] = (int) (distance / distanceFactor);
        array[1] = (int) ((converterDistance - array[0]) * 10);

        return array;
    }

    public static int[] getDividedWeight (Context context , int grams) {
        double w = Converter.getWeightVal(context, grams);

        int vals[] = new int[2];

        vals[0] = (int) w;
        vals[1] = (int) ((w - vals[0]) * 10);

        return vals;
    }

    public static double toMeters (Context context , double meters){
        if(SettingsManager.getSettingsManager(context).getUnite()){
            return meters * 1000;
        }

        return meters * 1609.34;
    }

    public static double toGrams (Context context , double val){
        if (SettingsManager.getSettingsManager(context).getUnite()){
            return val * 1000;
        }

        return val * 453.592;
    }








    //Date and Age.
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


    public static int getAge (int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return age;
    }


}

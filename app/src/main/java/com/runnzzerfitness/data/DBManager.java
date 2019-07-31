package com.runnzzerfitness.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    private static DBManager INSTANCE = null;

    private static final String TAG = "Database";


    //feed entry sessions table.
    private static final String database_name = "sessions_database";
    private static final String sessions_table_name = "sessions_table";
    private static final String _id = "id";
    private static final String _session = "session";


    private DBManager(@Nullable Context context) {
        super(context,
                database_name,
                null,
                1);
    }


    public static DBManager getInstance (Context context){
        if (INSTANCE == null){
            INSTANCE = new DBManager(context);
        }
        return INSTANCE;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + sessions_table_name
                +"( " + _id + " INTEGER PRIMARY KEY ,"
                + _session + " TEXT "
                + ")"
        );
        Log.i(TAG , "database created !");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists "+sessions_table_name;
        db.execSQL(query);
        onCreate(db);
    }



    /**
     * @param sessionData object to be saved.
     */
    public void saveSession(SessionData sessionData){
        //parse object to string
        String parsedSessionData = new Gson().toJson(sessionData);

        ContentValues contentValues = new ContentValues();
        contentValues.put(_session , parsedSessionData);

        getWritableDatabase().insert(
                sessions_table_name,
                null,
                contentValues
        );

        Log.i(TAG , "session saved !");
    }




    @SuppressLint("Recycle")
    public SessionData getLastSavedSession (){
        Cursor cursor = getReadableDatabase().rawQuery(
                "select * from "
                        + sessions_table_name,
                null);
        cursor.moveToLast();

        return new Gson().fromJson(cursor.getString(cursor.getColumnIndex(_session)) , SessionData.class);
    }



    @SuppressLint("Recycle")
    public SessionData getSessionById (int id){
        String sessionData = null;
        Cursor cursor = getReadableDatabase().rawQuery(
                "select * from "
                        + sessions_table_name
                        + " where "
                        + _id
                        + " = "
                        + id ,
                null
        );
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            sessionData = cursor.getString(cursor.getColumnIndex(_session));
            cursor.moveToNext();
        }


        return new Gson().fromJson(sessionData , SessionData.class);

    }



    @SuppressLint("Recycle")
    public ArrayList<DataWrapper> readAllSessions (){
        ArrayList<DataWrapper> list = new ArrayList<>();
        Cursor cursor = getWritableDatabase().rawQuery(
                "select * from " + sessions_table_name ,
                null
        );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DataWrapper dataWrapper = new DataWrapper();
            dataWrapper.id = cursor.getInt(cursor.getColumnIndex(_id));
            dataWrapper.sessionData = new Gson().fromJson(
                    cursor.getString(cursor.getColumnIndex(_session)) ,//old object as string.
                    SessionData.class//origin class
            );
            list.add(dataWrapper);
            cursor.moveToNext();
        }
        return list;
    }




    /**
     * @param id column where to delete.
     */
    public void deleteSessionById (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        //Delete Row Where the id equal to the id we giving you !
        db.delete(sessions_table_name ,
                _id + " = " + id ,
                null);
        db.close();
    }





    /**
     * @param id column where to update.
     * @param sessionData object to be in the old column.
     */
    public void updateSession (int id , SessionData sessionData ){
        ContentValues contentValues = new ContentValues();
        //put all the data to content values
        contentValues.put(_session, new Gson ().toJson(sessionData));
        //Update the Row in DataBase
        getReadableDatabase().update(
                sessions_table_name
                , contentValues
                , _id + " = " + id
                , null);
    }





    /**
     * it's better to run this on background thread
     */
    @SuppressLint("Recycle")
    public OverviewDataWrapper getOverviewData(){
        OverviewDataWrapper overviewDataWrapper = new OverviewDataWrapper();

        Cursor cursor = getWritableDatabase().rawQuery(
                "select * from " + sessions_table_name ,
                null
        );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            SessionData sd = new Gson().fromJson(
                    cursor.getString(cursor.getColumnIndex(_session)) ,//old object as string.
                    SessionData.class//origin class
            );

            overviewDataWrapper.addDuration(sd.duration);
            overviewDataWrapper.addDistance(sd.distance);

            cursor.moveToNext();
        }

        return overviewDataWrapper;
    }
}
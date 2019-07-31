package com.runnzzerfitness.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.Toast;

import com.runnzzerfitness.R;
import com.runnzzerfitness.fragments.dialogs.DialogBuilder;
import com.runnzzerfitness.fragments.dialogs.DialogListener;
import com.runnzzerfitness.fragments.dialogs.SelectDistance;
import com.runnzzerfitness.fragments.dialogs.SelectDuration;
import com.runnzzerfitness.viewmodules.EditViewModule;

import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;



public class AddSessionActivityBinder {

    private EditViewModule editViewModule;
    private Context context;
    private AppCompatActivity activity;

    public AddSessionActivityBinder( AppCompatActivity activity , Context context, EditViewModule viewModule) {
        this.editViewModule = viewModule;
        this.activity = activity;
        this.context = context;
    }


    public void showDatePicker (){
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, monthOfYear, dayOfMonth) ->
        {
            showTimePicker(
                    //date format eg. 04.May.2019
                    String.format(Locale.ENGLISH , "%02d.%s.%d" , dayOfMonth , Converter.monthIndex(monthOfYear) , year)
            );
        };

        new DatePickerDialog(
                context,
                onDateSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }


    private void showTimePicker (final String inCompletedTitle){
        Calendar myCalendar = Calendar.getInstance();
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker = new TimePickerDialog(context, (timePicker, selectedHour, selectedMinute)
                -> editViewModule.editTitle(
                String.format(Locale.ENGLISH , "%s %02d:%02d %s" , inCompletedTitle , selectedHour , selectedMinute , Converter.getTimeFormat(selectedHour)
                )
        ), hour, minute, true);//Yes 24 hour time

        mTimePicker.setTitle(activity.getString(R.string.select_time));
        mTimePicker.show();
    }


    public void showDurationFragment (){
        SelectDuration selectDuration = new SelectDuration();
        selectDuration.setInitValue(editViewModule.getData().duration);

        DialogBuilder dialogBuilder = new DialogBuilder(activity, selectDuration, new DialogListener() {
            @Override
            public void getRespond(double val) {
                editViewModule.editDuration((long) val);
            }
        });

        dialogBuilder.show(R.id.add_session_activity);
    }


    public void showDistanceFragment (){
        SelectDistance selectDuration = new SelectDistance();
        selectDuration.setInitValue(editViewModule.getData().distance);

        DialogBuilder dialogBuilder = new DialogBuilder(activity, selectDuration, new DialogListener() {
            @Override
            public void getRespond(double val) {
                editViewModule.editDistance(val);
            }
        });

        dialogBuilder.show(R.id.add_session_activity);
    }


    public void save (){
        editViewModule.saveData();
        back();
    }


    public void back (){
        activity.onBackPressed();
    }

}

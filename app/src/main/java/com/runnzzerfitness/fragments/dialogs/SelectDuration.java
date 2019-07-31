package com.runnzzerfitness.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SelectDurationBinding;
import com.runnzzerfitness.utils.Converter;
import com.runnzzerfitness.viewmodules.EditViewModule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

public class SelectDuration extends DialogFragment{

    private SelectDurationBinding selectDurationBinding;
    private long duration = 0;
    private DialogBuilder dialogBuilder;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectDurationBinding = DataBindingUtil.inflate(inflater , R.layout.select_duration , container , false);

        int [] vals = Converter.getDividedTime(duration);
        selectDurationBinding.numPickerOne.setValue(vals[0]);//hh
        selectDurationBinding.numPickerTwo.setValue(vals[1]);//mm
        selectDurationBinding.numPickerThree.setValue(vals[2]);//ss

        selectDurationBinding.setFragment(this);

        return selectDurationBinding.getRoot();
    }



    @Override
    public void setInitValue(double value) {
        this.duration = (long) value;
    }



    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }




    public void ok (){
        //get values from number pickers.
        int v1 = selectDurationBinding.numPickerOne.getValue();//hh
        int v2 = selectDurationBinding.numPickerTwo.getValue();//mm
        int v3 = selectDurationBinding.numPickerThree.getValue();//seconds

        //compose duration on ms.
        long duration = (
               //hh         //mm      //ss
                (v1 * 3600) + (v2 * 60) + v3
        ) * 1000;//duration on milliseconds.


        dialogBuilder.setRespond(duration);
    }


}

package com.runnzzerfitness.fragments.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.GoogleMap;
import com.runnzzerfitness.R;
import com.runnzzerfitness.data.SettingsManager;
import com.runnzzerfitness.databinding.SelectMapStyleBinding;
import com.shawnlin.numberpicker.NumberPicker;


public class SelectMapStyle extends DialogFragment {


    private DialogBuilder dialogBuilder;
    private NumberPicker numberPicker;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SelectMapStyleBinding selectMapStyleBinding = DataBindingUtil.inflate(inflater , R.layout.select_map_style, container , false);
        numberPicker = selectMapStyleBinding.getRoot().findViewById(R.id.num_picker_one);

        SettingsManager settingsManager = SettingsManager.getSettingsManager(getContext());

        String [] displayValues = settingsManager.getMapStylesTitles();

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(displayValues.length - 1);
        numberPicker.setDisplayedValues(displayValues);

        switch (settingsManager.getMapStyle()){

            case GoogleMap.MAP_TYPE_NORMAL :
                numberPicker.setValue(0);
                break;

            case GoogleMap.MAP_TYPE_SATELLITE :
                numberPicker.setValue(1);
                break;

            case GoogleMap.MAP_TYPE_TERRAIN :
                numberPicker.setValue(2);
                break;

        }

        //binding args.
        selectMapStyleBinding.setFragment(this);

        return selectMapStyleBinding.getRoot();
    }



    @Override
    public void setInitValue(double value) {
        //empty required.
    }



    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }



    public void ok (){
        dialogBuilder.setRespond(numberPicker.getValue());
    }
}

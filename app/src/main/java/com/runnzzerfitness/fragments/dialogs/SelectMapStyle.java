package com.runnzzerfitness.fragments.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

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

        String[] displayValues = {
                "",
                "",
                ""
        };

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(displayValues.length - 1);
        numberPicker.setDisplayedValues(displayValues);


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
        Toast.makeText(getContext(), numberPicker.getValue() + "", Toast.LENGTH_SHORT).show();
        dialogBuilder.setRespond(numberPicker.getValue());
    }
}

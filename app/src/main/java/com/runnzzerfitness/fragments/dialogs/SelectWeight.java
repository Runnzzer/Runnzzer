package com.runnzzerfitness.fragments.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SelectWeightBinding;
import com.runnzzerfitness.utils.Converter;


public class SelectWeight extends DialogFragment{

    private SelectWeightBinding selectWeightBinding;
    private DialogBuilder dialogBuilder;
    private double weight = 0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectWeightBinding = DataBindingUtil.inflate(inflater , R.layout.select_weight , container , false);

        selectWeightBinding.setContext(getContext());
        selectWeightBinding.setFragment(this);

        int [] vals = Converter.getDividedWeight(getContext() ,(int) weight);
        selectWeightBinding.numPickerOne.setValue(vals[0]);
        selectWeightBinding.numPickerTwo.setValue(vals[1]);

        return selectWeightBinding.getRoot();
    }



    @Override
    public void setInitValue(double value) {
        this.weight = value;
    }



    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }




    public void ok (){
        double returnedVal = selectWeightBinding.numPickerOne.getValue() + ((selectWeightBinding.numPickerTwo.getValue() * 0.1));
        dialogBuilder.setRespond(Converter.toGrams(getContext() , returnedVal));
    }


}

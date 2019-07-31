package com.runnzzerfitness.fragments.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SelectDistanceBinding;
import com.runnzzerfitness.utils.Converter;
import com.runnzzerfitness.viewmodules.EditViewModule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;


public class SelectDistance extends DialogFragment {

    private SelectDistanceBinding selectDistanceBinding;
    private double distance = 0;
    private DialogBuilder  dialogBuilder;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectDistanceBinding = DataBindingUtil.inflate(inflater , R.layout.select_distance , container , false);

        int [] vals = Converter.getDividedDistance(getContext() , distance);

        selectDistanceBinding.numPickerOne.setValue(vals[0]);
        selectDistanceBinding.numPickerTwo.setValue(vals[1]);
        selectDistanceBinding.distanceSymbol.setText(Converter.getDistanceSymbol(getContext()));

        selectDistanceBinding.setFragment(this);

        return selectDistanceBinding.getRoot();
    }



    @Override
    public void setInitValue(double value) {
        this.distance = value;
    }



    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }



    public void ok () {
        double numPickerVal = selectDistanceBinding.numPickerOne.getValue() + (selectDistanceBinding.numPickerTwo.getValue() * 0.10);
        dialogBuilder.setRespond(Converter.toMeters(getContext() , numPickerVal));
    }

}

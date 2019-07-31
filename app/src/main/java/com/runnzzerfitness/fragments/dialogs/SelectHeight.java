package com.runnzzerfitness.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SelectHeightBinding;
import com.runnzzerfitness.utils.Converter;

public class SelectHeight extends DialogFragment{

    private SelectHeightBinding selectHeightBinding;
    private DialogBuilder dialogBuilder;
    private int height = 0;//mm




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectHeightBinding = DataBindingUtil.inflate(inflater, R.layout.select_height ,container, false);

        //set args for binding.
        selectHeightBinding.setFragment(this);

        //set default value for number picker.
        selectHeightBinding.numPickerOne.setValue((int) Converter.getHeightVal(height));

        return selectHeightBinding.getRoot();
    }



    @Override
    public void setInitValue(double value) {
        this.height = (int) value;
    }



    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }



    public void ok (){
        dialogBuilder.setRespond(selectHeightBinding.numPickerOne.getValue() * 10);//from cm to mm.
    }



    public void back (){
        getActivity().onBackPressed();
    }

}

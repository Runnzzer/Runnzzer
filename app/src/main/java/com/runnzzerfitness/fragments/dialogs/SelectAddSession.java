package com.runnzzerfitness.fragments.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SelectAddSessionBinding;

public class SelectAddSession extends DialogFragment {

    private DialogBuilder dialogBuilder;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SelectAddSessionBinding selectAddSessionBinding = DataBindingUtil.inflate(inflater , R.layout.select_add_session , container , false);

        selectAddSessionBinding.setFragment(this);

        return selectAddSessionBinding.getRoot();
    }


    @Override
    public void setInitValue(double value) {
        //required empty.
    }



    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }



    public void recordSession (){
        dialogBuilder.setRespond(0);
    }



    public void addSessionManually (){
        dialogBuilder.setRespond(1);
    }


}

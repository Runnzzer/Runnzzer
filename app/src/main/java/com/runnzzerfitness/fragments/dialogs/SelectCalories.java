package com.runnzzerfitness.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SelectCaloriesBinding;
import com.runnzzerfitness.viewmodules.EditViewModule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;


public class SelectCalories extends DialogFragment  {

    private SelectCaloriesBinding selectCaloriesBinding;
    private DialogBuilder dialogBuilder;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectCaloriesBinding = DataBindingUtil.inflate(inflater , R.layout.select_calories , container,false);

        return selectCaloriesBinding.getRoot();
    }



    @Override
    public void setInitValue(double value) {

    }


    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }
}

package com.runnzzerfitness.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.DeleteDialogBinding;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;



public class DeleteAlertDialog extends DialogFragment {

    private DialogBuilder dialogBuilder;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DeleteDialogBinding alertDialogFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.delete_dialog, container, false);

        alertDialogFragmentBinding.setFragment(this);

        return alertDialogFragmentBinding.getRoot();
    }



    @Override
    public void setInitValue(double value) {
    }



    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }



    public void ok (){
        dialogBuilder.setRespond(0);
    }


}
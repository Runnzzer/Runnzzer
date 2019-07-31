package com.runnzzerfitness.fragments.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.CustomDialogBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


public class CustomDialog extends Fragment {


    private DialogBuilder dialogBuilder;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CustomDialogBinding customDialogBinding = DataBindingUtil.inflate(inflater , R.layout.custom_dialog , container , false);

        //set binding args.
        customDialogBinding.setDialogBuilder(dialogBuilder);

        //add fragment on the center container.
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.dialog_container , dialogBuilder.getDialogFragment())
                .commit();

        return customDialogBinding.getRoot();
    }




    public void setDialogBuilder (DialogBuilder dialogBuilder){
        this.dialogBuilder = dialogBuilder;
    }


}

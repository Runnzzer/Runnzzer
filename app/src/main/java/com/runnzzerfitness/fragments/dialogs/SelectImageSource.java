package com.runnzzerfitness.fragments.dialogs;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SelectImageSourceBinding;
import com.runnzzerfitness.ui.activities.ProfileActivity;


public class SelectImageSource extends DialogFragment{



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SelectImageSourceBinding selectImageSourceBinding = DataBindingUtil.inflate(inflater , R.layout.select_image_source , container , false);

        //set binding args.
        selectImageSourceBinding.setFragment(this);

        return selectImageSourceBinding.getRoot();
    }



    public void openGallery (){
        ProfileActivity profileActivity = (ProfileActivity) getActivity();
        profileActivity.openGallery();
        back();
    }



    public void openCamera (){
        ProfileActivity profileActivity = (ProfileActivity) getActivity();
        profileActivity.openCamera();
        back();
    }



    @Override
    public void setInitValue(double value) {

    }


    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {

    }



    private void back (){
        getActivity().getSupportFragmentManager().popBackStack();
    }
}

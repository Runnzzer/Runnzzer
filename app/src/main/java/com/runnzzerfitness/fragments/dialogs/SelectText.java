package com.runnzzerfitness.fragments.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SelectTextBinding;

public class SelectText extends DialogFragment {

    private DialogBuilder dialogBuilder;
    private String text;
    private SelectTextBinding selectTextBinding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectTextBinding = DataBindingUtil.inflate(inflater , R.layout.select_text , container , false);

        //set binding args.
        selectTextBinding.setFragment(this);

        //set editText text.
        selectTextBinding.editText.setText(text);

        return selectTextBinding.getRoot();
    }


    public void setInitText (String text){
        this.text = text;
    }


    @Override
    public void setInitValue(double value) {

    }



    @Override
    public void setDialogBuilder(DialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }




    public void ok (){
        String text = selectTextBinding.editText.getText().toString();
        dialogBuilder.setRespond(text);
    }

}

package com.runnzzerfitness.fragments.dialogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DialogBuilder {

    private AppCompatActivity appCompatActivity ;
    private DialogFragment dialogFragment ;
    private DialogListener dialogListener;
    private TextDialogListener textDialogListener;


    public DialogBuilder(@NonNull AppCompatActivity appCompatActivity, @NonNull DialogFragment dialogFragment){
        this.appCompatActivity = appCompatActivity;
        //initialize dialog fragment.
        this.dialogFragment = dialogFragment;
        this.dialogFragment.setDialogBuilder(this);//set builder to return responds.
    }



    public DialogBuilder(@NonNull AppCompatActivity appCompatActivity, @NonNull DialogFragment dialogFragment, @NonNull DialogListener dialogListener) {
        this.appCompatActivity = appCompatActivity;
        //initialize dialog fragment.
        this.dialogFragment = dialogFragment;
        this.dialogFragment.setDialogBuilder(this);//set builder to return responds.

        this.dialogListener = dialogListener;
    }



    public DialogBuilder(@NonNull AppCompatActivity appCompatActivity, @NonNull DialogFragment dialogFragment, @NonNull TextDialogListener textDialogListener) {
        this.appCompatActivity = appCompatActivity;
        //initialize dialog fragment.
        this.dialogFragment = dialogFragment;
        this.dialogFragment.setDialogBuilder(this);//set builder to return responds.

        this.textDialogListener = textDialogListener;
    }




    public void show (int containerId){

        CustomDialog dialog = new CustomDialog();
        dialog.setDialogBuilder(this);

        //show fragment on given view.
        appCompatActivity
                .getSupportFragmentManager()
                .beginTransaction()
                .add(containerId , dialog)
                .addToBackStack(null)
                .commit();
    }




    public void setRespond (double val){
        dialogListener.getRespond(val);//notify listener about given returned result.
        hide();
    }




    /**
     * This function only can called when u build this Object via second constructor.
     * */
    public void setRespond (String val){
        textDialogListener.getText(val);
        hide();
    }




    public DialogFragment getDialogFragment (){
        return this.dialogFragment;
    }




    public void hide (){
        appCompatActivity.onBackPressed();//hide custom dialog fragment.
    }


}

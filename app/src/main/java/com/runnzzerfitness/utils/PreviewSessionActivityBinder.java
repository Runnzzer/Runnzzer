package com.runnzzerfitness.utils;

import android.content.Context;
import android.content.Intent;

import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.fragments.dialogs.DeleteAlertDialog;
import com.runnzzerfitness.fragments.dialogs.DialogBuilder;
import com.runnzzerfitness.fragments.dialogs.DialogListener;
import com.runnzzerfitness.fragments.dialogs.SelectDistance;
import com.runnzzerfitness.ui.activities.EditSessionActivity;

import androidx.appcompat.app.AppCompatActivity;


public class PreviewSessionActivityBinder {

    private AppCompatActivity appCompatActivity;
    private Context context;
    private int sessionId;



    public PreviewSessionActivityBinder(AppCompatActivity appCompatActivity , Context context, int sessionId) {
        this.context = context;
        this.sessionId = sessionId;
        this.appCompatActivity = appCompatActivity;
    }



    public void openEditActivity (){
        Intent intent = new Intent(context , EditSessionActivity.class);
        intent.putExtra("id" , sessionId);//put id
        context.startActivity(intent);
    }



    public void showDeleteDialog (){
        DeleteAlertDialog deleteAlertDialog = new DeleteAlertDialog();

        DialogBuilder dialogBuilder = new DialogBuilder(appCompatActivity, deleteAlertDialog, new DialogListener() {
            @Override
            public void getRespond(double val) {
                if (val == 0){
                    //delete session.
                    DBManager.getInstance(context).deleteSessionById(sessionId);

                    //back to mainActivity
                    back();
                }
            }
        });

        dialogBuilder.show(R.id.preview_session_root);

    }



    public void back (){
        appCompatActivity.onBackPressed();
    }

}

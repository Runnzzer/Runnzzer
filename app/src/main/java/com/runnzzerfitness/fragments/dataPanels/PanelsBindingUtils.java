package com.runnzzerfitness.fragments.dataPanels;

import android.content.Context;

import com.runnzzerfitness.R;

import androidx.fragment.app.FragmentManager;
import com.runnzzerfitness.data.SettingsManager;

public class PanelsBindingUtils {

    private Context context;
    private FragmentManager fragmentManager;


    //TODO remove this binder.

    public PanelsBindingUtils(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;

        loadFragments();
    }


    private void loadFragments (){
        fragmentManager.beginTransaction()
                .add(R.id.first_panel , SettingsManager.getSettingsManager(context).getFirstPanel())
                .add(R.id.second_panel , SettingsManager.getSettingsManager(context).getSecondPanel())
                .add(R.id.third_panel , SettingsManager.getSettingsManager(context).getThirdPanel())
                .add(R.id.fourth_panel , SettingsManager.getSettingsManager(context).getFourthPanel())
                .commit();
    }
}

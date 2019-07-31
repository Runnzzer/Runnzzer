package com.runnzzerfitness.fragments.dataPanels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SpeedPanelFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.runnzzerfitness.tracking.Tracker;
import com.runnzzerfitness.utils.Converter;

import java.util.Locale;

public class SpeedPanel extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final SpeedPanelFragmentBinding speedPanelFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.speed_panel_fragment , container , false);


        //speed unite text.
        speedPanelFragmentBinding.speedDistanceUnite.setText(Converter.getSpeedSymbol(getContext()));


        Tracker.getInstance().speedLiveData.observe(this,
                aFloat -> speedPanelFragmentBinding.speedValueText
                        .setText(String.format(Locale.ENGLISH , "%.1f" , Converter.getSpeedVal(getContext() , aFloat))));


        return speedPanelFragmentBinding.getRoot();
    }
}

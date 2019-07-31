package com.runnzzerfitness.fragments.dataPanels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.ElevationPanelFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.runnzzerfitness.tracking.Tracker;

public class ElevationPanel extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ElevationPanelFragmentBinding elevationPanelFragmentBinding = DataBindingUtil.inflate(inflater ,
                R.layout.elevation_panel_fragment ,
                container ,
                false);

        Tracker.getInstance().elevationLiveData.observe(this,
                aDouble -> elevationPanelFragmentBinding.elevationValueText.setText(String.format("%s", aDouble))
        );

        return elevationPanelFragmentBinding.getRoot();
    }
}

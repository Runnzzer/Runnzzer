package com.runnzzerfitness.fragments.dataPanels;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.DistancePanleFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.runnzzerfitness.tracking.Tracker;
import com.runnzzerfitness.utils.Converter;

import java.util.Locale;


public class DistancePanel extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final DistancePanleFragmentBinding distancePanleFragmentBinding = DataBindingUtil.inflate(inflater ,
                R.layout.distance_panle_fragment ,
                container ,
                false);

        //set distance unite text.
        distancePanleFragmentBinding.distanceUnite.setText(Converter.getDistanceSymbol(getContext()));

        Tracker.getInstance().distanceLiveData.observe(this, aDouble -> distancePanleFragmentBinding
                .distanceValue
                .setText(
                        //set converted distance value to text.
                        String.format(Locale.ENGLISH , "%.1f", Converter.getDistanceVal(getContext(), aDouble))
                ));

        return distancePanleFragmentBinding.getRoot();
    }




}

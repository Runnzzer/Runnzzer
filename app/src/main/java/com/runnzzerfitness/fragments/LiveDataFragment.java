package com.runnzzerfitness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.LiveDataFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.runnzzerfitness.fragments.dataPanels.PanelsBindingUtils;

public class LiveDataFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LiveDataFragmentBinding liveDataFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.live_data_fragment , container , false);
        liveDataFragmentBinding.setPanelBinding(new PanelsBindingUtils(getContext() , getActivity().getSupportFragmentManager()));
        return liveDataFragmentBinding.getRoot();
    }
}

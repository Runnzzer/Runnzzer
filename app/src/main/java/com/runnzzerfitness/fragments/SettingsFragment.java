package com.runnzzerfitness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SettingsFragmentBinding;


public class SettingsFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SettingsFragmentBinding settingsFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.settings_fragment , container , false);

        return settingsFragmentBinding.getRoot();
    }

}

package com.runnzzerfitness.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.SettingsFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.runnzzerfitness.utils.MainActivityBinder;
import com.runnzzerfitness.ui.activities.MainActivity;
import com.runnzzerfitness.viewmodules.MainActivityViewModule;


public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SettingsFragmentBinding settingsFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.settings_fragment , container , false);

        settingsFragmentBinding.setMainViewModule(ViewModelProviders.of(this).get(MainActivityViewModule.class));
        settingsFragmentBinding.setBinder(new MainActivityBinder (getContext() ,(MainActivity) getActivity()));
        settingsFragmentBinding.setFragmentActivity(getActivity());

        settingsFragmentBinding.setLifecycleOwner(this);

        return settingsFragmentBinding.getRoot();
    }
}

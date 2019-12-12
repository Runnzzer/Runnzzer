package com.runnzzerfitness.fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding homeFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.home_fragment , container , false);

        return homeFragmentBinding.getRoot();
    }
}

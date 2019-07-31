package com.runnzzerfitness.fragments.dataPanels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.PacePanelFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class PacePanle extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PacePanelFragmentBinding pacePanelFragmentBinding = DataBindingUtil.inflate(inflater ,
                R.layout.pace_panel_fragment ,
                container ,
                false);

        return pacePanelFragmentBinding.getRoot();
    }
}

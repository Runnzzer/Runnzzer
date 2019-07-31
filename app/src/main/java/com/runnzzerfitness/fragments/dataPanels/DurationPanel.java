package com.runnzzerfitness.fragments.dataPanels;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.DurationPanleFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.runnzzerfitness.tracking.Tracker;
import com.runnzzerfitness.utils.Converter;


public class DurationPanel extends Fragment implements Runnable,Observer<Boolean>{


    private DurationPanleFragmentBinding durationPanleFragmentBinding;
    private Handler handler = new Handler();
    private Tracker tracker;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        durationPanleFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.duration_panle_fragment , container , false);

        tracker = Tracker.getInstance();

        //observe com.runnzzerfitness.tracking state.
        tracker.state.observe(this, this);

        return durationPanleFragmentBinding.getRoot();
    }




    private void startCounting() {
        handler.postDelayed(this , 0);
    }




    private void stopCounting() {
        handler.removeCallbacks(this);
    }









    @Override
    public void onChanged(Boolean aBoolean) {
        durationPanleFragmentBinding.counterText.setText(Converter.getTime(tracker.getDuration()));
        if (aBoolean){
            startCounting();
        }else {
            stopCounting();
        }
    }




    @Override
    public void run() {
        durationPanleFragmentBinding.counterText.setText(Converter.getTime(tracker.getDuration()));
        handler.postDelayed(this , 0);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopCounting();
    }
}


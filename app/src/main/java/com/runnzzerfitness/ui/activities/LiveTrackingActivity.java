package com.runnzzerfitness.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.ActivityLiveTrackingBinding;

public class LiveTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLiveTrackingBinding activityLiveTrackingBinding = DataBindingUtil.setContentView(this , R.layout.activity_live_tracking);

        activityLiveTrackingBinding.setFragmentManager(getSupportFragmentManager());
    }

}

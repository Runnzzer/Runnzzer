package com.runnzzerfitness.ui.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.PolylineOptions;
import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.SessionData;
import com.runnzzerfitness.databinding.PreviewRecentSessionBinding;
import com.runnzzerfitness.utils.PreviewRecentSessionBinder;

import java.util.ArrayList;


public class PreviewRecentSessionActivity extends AppCompatActivity implements OnMapReadyCallback {

    private PreviewRecentSessionBinder previewRecentSessionBinder;
    private SessionData sessionData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreviewRecentSessionBinding activitySessionPreviewBinding = DataBindingUtil.setContentView(this , R.layout.preview_recent_session);

        //set Args for binding.
        activitySessionPreviewBinding.setSessionData(sessionData = DBManager.getInstance(this).getLastSavedSession());
        activitySessionPreviewBinding.setBinder(previewRecentSessionBinder = new PreviewRecentSessionBinder(this , this));
        activitySessionPreviewBinding.setContext(this);

        //Obtain the SupportMapFragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        //map sync
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onBackPressed() {
        previewRecentSessionBinder.openMainActivity();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null){
            ArrayList<PolylineOptions> path = sessionData.path;
            //draw path to the Map
            for (int i=0;i<path.size();i++){
                googleMap.addPolyline(path.get(i));
            }
        }
    }
}

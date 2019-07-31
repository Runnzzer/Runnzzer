package com.runnzzerfitness.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.SessionData;
import com.runnzzerfitness.databinding.PreviewSessionBinding;
import com.runnzzerfitness.utils.PreviewSessionActivityBinder;



public class PreviewSessionActivity extends AppCompatActivity implements OnMapReadyCallback {

    private int sessionId;//id on "sessions" table.
    public MutableLiveData<SessionData> sessionData = new MutableLiveData<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreviewSessionBinding previewSessionBinding = DataBindingUtil.setContentView(this, R.layout.preview_session);

        //session id on the database.
        sessionId = getIntent().getExtras().getInt("id");

        //set binding args.
        previewSessionBinding.setActivity(this);
        previewSessionBinding.setContext(this);
        previewSessionBinding.setBinder(new PreviewSessionActivityBinder( this , this , sessionId));

        //observation.
        previewSessionBinding.setLifecycleOwner(this);

        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        //mapFragment.getMapAsync(this);
    }




    @Override
    protected void onResume() {
        super.onResume();
        //updateScreen screen by setting new value on the live data.
        sessionData.setValue(DBManager.getInstance(this).getSessionById(sessionId));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}

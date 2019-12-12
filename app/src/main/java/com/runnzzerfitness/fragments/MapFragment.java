package com.runnzzerfitness.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.runnzzerfitness.R;
import com.runnzzerfitness.data.SettingsManager;
import com.runnzzerfitness.databinding.MapFragmentBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.runnzzerfitness.tracking.Tracker;



public class MapFragment extends Fragment implements OnMapReadyCallback{


    private static final int map_camera_zoom = 16;//map camera zoom level.
    private Tracker tracker;
    private GoogleMap googleMap;
    private MapFragmentBinding mapFragmentBinding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mapFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.map_fragment , container , false);

        tracker = Tracker.getInstance();

        //set arguments for binding.
        mapFragmentBinding.setTracker(tracker);
        mapFragmentBinding.setFragmentActivity(getActivity());
        mapFragmentBinding.setContext(getContext());
        mapFragmentBinding.setFragment(this);

        //observation.
        mapFragmentBinding.setLifecycleOwner(this);

        return mapFragmentBinding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapView mapView = view.findViewById(R.id.map);//get mapView.
        mapView.onCreate(null);
        mapView.onResume();
        mapView.getMapAsync(this);
    }



    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if (googleMap != null){
            MapsInitializer.initialize(getContext());

            //set map default settings.
            googleMap.setMyLocationEnabled(true);//add blue dot of current location to the map.
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);//disable current location button.
            googleMap.setMapType(SettingsManager.getSettingsManager(getContext()).getMapStyle());//Map Type.

            //Observe last location update and update map camera.
            tracker.currentLocation.observe(this, latLng -> {
                if (latLng != null){
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , map_camera_zoom));
                }
            });

            //observe path state and draw it to the map.
            tracker.trackGenerator.onPathChanged(path -> {
                for (int i=0; i<path.size() ;i++)
                    googleMap.addPolyline(path.get(i));
            });

        }
    }



    public void changeMapStyle () {
        PopupMenu popup = new PopupMenu(getContext() , mapFragmentBinding.mapStylesButton);
        popup.getMenuInflater().inflate(R.menu.map_styles, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {

            SettingsManager settingsManager = SettingsManager.getSettingsManager(getContext());
            switch (item.getItemId()){
                case R.id.normal :
                    settingsManager.changeMapStyle(GoogleMap.MAP_TYPE_NORMAL);
                    break;

                case R.id.satellite :
                    settingsManager.changeMapStyle(GoogleMap.MAP_TYPE_SATELLITE);
                    break;

                case R.id.terrain :
                    settingsManager.changeMapStyle(GoogleMap.MAP_TYPE_TERRAIN);
                    break;
            }

            //change Map Style if the map is present.
            if (googleMap != null){
                googleMap.setMapType(settingsManager.getMapStyle());
            }

            return true;
        });
        popup.show();

    }

}

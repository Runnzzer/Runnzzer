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
import com.runnzzerfitness.databinding.MapFragmentBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.runnzzerfitness.tracking.Tracker;



public class MapFragment extends Fragment implements OnMapReadyCallback {


    private static final int map_camera_zoom = 16;//map camera zoom level.
    private Tracker tracker;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MapFragmentBinding mapFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.map_fragment , container , false);

        tracker = Tracker.getInstance();

        //set arguments for binding.
        mapFragmentBinding.setTracker(tracker);
        mapFragmentBinding.setFragmentActivity(getActivity());
        mapFragmentBinding.setContext(getContext());

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

        MapsInitializer.initialize(getContext());

        if (googleMap != null){
            //show blue dot of current location on the map.
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);

            //Observe last location update and update map camera.
            tracker.currentLocation.observe(this, latLng -> {
                if (latLng != null){
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , map_camera_zoom));
                }
            });

            //observe path state.
            tracker.trackGenerator.onPathChanged(path -> {
                for (int i=0; i<path.size() ;i++)
                    googleMap.addPolyline(path.get(i));
            });
        }
    }


}

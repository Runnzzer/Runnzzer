package com.runnzzerfitness.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.runnzzerfitness.utils.MainActivityBinder;
import com.runnzzerfitness.fragments.HistoryFragment;
import com.runnzzerfitness.fragments.HomeFragment;
import com.runnzzerfitness.fragments.SettingsFragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.runnzzerfitness.tracking.MainService;
import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.ActivityMainBinding;
import com.runnzzerfitness.tracking.TrackingFlags;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //start service if it's not running.
        startService(new Intent(this , MainService.class));

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        BottomNavigationView bottomNavigationView = activityMainBinding.bottomNavigationView;

        //set default fragment on the container.
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer , new HomeFragment())
                .commit();

        //set listener.
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;

            case R.id.nav_his:
                fragment = new HistoryFragment();
                break;

            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer , fragment)
                .commit();

        return true;
    }


    public void openTrackingActivity(){
        //permission granted successfully.
        finish();
        Intent intent = new Intent(this, MainService.class);
        intent.putExtra(TrackingFlags.FLAG_KEY , TrackingFlags.START_TRACKING);
        startService(intent);
        startActivity(new Intent(this , LiveTrackingActivity.class));
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MainActivityBinder.LOCATION_REQUEST_CODE){
            //
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                openTrackingActivity();
            }

        }
    }
}

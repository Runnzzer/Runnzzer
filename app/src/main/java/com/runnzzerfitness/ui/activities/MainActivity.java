package com.runnzzerfitness.ui.activities;


import android.content.pm.PackageManager;
import android.os.Bundle;


import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.OverviewDataWrapper;
import com.runnzzerfitness.databinding.ActivityMainBinding;

import com.runnzzerfitness.fragments.HistoryFragment;
import com.runnzzerfitness.fragments.HomeFragment;
import com.runnzzerfitness.fragments.SettingsFragment;
import com.runnzzerfitness.utils.MainActivityBinder;

import androidx.databinding.DataBindingUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.MutableLiveData;


public class MainActivity extends AppCompatActivity {


    public MutableLiveData<OverviewDataWrapper> overview = new MutableLiveData<>();
    private MainActivityBinder binder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);

        binder = new MainActivityBinder(this , this);

        BottomNavigationView bottomNavigationView = activityMainBinding.bottomNavigationView;

        //set default fragment.
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        setTransition(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            //TODO don't perform transition if fragment already showed on the container.
            switch (menuItem.getItemId()){
                case R.id.nav_home :
                    setTransition(new HomeFragment());
                    break;

                case R.id.nav_his :
                    setTransition(new HistoryFragment());
                    break;

                case R.id.nav_settings :
                    setTransition(new SettingsFragment());
                    break;
            }

            return true;
        });
    }



    private void setTransition (Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container , fragment)
                .commit();
    }



    @Override
    protected void onResume() {
        super.onResume();

        overview.setValue(DBManager.getInstance(this).getOverviewData());
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MainActivityBinder.LOCATION_REQUEST_CODE){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                binder.openTrackingActivity();
            }

        }
    }
}

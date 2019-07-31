package com.runnzzerfitness.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.OverviewDataWrapper;
import com.runnzzerfitness.databinding.HomeFragmentBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.runnzzerfitness.utils.MainActivityBinder;
import com.runnzzerfitness.ui.activities.MainActivity;


public class HomeFragment extends Fragment {

    public MutableLiveData<OverviewDataWrapper> overview = new MutableLiveData<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding homeFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.home_fragment , container , false);

        //set binding args.
        homeFragmentBinding.setBinder(new MainActivityBinder(getContext() , (MainActivity) getActivity()));
        homeFragmentBinding.setFragment(this);
        homeFragmentBinding.setContext(getContext());

        homeFragmentBinding.setLifecycleOwner(this);//observation.

        return homeFragmentBinding.getRoot();
    }



    @Override
    public void onResume() {
        super.onResume();
        //TODO move this to background thread.
        overview.setValue(DBManager.getInstance(getContext()).getOverviewData());
    }
}

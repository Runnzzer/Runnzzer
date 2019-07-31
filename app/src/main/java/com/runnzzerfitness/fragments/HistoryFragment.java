package com.runnzzerfitness.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.runnzzerfitness.ui.activities.MainActivity;
import com.runnzzerfitness.ui.activities.PreviewSessionActivity;
import com.runnzzerfitness.utils.HistoryListAdapter;
import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.DataWrapper;
import com.runnzzerfitness.databinding.HistoryFragmentBinding;
import com.runnzzerfitness.utils.MainActivityBinder;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class HistoryFragment extends Fragment {
    private ListView listView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HistoryFragmentBinding historyFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.history_fragment , container , false);

        listView = historyFragmentBinding.historyList;

        return historyFragmentBinding.getRoot();
    }




    @Override
    public void onResume() {
        super.onResume();

        ArrayList<DataWrapper> sessionsList = DBManager.getInstance(getContext()).readAllSessions();
        Collections.reverse(sessionsList);

        listView.setAdapter(new HistoryListAdapter(getContext() , sessionsList));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext() , PreviewSessionActivity.class);
            intent.putExtra("id" , sessionsList.get(position).id);
            startActivity(intent);
        });
    }



}

package com.runnzzerfitness.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DataWrapper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class HistoryListAdapter extends ArrayAdapter<DataWrapper> {



    public HistoryListAdapter(@NonNull Context context, ArrayList<DataWrapper> list) {
        super(context , R.layout.list_row , list);
    }



    @NonNull
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         View view = LayoutInflater
                 .from(getContext())
                 .inflate(R.layout.list_row , parent , false);

         TextView textView = view.findViewById(R.id.session_title);
         textView.setText(getItem(position).sessionData.title);

         return view;
    }
}

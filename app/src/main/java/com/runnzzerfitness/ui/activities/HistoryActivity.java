package com.runnzzerfitness.ui.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.runnzzerfitness.R;

public class HistoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this , R.layout.history_fragment);

        Toolbar toolbar = findViewById(R.id.include);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.history);
    }
}

package com.runnzzerfitness.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.ActivityAddSessionBinding;
import com.runnzzerfitness.utils.AddSessionActivityBinder;
import com.runnzzerfitness.viewmodules.AddSessionViewModule;

public class AddSessionActivity extends AppCompatActivity {


    private AddSessionActivityBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddSessionBinding activity_add_session = DataBindingUtil.setContentView(this , R.layout.activity_add_session);

        AddSessionViewModule vm = ViewModelProviders.of(this).get(AddSessionViewModule.class);

        //set args for binding.
        activity_add_session.setContext(this);
        activity_add_session.setVm(vm);
        activity_add_session.setBinder(binder = new AddSessionActivityBinder(this ,this , vm));

        activity_add_session.setLifecycleOwner(this);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

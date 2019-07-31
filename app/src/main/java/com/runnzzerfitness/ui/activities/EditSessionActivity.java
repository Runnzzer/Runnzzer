package com.runnzzerfitness.ui.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import com.runnzzerfitness.R;
import com.runnzzerfitness.databinding.EditSessionBinding;
import com.runnzzerfitness.utils.EditSessionBinder;
import com.runnzzerfitness.viewmodules.EditSessionViewModule;


public class EditSessionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditSessionBinding editSessionBinding = DataBindingUtil.setContentView(this , R.layout.edit_session);

        EditSessionViewModule vm = ViewModelProviders.of(this).get(EditSessionViewModule.class);
        vm.setId(getIntent().getExtras().getInt("id"));

        //set args for binding.
        editSessionBinding.setBinder(new EditSessionBinder(this ,this , vm));
        editSessionBinding.setVm(vm);

        //observation.
        editSessionBinding.setLifecycleOwner(this);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

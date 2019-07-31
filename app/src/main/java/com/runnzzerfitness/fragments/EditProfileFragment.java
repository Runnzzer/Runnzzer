package com.runnzzerfitness.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.ProfileDataWrapper;
import com.runnzzerfitness.data.ProfileSettings;
import com.runnzzerfitness.databinding.EditProfileFragmentBinding;
import com.runnzzerfitness.fragments.dialogs.DialogBuilder;
import com.runnzzerfitness.fragments.dialogs.DialogListener;
import com.runnzzerfitness.fragments.dialogs.SelectHeight;
import com.runnzzerfitness.fragments.dialogs.SelectImageSource;
import com.runnzzerfitness.fragments.dialogs.SelectText;
import com.runnzzerfitness.fragments.dialogs.SelectWeight;
import com.runnzzerfitness.fragments.dialogs.TextDialogListener;
import com.runnzzerfitness.ui.activities.ProfileActivity;
import com.runnzzerfitness.utils.Converter;


import java.util.Calendar;



public class EditProfileFragment extends Fragment {


    public MutableLiveData<ProfileDataWrapper> profile = new MutableLiveData<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EditProfileFragmentBinding editProfileFragmentBinding = DataBindingUtil.inflate(inflater , R.layout.edit_profile_fragment , container , false);

        //set args for binding.
        editProfileFragmentBinding.setFragment(this);
        editProfileFragmentBinding.setContext(getContext());

        profile.setValue(ProfileSettings.getInstance(getContext()).getProfile());

        //observation
        editProfileFragmentBinding.setLifecycleOwner(this);

        return editProfileFragmentBinding.getRoot();
    }



    public void selectImage (){
        ProfileActivity profileActivity = (ProfileActivity) getActivity();
        profileActivity.getReturnedImage(bitmap -> {
            profile.getValue().image = bitmap;
            refreshLivaData();
        });

        DialogBuilder dialogBuilder = new DialogBuilder((ProfileActivity) getActivity() , new SelectImageSource());

        dialogBuilder.show(R.id.edit_profile_root);
    }



    public void selectName (){
        SelectText selectText = new SelectText();
        selectText.setInitText(profile.getValue().name);

        DialogBuilder dialogBuilder = new DialogBuilder((ProfileActivity) getActivity(), selectText, new TextDialogListener() {
            @Override
            public void getText(String text) {
                profile.getValue().name = text;
                refreshLivaData();
            }
        });

        dialogBuilder.show(R.id.edit_profile_root);
    }



    public void selectAge (){
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, monthOfYear, dayOfMonth) ->
        {
           profile.getValue().age = Converter.getAge(year , monthOfYear , dayOfMonth);
           refreshLivaData();
        };

        new DatePickerDialog(
                getActivity(),
                onDateSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }



    public void selectHeight (){
        SelectHeight selectHeight = new SelectHeight();
        selectHeight.setInitValue(profile.getValue().height);

        DialogBuilder dialogBuilder = new DialogBuilder((ProfileActivity) getActivity(), selectHeight , new DialogListener() {
            @Override
            public void getRespond(double val) {
                profile.getValue().height = (int) val;
                refreshLivaData();
            }
        });

        dialogBuilder.show(R.id.edit_profile_root);
    }



    public void selectWeight (){
        SelectWeight selectWeight = new SelectWeight();
        selectWeight.setInitValue(profile.getValue().weight);

        DialogBuilder dialogBuilder = new DialogBuilder((ProfileActivity) getActivity(), selectWeight, new DialogListener() {
            @Override
            public void getRespond(double val) {
                profile.getValue().weight = (int) val;
                refreshLivaData();
            }
        });

        dialogBuilder.show(R.id.edit_profile_root);
    }



    public void save (){
        ProfileSettings.getInstance(getContext()).editProfile(profile.getValue());
        ProfileActivity profileActivity = (ProfileActivity) getActivity();
        profileActivity.updateScreen();
        back();
    }



    private void refreshLivaData() {
        profile.setValue(profile.getValue());
    }



    public void back (){
        getActivity().onBackPressed();
    }

}

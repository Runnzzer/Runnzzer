package com.runnzzerfitness.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;


import com.runnzzerfitness.R;
import com.runnzzerfitness.data.DBManager;
import com.runnzzerfitness.data.ProfileDataWrapper;
import com.runnzzerfitness.data.ProfileSettings;
import com.runnzzerfitness.databinding.ActivityProfileBinding;
import com.runnzzerfitness.utils.ProfileActivityBinder;

import java.io.IOException;



public class ProfileActivity extends AppCompatActivity {

    public static final int from_camera_app = 0;
    public static final int from_gallery_app = 1;
    private getBitMap listener;



    public MutableLiveData<ProfileDataWrapper> profile = new MutableLiveData<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding activityProfileBinding = DataBindingUtil.setContentView(this , R.layout.activity_profile);

        //set args for binding.
        activityProfileBinding.setActivity(this);
        activityProfileBinding.setContext(this);
        activityProfileBinding.setBinder(new ProfileActivityBinder(this));

        updateScreen();

        //observation
        activityProfileBinding.setLifecycleOwner(this);
    }




    public void updateScreen(){
        profile.setValue(ProfileSettings.getInstance(this).getProfile());
    }




    public void getReturnedImage (getBitMap listener){
        this.listener = listener;
    }




    public void openGallery (){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"), 1);
        }
    }




    public void openCamera (){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 0);
        }

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this , "get Result back" + requestCode , Toast.LENGTH_SHORT).show();

        switch (requestCode){

            case from_camera_app :
                if (resultCode == RESULT_OK){
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    if (listener != null){
                        listener.getBitMap(imageBitmap);
                    }
                }
                break;

            case from_gallery_app:
                try {
                    Uri uri = data.getData();
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    if (listener != null){
                        listener.getBitMap(mBitmap);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}

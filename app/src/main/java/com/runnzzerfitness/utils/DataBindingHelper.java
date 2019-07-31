package com.runnzzerfitness.utils;



import android.graphics.Bitmap;
import android.view.View;
import android.widget.ScrollView;

import com.google.android.material.tabs.TabLayout;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.runnzzerfitness.fragments.LiveTrackingActivityAdapter;

import de.hdodenhof.circleimageview.CircleImageView;


public class DataBindingHelper {


    @BindingAdapter({"app:viewPager" , "app:fragmentManager"})
    public static void initializeTabWithPager (TabLayout tabLayout , ViewPager viewPager , FragmentManager fragmentManager){
        viewPager.setAdapter(new LiveTrackingActivityAdapter(fragmentManager));
        tabLayout.setupWithViewPager(viewPager);
    }



    @BindingAdapter({"app:isActive"})
    public static void setLayoutOpacity (View view , boolean state){
        if (state){
            view.setAlpha(1.0f);
        }else {
            //emphasis values -> 0.38 //0.60 //0.87
            view.setAlpha(0.38f);
        }
    }



    @BindingAdapter({"app:controlActive"})
    public static void controlActive (View view , boolean state){
        if (state){
            view.setVisibility(View.VISIBLE);
        }else {
            view.setVisibility(View.INVISIBLE);
        }
    }



    @BindingAdapter({"app:controlInActive"})
    public static void controlInActive (View view , boolean state){
        if (!state){
            view.setVisibility(View.VISIBLE);
        }else {
            view.setVisibility(View.INVISIBLE);
        }
    }


    @BindingAdapter({"app:imageSource"})
    public static void imageSource (CircleImageView image , Bitmap bitmap){
        if (bitmap != null){
            image.setImageBitmap(bitmap);
        }
    }




}

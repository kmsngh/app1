package com.example.app1.FragmentActivities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.FragmentActivity;

import com.example.app1.R;

public class CreateContact extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_contact);
    }
}

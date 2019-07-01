package com.example.app1.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.app1.Adapters.PagerController;
import com.example.app1.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    TabItem contacts;
    TabItem gallery;
    TabItem playlist;
    ViewPager mPager;
    PagerController mPagerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tabLayout);
        contacts = findViewById(R.id.contacts);
        gallery = findViewById(R.id.gallery);
        playlist = findViewById(R.id.playlist);
        mPager = findViewById(R.id.viewpager);

        mPagerController = new PagerController(getSupportFragmentManager(), mTabLayout.getTabCount());
        mPager.setAdapter(mPagerController);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }

}

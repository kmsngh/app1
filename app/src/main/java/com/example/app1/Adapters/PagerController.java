package com.example.app1.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.app1.Fragments.Contacts;
import com.example.app1.Fragments.Gallery;
import com.example.app1.Fragments.Playlist;

public class PagerController extends FragmentPagerAdapter {

    int tabCounts;

    public PagerController(FragmentManager fm, int tabCounts) {
        super(fm);
        this.tabCounts = tabCounts;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new Contacts();
            case 1:
                return new Gallery();
            case 2:
                return new Playlist();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCounts;
    }
}

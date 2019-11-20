package com.nssoft.instagramtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Adapter_pager extends FragmentPagerAdapter {

    Fragment[] fragments=new Fragment[3];
    String[] pageTitles=new String[]{"갤러리", "사진", "동영상"};

    public Adapter_pager(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0]=new Frag_tab_gallery();
        fragments[1]=new Frag_tab_photo();
        fragments[2]=new Frag_tab_video();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}

package com.example.quanlythuchi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.quanlythuchi.fragment.FragmentKhoanChi;
import com.example.quanlythuchi.fragment.FragmentLoaiChi;


public class ChiPagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public ChiPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag = null;
        switch (i){
            case 0:
                frag = new FragmentKhoanChi();
                break;
            case 1:
                frag = new FragmentLoaiChi();
                break;
            default:
                return null;
        }
        return frag;

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Khoản Chi";
                break;
            case 1:
                title = "Loại Chi";
                break;
        }
        return title;
    }
}



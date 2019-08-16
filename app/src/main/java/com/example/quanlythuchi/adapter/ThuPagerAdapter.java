package com.example.quanlythuchi.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.quanlythuchi.ThongKeFragment;
import com.example.quanlythuchi.fragment.FragmentKhoanThu;
import com.example.quanlythuchi.fragment.FragmentLoaiThu;

public class ThuPagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public ThuPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag = null;
        switch (i){
            case 0:
                frag = new FragmentKhoanThu();
                break;
            case 1:
                frag = new FragmentLoaiThu();
                break;
            default:
                return new ThongKeFragment();
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
                title = "Khoản Thu";
                break;
            case 1:
                title = "Loại Thu";
                break;
        }
        return title;
    }
}



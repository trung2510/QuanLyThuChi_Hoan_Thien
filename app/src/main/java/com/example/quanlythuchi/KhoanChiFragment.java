package com.example.quanlythuchi;


import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlythuchi.adapter.ChiPagerAdapter;

public class KhoanChiFragment extends Fragment {

    private TabLayout tabLayout;
    //private TabItem tabkhoanthu;
    //private TabItem tabloaithu;
    private ViewPager viewPager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoanchi, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        //tabkhoanthu = (TabItem) view.findViewById(R.id.tabkhoanthu);
        //tabloaithu = (TabItem) view.findViewById(R.id.tabloaithu);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        //liên kết tablayout với Viewpager
        tabLayout.setupWithViewPager(viewPager);

        ChiPagerAdapter pageAdapter = new ChiPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        //gắn viewPager với tablayout
        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        return view;


    }


}

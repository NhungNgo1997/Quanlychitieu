package com.ngokimnhung.quanlychitieu;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.ngokimnhung.quanlychitieu.FragmentAdapter.QuanLyThuAdapter;

public class QuanLyThu_Fragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    QuanLyThuAdapter adapter;
    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.quanlythu_layout, container, false);
        tabLayout=view.findViewById(R.id.tabqlthu);
        viewPager=view.findViewById(R.id.vpqlthu);
        adapter= new QuanLyThuAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;

    }


}

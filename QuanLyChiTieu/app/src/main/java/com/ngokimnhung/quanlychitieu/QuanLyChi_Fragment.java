package com.ngokimnhung.quanlychitieu;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ngokimnhung.quanlychitieu.FragmentAdapter.QuanLyChiAdapter;

public class QuanLyChi_Fragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    QuanLyChiAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.quanlychi_layout, container, false);

        tabLayout= view.findViewById(R.id.tabqlchi);
        viewPager= view.findViewById(R.id.vpqlchi);
        adapter= new QuanLyChiAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}

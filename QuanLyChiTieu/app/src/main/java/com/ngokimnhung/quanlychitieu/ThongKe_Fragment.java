package com.ngokimnhung.quanlychitieu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ngokimnhung.quanlychitieu.FragmentAdapter.QuanLyThongKeAdapter;
import com.ngokimnhung.quanlychitieu.FragmentAdapter.QuanLyThuAdapter;

public class ThongKe_Fragment extends Fragment{
    TabLayout tabLayout;
    ViewPager viewPager;
    QuanLyThongKeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_thongke__fragment, container, false);
        tabLayout=view.findViewById(R.id.tabthongke);
        viewPager=view.findViewById(R.id.vpthongke);
        adapter= new QuanLyThongKeAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}

package com.ngokimnhung.quanlychitieu.FragmentAdapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ngokimnhung.quanlychitieu.ThongKe.ThongKeNam_Fagment;
import com.ngokimnhung.quanlychitieu.ThongKe.ThongKeNgayChon_Fragment;
import com.ngokimnhung.quanlychitieu.ThongKe.ThongKeNgay_Fagment;
import com.ngokimnhung.quanlychitieu.ThongKe.ThongKeThang_Fagment;

public class QuanLyThongKeAdapter extends FragmentPagerAdapter{
    public QuanLyThongKeAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ThongKeNgay_Fagment();
            case 1:
                return new ThongKeThang_Fagment();
            case  2:
                return new ThongKeNam_Fagment();
            case 3:
                return new ThongKeNgayChon_Fragment();

            default:
                return new ThongKeNgay_Fagment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Ngày Này";
            case 1:
                return "Tháng Này";
            case 2:
                return "Năm Này";
            case 3:
                return "Chọn Ngày";
            default:
                return "Ngày Này";
        }
    }
}

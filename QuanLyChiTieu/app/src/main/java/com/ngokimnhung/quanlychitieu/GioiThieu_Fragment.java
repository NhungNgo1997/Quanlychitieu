package com.ngokimnhung.quanlychitieu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GioiThieu_Fragment extends Fragment {
    ListView lvgt;
    private ArrayAdapter arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gioi_thieu__fragment, container, false);

        lvgt=view.findViewById(R.id.lvgt);

        String[] values = new String[] { "Loại Bài: Android", "Tên Bài: Quản Lý Chi Tiêu", "Ngày xuất bản :13/03/2019","Cảm Ơn Bạn Đã Sử Dụng Money Manager" };
        arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        lvgt.setAdapter(arrayAdapter);
        return view;
    }
}
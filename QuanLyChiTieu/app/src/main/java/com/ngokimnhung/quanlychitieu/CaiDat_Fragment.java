package com.ngokimnhung.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CaiDat_Fragment extends Fragment {
    TextView tvten, tvmk;
    ImageView suaten, suamk;
    String username="";
    String password="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.caidat_fragment, container, false);
        tvten=view.findViewById(R.id.tvten);
        tvmk=view.findViewById(R.id.tvmk);
        suamk=view.findViewById(R.id.suamk);
        suamk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuaMatKhauActivity suaMatKhauActivity= new SuaMatKhauActivity();
                suaMatKhauActivity.show(getFragmentManager(),"dialog");
            }
        });
        Intent intent= getActivity().getIntent();
        username=intent.getStringExtra("username");
        password=intent.getStringExtra("password");
        tvten.setText(intent.getStringExtra("username"));
        tvmk.setText(intent.getStringExtra("password"));


        return view;
    }
}

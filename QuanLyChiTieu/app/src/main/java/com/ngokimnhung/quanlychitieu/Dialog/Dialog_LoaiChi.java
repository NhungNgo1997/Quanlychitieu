package com.ngokimnhung.quanlychitieu.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngokimnhung.quanlychitieu.DangKyActivity;
import com.ngokimnhung.quanlychitieu.R;

import java.util.HashMap;
import java.util.Map;

public class Dialog_LoaiChi extends DialogFragment{
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/themloaichi.php";
    Button btnhuyloaichi, btnthemloaichi;
    EditText editloaichi;
    String username;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_loaichi, container, false);
        btnhuyloaichi= view.findViewById(R.id.btnhuyloaichi);
        btnthemloaichi= view.findViewById(R.id.btnthemloaichi);
        editloaichi= view.findViewById(R.id.editloaichi);
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("username")){
            username = intent.getStringExtra("username");
            Log.e("USERNAME", username);
        }
        btnthemloaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloaithu= editloaichi.getText().toString().trim();
                if(tenloaithu.isEmpty()){

                    Toast.makeText(getActivity(),"Không được bỏ trống",Toast.LENGTH_SHORT).show();
                }else {
                    them(url);
                }
            }
        });

        btnhuyloaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }
    public void them(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {

                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getActivity(), "Tên này đã tồn tại", Toast.LENGTH_SHORT).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Vui lòng kiểm tra kết nối internet của bạn"+error.toString(), Toast.LENGTH_SHORT).show();

                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("tenloaichi", editloaichi.getText().toString().trim());
                params.put("username", username.toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}


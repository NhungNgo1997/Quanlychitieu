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

public class Dialog_LoaiThu extends DialogFragment{
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/themloaithu.php";
    Button btnhuyloaithu, btnthemloaithu;
    EditText editloaithu;
    String username;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_loaithu, container, false);
        btnhuyloaithu= view.findViewById(R.id.btnhuyloaithu);
        btnthemloaithu= view.findViewById(R.id.btnthemloaithu);
        editloaithu= view.findViewById(R.id.editloaithu);
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("username")){
            username = intent.getStringExtra("username");
            Log.e("USERNAME", username);
        }
        btnthemloaithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloaithu= editloaithu.getText().toString().trim();
                if(tenloaithu.isEmpty()){

                    Toast.makeText(getActivity(),"Vui lòng nhập đầy đủ",Toast.LENGTH_SHORT).show();
                }else {
                   them(url);
                }
            }
        });

        btnhuyloaithu.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(getActivity(), "Vui lòng kiểm tra kết nối internet của bạn", Toast.LENGTH_SHORT).show();

                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("tenloaithu", editloaithu.getText().toString().trim());
                params.put("username", username.toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}


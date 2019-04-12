package com.ngokimnhung.quanlychitieu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngokimnhung.quanlychitieu.Adapter.KhoanChi;
import com.ngokimnhung.quanlychitieu.Adapter.KhoanThu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SuaMatKhauActivity extends DialogFragment {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/suamatkhau.php";
    EditText editmkcu, editmkmoi ;
    Button btnhuy, btnsua;


    String username="";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.suamatkhau_activity, container, false);

        editmkcu=view.findViewById(R.id.editmkcu);
        editmkmoi=view.findViewById(R.id.editmkmoi);
        btnhuy=view.findViewById(R.id.btnhuy);
        btnsua=view.findViewById(R.id.btnsua);
        Intent intent=getActivity().getIntent();
        if (intent.hasExtra("username")){
            username = intent.getStringExtra("username");
            Log.e("USERNAME", username);
        }

        onClick();
        return view;
    }

    public void onClick(){


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mkcu= editmkcu.getText().toString().trim();
                String mkmoi=editmkmoi.getText().toString().trim();

                if(mkcu.isEmpty()||mkmoi.isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }else {
                    suamk(url);
                }
            }
        });
    }
    public void suamk(final String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {

                    Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(getActivity(), "Lỗi"+response.toString(), Toast.LENGTH_SHORT).show();

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

                params.put("password", editmkcu.getText().toString().trim());
                params.put("matkhaumoi", editmkmoi.getText().toString().trim());
                // params.put("loaithu", spinner.toString().trim());
                params.put("username",username.toString().trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }
}

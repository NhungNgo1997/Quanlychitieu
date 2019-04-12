package com.ngokimnhung.quanlychitieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.ngokimnhung.quanlychitieu.Adapter.LoaiChi;
import com.ngokimnhung.quanlychitieu.Adapter.Xuly;

import java.util.HashMap;
import java.util.Map;

public class SuaLoaiChiActivity extends AppCompatActivity {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/sualoaichi.php";
    EditText editloaichi;
    Button btnhuyloaichi,btnsualoaichi;
    int id=0;
    String username="";
    LoaiChi loaiChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sualoaichi);
        anhxa();
        Intent intent= getIntent();
        if (intent.hasExtra("username")){
            username = intent.getStringExtra("username");
            Log.e("USERNAME", username);
        }
        loaiChi=(LoaiChi) intent.getSerializableExtra("dataLoaichi");
        id=loaiChi.getId_loaichi();
        editloaichi.setText(loaiChi.getTenloaichi());
        btnsualoaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloaichi= editloaichi.getText().toString().trim();
                if(tenloaichi.isEmpty()){

                    Toast.makeText(SuaLoaiChiActivity.this,"Vui lòng nhập đầy đủ",Toast.LENGTH_SHORT).show();
                }else {
                    Capnhat(url);
                }
            }
        });

        btnhuyloaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void Capnhat(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(SuaLoaiChiActivity.this,"Sửa thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SuaLoaiChiActivity.this, "Không thể sửa trường này vì tồn tại trong khoản chi",Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SuaLoaiChiActivity.this, "Vui lòng kiểm tra kết nối internet của bạn", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("id_loaichi",String.valueOf(id));
                params.put("tenloaichi",editloaichi.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void anhxa(){
        editloaichi=findViewById(R.id.editsualoaichi);
        btnhuyloaichi=findViewById(R.id.btnhuysualoaichi);
        btnsualoaichi=findViewById(R.id.btnsualoaichi);
    }
}


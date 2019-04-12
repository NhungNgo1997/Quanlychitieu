package com.ngokimnhung.quanlychitieu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.ngokimnhung.quanlychitieu.Adapter.Xuly;

import java.util.HashMap;
import java.util.Map;

public class SuaLoaiThuActivity extends AppCompatActivity {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/sualoaithu.php";
    EditText editloaithu;
    Button btnhuyloaithu,btnsualoaithu;
    int id=0;
    String username="";
    Xuly xuly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sualoaithu);
        anhxa();
        Intent intent= getIntent();
        if (intent.hasExtra("username")){
            username = intent.getStringExtra("username");
            Log.e("USERNAME", username);
        }
        xuly=(Xuly) intent.getSerializableExtra("dataLoaithu");
        id=xuly.getId_loaithu();
        editloaithu.setText(xuly.getTenloaithu());
        btnsualoaithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloaithu= editloaithu.getText().toString().trim();
                if(tenloaithu.isEmpty()){

                    Toast.makeText(SuaLoaiThuActivity.this,"Vui lòng nhập đầy đủ",Toast.LENGTH_SHORT).show();
                }else {
                    Capnhat(url);
                }
            }
        });

        btnhuyloaithu.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(SuaLoaiThuActivity.this,"Sửa thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SuaLoaiThuActivity.this, "Không thể sửa trường này vì tồn tại trong khoản thu",Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SuaLoaiThuActivity.this, "Vui lòng kiểm tra kết nối internet của bạn", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("id_loaithu",String.valueOf(id));
                params.put("tenloaithu",editloaithu.getText().toString().trim());
             
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void anhxa(){
        editloaithu=findViewById(R.id.editsualoaithu);
        btnhuyloaithu=findViewById(R.id.btnhuysualoaithu);
        btnsualoaithu=findViewById(R.id.btnsualoaithu);
    }
}


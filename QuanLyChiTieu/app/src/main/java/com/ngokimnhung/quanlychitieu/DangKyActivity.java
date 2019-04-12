package com.ngokimnhung.quanlychitieu;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

import static java.lang.System.in;

public class DangKyActivity extends AppCompatActivity {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/dangky.php";

    EditText edten, edmk;
    Button btndangky,btnhuy ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_item);
        anhxa();
        onClick();
    }
    public void onClick(){

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= edten.getText().toString().trim();
                String password=edmk.getText().toString().trim();
                if(username.isEmpty()|| password.isEmpty()){
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();


                }
                else{
                    dangky(url);
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void dangky(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(DangKyActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {

                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(DangKyActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(DangKyActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangKyActivity.this, "Vui lòng kiểm tra kết nối internet của bạn"+error.toString(), Toast.LENGTH_SHORT).show();

                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("username", edten.getText().toString().trim());
                params.put("password", edmk.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void anhxa(){
        edten= findViewById(R.id.ten);
        edmk=findViewById(R.id.mk);
        btnhuy=findViewById(R.id.huydangky);
        btndangky=findViewById(R.id.dangky);
    }
}

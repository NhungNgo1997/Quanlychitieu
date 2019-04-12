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

public class MainActivity extends AppCompatActivity {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/dangnhap.php";

    EditText editusername, editmk;
    Button btndangnhap, btndangky;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        onClick();
    }
    public void onClick(){
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent= new Intent(MainActivity.this, DangKyActivity.class);
            startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= editusername.getText().toString().trim();
                String password=editmk.getText().toString().trim();
                if(username.isEmpty()|| password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();


                }
                else{
                    progressBar =new ProgressDialog(MainActivity.this);
                    progressBar.setMessage("Đang đăng nhập vui lòng đợi");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressBar.setIndeterminate(false);
                    progressBar.show();
                    dangnhap(url);
                }
            }
        });

    }

    public void dangnhap(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    progressBar.dismiss();
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MainActivity.this, ThongKeActivity.class);
                    intent.putExtra("username",editusername.getText().toString().trim());
                    intent.putExtra("password",editmk.getText().toString().trim());
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng kiểm tra lại tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    progressBar.dismiss();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Vui lòng kiểm tra kết nối internet của bạn"+error.toString(), Toast.LENGTH_SHORT).show();

                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("username", editusername.getText().toString().trim());
                params.put("password", editmk.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
    private void anhxa(){
        editusername= findViewById(R.id.edtusename);
        editmk=findViewById(R.id.edtmk);
        btndangnhap=findViewById(R.id.btndangnhap);
        btndangky=findViewById(R.id.btndangki);
    }
}

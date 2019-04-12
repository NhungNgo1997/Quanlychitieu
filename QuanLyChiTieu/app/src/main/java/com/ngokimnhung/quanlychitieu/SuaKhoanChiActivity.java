package com.ngokimnhung.quanlychitieu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SuaKhoanChiActivity extends AppCompatActivity {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/suakhoanchi.php";
    String url2="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/layloaichi.php";
    Spinner spinner;
    EditText editsotien, editkhoanchi , tvngay;
    Button btnthemkhoanchi, btnhuykhoanchi;
    KhoanChi khoanChi;
    int id=0;
    ArrayList<String> Loaichi;

    String username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suakhoanchi);
        anhxa();
        Loaichi=new ArrayList<>();
        layloaichi(url2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String thu=spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                Toast.makeText(SuaKhoanChiActivity.this,thu,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Intent intent= getIntent();

        khoanChi=(KhoanChi) intent.getSerializableExtra("datakhoanchi");
        id=khoanChi.getId_khoanchi();
        username=khoanChi.getUsername();
        editkhoanchi.setText(khoanChi.getTenkhoanchi());
        editsotien.setText(khoanChi.getSotienchi()+"");

        tvngay.setText(khoanChi.getNgaythangchi());

        onClick();
    }
    private void layloaichi(String url2){
        RequestQueue requestQueue1=Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0; i<array.length();i++){
                        JSONObject object= array.getJSONObject(i);
                        String thu=object.getString("tenloaichi");
                        Loaichi.add(thu);
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(SuaKhoanChiActivity.this,android.R.layout.simple_spinner_dropdown_item,Loaichi));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("username",username.toString().trim());
                return params;
            }
        };
        requestQueue1.add(stringRequest);
    }
    private void anhxa(){
        tvngay= findViewById(R.id.editsuangay);
        editsotien=findViewById(R.id.editsuasotien);
        editkhoanchi=findViewById(R.id.editsuakhoanchi);
         spinner=findViewById(R.id.spkhoanchi);
        btnthemkhoanchi=findViewById(R.id.btnsuakhoanchi);
        btnhuykhoanchi=findViewById(R.id.btnhuysuakhoanchi);
    }
    public void onClick(){
        tvngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar cal=Calendar.getInstance();
                int Year =cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.MONTH);
                int Day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog date=new DatePickerDialog(SuaKhoanChiActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i1, int i2, int i3) {
                        cal.set(i1,i2,i2);

                        tvngay.setText(i1+"/"+(i2+1)+"/"+i3);

                    }


                }, Year, Month, Day);

                date.show();

            }
        });

        btnhuykhoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        btnthemkhoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngay=tvngay.getText().toString().trim();

                String tenkhoanchi= editkhoanchi.getText().toString().trim();
                String sotienchi=editsotien.getText().toString().trim();
                //String loaithu=spinner.toString().trim();

                if(tenkhoanchi.isEmpty()||sotienchi.isEmpty()||ngay.isEmpty()){
                    Toast.makeText(SuaKhoanChiActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }else {
                    themkhoanchi(url);
                }
            }
        });
    }
    public void themkhoanchi(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(SuaKhoanChiActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {

                    Toast.makeText(SuaKhoanChiActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(SuaKhoanChiActivity.this, "Lỗi"+response.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SuaKhoanChiActivity.this, "Vui lòng kiểm tra kết nối internet của bạn", Toast.LENGTH_SHORT).show();

                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("id_khoanchi",String.valueOf(id));
                params.put("tenkhoanchi", editkhoanchi.getText().toString().trim());
                params.put("sotienchi", editsotien.getText().toString().trim());
                 params.put("loaichi", spinner.getSelectedItem().toString().trim());
                params.put("ngaythangchi",tvngay.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }
}

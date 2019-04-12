package com.ngokimnhung.quanlychitieu.ThongKe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
import com.ngokimnhung.quanlychitieu.Adapter.TKKhoanChiAdapter;
import com.ngokimnhung.quanlychitieu.Adapter.TKKhoanThuAdapter;
import com.ngokimnhung.quanlychitieu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThongKeNgayChon_Fragment extends Fragment {
    public static final String URL_TONG="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/";
    String url=URL_TONG+"laykhoanchingaychon.php";
    String url1=URL_TONG+"laykhoanthungaychon.php";
    String url2=URL_TONG+"tongthungaychon.php";
    String url3=URL_TONG+"tongchingaychon.php";

    EditText editchon;
    Button btnchon;
    ListView lvtkchi,lvktthu;
    TextView tongthu, tongchi,tvsodu;

    String username="";
    ArrayList<KhoanChi> arraykhoanchi;
    TKKhoanChiAdapter adapter;
    ArrayList<KhoanThu> arraykhoanthu;
    TKKhoanThuAdapter adapter1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thongkengaychon_fragment, container, false);
        editchon= view.findViewById(R.id.editchon);
        btnchon=view.findViewById(R.id.btnchon);
        lvtkchi=view.findViewById(R.id.lvtkchi);
        lvktthu=view.findViewById(R.id.lvtkthu);
        tongthu=view.findViewById(R.id.tongthu);
        tongchi=view.findViewById(R.id.tongchi);


        Intent intent= getActivity().getIntent();
        if(intent.hasExtra("username")){
            username=intent.getStringExtra("username");
        }
        onClick();
        return view;
    }
    public void onClick(){
        editchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar cal=Calendar.getInstance();
                int Year =cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.MONTH);
                int Day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog date=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i1, int i2, int i3) {
                        cal.set(i1,i2,i2);

                        editchon.setText(i1+"/"+(i2+1)+"/"+i3);

                    }


                }, Year, Month, Day);

                date.show();

            }
        });
        btnchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arraykhoanthu= new ArrayList<>();
                arraykhoanchi= new ArrayList<>();
                adapter= new TKKhoanChiAdapter(getActivity(),R.layout.thongkekhoan_item,arraykhoanchi);
                adapter1= new TKKhoanThuAdapter(getActivity(),R.layout.thongkekhoan_item,arraykhoanthu);
                lvktthu.setAdapter(adapter1);
                lvtkchi.setAdapter(adapter);
                laykhoanchi(url);
                laykhoanthu(url1);
                laytongthu(url2);
                laytongchi(url3);

            }
        });
    }

    private void laytongchi(String url3){
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tongchi.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Vui lòng kiểm tra kết nối internet",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("username",username.toString().trim());
                params.put("ngaythangchi",editchon.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void laytongthu(String url2){
        RequestQueue requestQueue =Volley.newRequestQueue(getActivity());
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    tongthu.setText(response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(),"Vui lòng kiểm tra kết nối internet",Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("username",username.toString().trim());
                    params.put("ngaythangthu",editchon.getText().toString().trim());
                    return params;
                }
            };

        requestQueue.add(stringRequest);
    }
    private void laykhoanchi(String url){
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array= new JSONArray(response);
                    for(int i=0; i<array.length();i++){
                        JSONObject object= array.getJSONObject(i);
                        arraykhoanchi.add(new KhoanChi(
                                object.getInt("id_khoanchi"),
                                object.getString("tenkhoanchi"),
                                object.getString("username"),
                                object.getString("loaichi"),
                                object.getInt("sotienchi"),
                                object.getString("ngaythangchi")
                        ));
                    }
                    adapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Vui lòng kiểm tra kết nối internet",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("username", username.toString().trim());
                params.put("ngaythangchi",editchon.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void laykhoanthu(String url1){
        final RequestQueue requestQueue1= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        arraykhoanthu.add(new KhoanThu(
                                object.getInt("id_khoanthu"),
                                object.getString("tenkhoanthu"),
                                object.getString("username"),
                                object.getInt("sotienthu"),
                                object.getString("loaithu"),
                                object.getString("ngaythangthu")

                        ));
                    }
                    adapter1.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();


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
                params.put("username", username.toString().trim());
                params.put("ngaythangthu",editchon.getText().toString().trim());

                return params;
            }
        };


        requestQueue1.add(stringRequest);
    }
}

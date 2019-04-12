package com.ngokimnhung.quanlychitieu.Dialog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngokimnhung.quanlychitieu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Dialog_KhoanChi extends DialogFragment {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/themkhoanchi.php";
    String url2="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/layloaichi.php";
    TextView tvngay;
    Spinner spinner;
    EditText editsotien, editkhoanchi;
    Button btnthemkhoanchi, btnhuykhoanchi;
    ImageView chon;
    String username;
    ArrayList<String>  Loaichi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.dialog_khoanchi, container, false);
        tvngay= view.findViewById(R.id.editngay);

        editsotien= view.findViewById(R.id.editsotien);
        editkhoanchi= view.findViewById(R.id.editkhoanchi);
        btnhuykhoanchi= view.findViewById(R.id.btnhuykhoanchi);
        btnthemkhoanchi= view.findViewById(R.id.btnthemkhoanchi);
        Loaichi= new ArrayList<>();
        spinner= view.findViewById(R.id.spkhoanchi);
        layloaichi(url2);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String chi=spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                Toast.makeText(getActivity(),chi,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("username")){
            username = intent.getStringExtra("username");
            Log.e("USERNAME", username);
        }

        onClick();
        return view;
    }
    private void layloaichi(String url2){
        final RequestQueue requestQueue1= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        String chi=object.getString("tenloaichi");
                        Loaichi.add(chi);
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item, Loaichi));
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


                return params;
            }
        };


        requestQueue1.add(stringRequest);
    }



    public void onClick(){
        tvngay.setOnClickListener(new View.OnClickListener() {
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

                        tvngay.setText(i1+"/"+(i2+1)+"/"+i3);

                    }


                }, Year, Month, Day);

                date.show();

            }
        });
        btnhuykhoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();

            }
        });
        btnthemkhoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngay=tvngay.getText().toString().trim();

                String tenkhoanchi= editkhoanchi.getText().toString().trim();
                String sotienchi=editsotien.getText().toString().trim();
                String loaichi=spinner.toString().trim();

                if(tenkhoanchi.isEmpty()||sotienchi.isEmpty()||loaichi.isEmpty()||ngay.isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }else {
                    themkhoanchi(url);
                }
            }
        });
    }
    public void themkhoanchi(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {

                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();


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
                params.put("tenkhoanchi", editkhoanchi.getText().toString().trim());
                params.put("username", username.toString().trim());
                params.put("sotienchi", editsotien.getText().toString().trim());
                params.put("loaichi", spinner.getSelectedItem().toString().trim());
                params.put("ngaythangchi",tvngay.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

}

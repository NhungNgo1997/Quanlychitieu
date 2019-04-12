package com.ngokimnhung.quanlychitieu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngokimnhung.quanlychitieu.Adapter.Xuly;
import com.ngokimnhung.quanlychitieu.Adapter.XulyAdapter;
import com.ngokimnhung.quanlychitieu.Dialog.Dialog_LoaiThu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoaiThu_Fragment extends Fragment {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/layloaithu.php";

    FloatingActionButton floatloaithu;
    ListView lvloaithu;
    ArrayList<Xuly> arrayXuly;
    XulyAdapter adapter;

    String username="";
    int id=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.loaithu__fragment, container, false);
        floatloaithu=view.findViewById(R.id.floatloaithu);
        lvloaithu=view.findViewById(R.id.lvloaithu);
        arrayXuly= new ArrayList<>();
        adapter= new XulyAdapter(getActivity(), R.layout.loai_item, arrayXuly);
        lvloaithu.setAdapter(adapter);

        layloaithu(url);

        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("username")){
            username = intent.getStringExtra("username");
            Log.e("USERNAME", username);
        }



        floatloaithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_LoaiThu dialog_loaiThu= new Dialog_LoaiThu();
                dialog_loaiThu.show(getFragmentManager(), "dialog");
            }
        });

        return view;
    }

    private void layloaithu(String url){
        final RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        arrayXuly.add(new Xuly(
                                object.getInt("id_loaithu"),
                                object.getString("tenloaithu")

                                                ));
                    }
                    adapter.notifyDataSetChanged();
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


        requestQueue.add(stringRequest);
        }

}


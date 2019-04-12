package com.ngokimnhung.quanlychitieu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngokimnhung.quanlychitieu.Adapter.KhoanChi;
import com.ngokimnhung.quanlychitieu.Adapter.KhoanChiAdapter;
import com.ngokimnhung.quanlychitieu.Dialog.Dialog_KhoanChi;
import com.ngokimnhung.quanlychitieu.Dialog.Dialog_KhoanThu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KhoanChi_Fragment extends Fragment {
    String url="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/laykhoanchi.php";
    FloatingActionButton floatthem;
    ListView lvchi;
    String username="";
    ArrayList<KhoanChi> arraykhoanchi;
    KhoanChiAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.khoanchi__fragment, container, false);
        floatthem= view.findViewById(R.id.floatchi);
        lvchi=view.findViewById(R.id.lvchi);
        arraykhoanchi= new ArrayList<>();
        adapter= new KhoanChiAdapter(getActivity(),R.layout.khoan_item,arraykhoanchi);
        lvchi.setAdapter(adapter);
        laykhoanchi(url);
        Intent intent= getActivity().getIntent();
        if(intent.hasExtra("username")){
            username=intent.getStringExtra("username");
        }

        floatthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_KhoanChi dialog_khoanChi = new Dialog_KhoanChi();
                dialog_khoanChi.show(getFragmentManager(), "dialog");


            }
        });
        return  view;
    }
    private void laykhoanchi(String url){
        final RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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

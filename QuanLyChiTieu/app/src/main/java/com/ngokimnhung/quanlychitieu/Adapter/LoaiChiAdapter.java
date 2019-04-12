package com.ngokimnhung.quanlychitieu.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ngokimnhung.quanlychitieu.LoaiChi_Fragment;
import com.ngokimnhung.quanlychitieu.R;
import com.ngokimnhung.quanlychitieu.Adapter.Xuly;
import com.ngokimnhung.quanlychitieu.SuaLoaiChiActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoaiChiAdapter extends BaseAdapter {
    String urldelete="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/xoaloaichi.php";
    private Context context;
    int layout;
    List<LoaiChi> loaichiList;

    public LoaiChiAdapter(Context context,int layout,List<LoaiChi> loaichiList){
        this.context=context;
        this.layout=layout;
        this.loaichiList=loaichiList;
    }
    @Override
    public int getCount() { return loaichiList.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView tvten;
        ImageView imgxoa,imgghi;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);

            viewHolder.tvten=convertView.findViewById(R.id.tvten);
            viewHolder.imgghi=convertView.findViewById(R.id.ghi);
            viewHolder.imgxoa=convertView.findViewById(R.id.xoa);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        final LoaiChi loaiChi=loaichiList.get(i);
        viewHolder.tvten.setText(loaiChi.getTenloaichi());
        viewHolder.imgghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, SuaLoaiChiActivity.class);
                intent.putExtra("dataLoaichi",loaiChi);
                context.startActivity(intent);
            }
        });
        viewHolder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Xacnhanxoa(loaiChi.getTenloaichi(), loaiChi.getId_loaichi());
            }
        });

        return convertView;
    }
    public void Xacnhanxoa(String ten, final int id){
        AlertDialog.Builder dialogxoa= new AlertDialog.Builder(context);
        dialogxoa.setMessage("Bạn có muốn xóa "+ten+" không");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete(id);
            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
    public void delete(final int id){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, urldelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(context, "Xóa thành công",Toast.LENGTH_SHORT).show();



                }
                else {
                    Toast.makeText(context, "Không thể xóa trường này vì tồn tại trong khoản chi",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Vui lòng kiểm tra kết nối internet",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params=new HashMap<>();
                params.put("id",String.valueOf(id));
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }


}

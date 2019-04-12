package com.ngokimnhung.quanlychitieu.Adapter;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
import com.ngokimnhung.quanlychitieu.R;
import com.ngokimnhung.quanlychitieu.SuaKhoanChiActivity;
import com.ngokimnhung.quanlychitieu.SuaKhoanThuActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KhoanChiAdapter extends BaseAdapter {
    String urldelete="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/xoakhoanchi.php";
    private Context context;
    int layout;
    List<KhoanChi> listkhoanchi;

    public KhoanChiAdapter(Context context, int layout, List<KhoanChi> listkhoanchi) {
        this.context = context;
        this.layout = layout;
        this.listkhoanchi = listkhoanchi;
    }

    @Override
    public int getCount() {
        return listkhoanchi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtkhoan,txtgia,txtngay;
        ImageView ghi,xoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.khoan_item,null);
            viewHolder.txtkhoan=(TextView) view.findViewById(R.id.tvkhoan);
            viewHolder.txtgia=(TextView) view.findViewById(R.id.tvgia);
            viewHolder.txtngay=(TextView) view.findViewById(R.id.tvngay);
            viewHolder.ghi=(ImageView) view.findViewById(R.id.imgghi);
            viewHolder.xoa=(ImageView) view.findViewById(R.id.imgxoa);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final KhoanChi khoanChi= listkhoanchi.get(i);
        viewHolder.txtkhoan.setText(khoanChi.getTenkhoanchi());
        viewHolder.txtgia.setText(""+khoanChi.getSotienchi());
        viewHolder.txtngay.setText(khoanChi.getNgaythangchi());
        viewHolder.ghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, SuaKhoanChiActivity.class);
                intent.putExtra("datakhoanchi",khoanChi);
                context.startActivity(intent);
            }
        });
        viewHolder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Xacnhanxoa(khoanChi.getTenkhoanchi(), khoanChi.getId_khoanchi());

            }
        });


        return view;
    }
    public void Xacnhanxoa(String ten, final int id){
        AlertDialog.Builder dialogxoa=new AlertDialog.Builder(context);
        dialogxoa.setMessage("Bạn có đồng ý xóa "+ten+" không");
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
                    Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(context,"Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Vui lòng kiểm tra internet", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}

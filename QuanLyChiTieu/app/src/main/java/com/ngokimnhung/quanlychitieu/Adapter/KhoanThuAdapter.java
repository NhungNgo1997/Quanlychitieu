package com.ngokimnhung.quanlychitieu.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
import com.ngokimnhung.quanlychitieu.SuaKhoanThuActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KhoanThuAdapter extends BaseAdapter {
    String urldelete="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/xoakhoanthu.php";
    private Context context;
    int layout;
    List<KhoanThu> listkhoanthu;

    TextView txtkhoan,txtgia,txtngay;
    ImageView imgghi,imgxoa;

    public KhoanThuAdapter(Context context, int layout, List<KhoanThu> listkhoanthu) {
        this.context = context;
        this.layout = layout;
        this.listkhoanthu = listkhoanthu;
    }

    @Override
    public int getCount() {
        return listkhoanthu.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{ }


    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertview==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview=inflater.inflate(R.layout.khoan_item,null);
            txtkhoan= convertview.findViewById(R.id.tvkhoan);
            txtgia= convertview.findViewById(R.id.tvgia);
            txtngay= convertview.findViewById(R.id.tvngay);
            imgghi=convertview.findViewById(R.id.imgghi);
            imgxoa=convertview.findViewById(R.id.imgxoa);
            convertview.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertview.getTag();
        }
        final KhoanThu khoanThu= listkhoanthu.get(i);
        txtkhoan.setText(khoanThu.getTenkhoanthu());
        txtgia.setText(khoanThu.getSotienthu()+"");
        txtngay.setText(khoanThu.getNgaythangthu());
        imgghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, SuaKhoanThuActivity.class);
                intent.putExtra("datakhoanthu",khoanThu);
                context.startActivity(intent);
            }
        });
        imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Xacnhanxoa(khoanThu.getTenkhoanthu(), khoanThu.getId_khoanthu());
            }
        });

// show loi ra cho t

        return convertview;
    }
    public void Xacnhanxoa(String ten, final int id){
        AlertDialog.Builder dialodxoa= new AlertDialog.Builder(context);
        dialodxoa.setMessage("Bạn có muốn xóa "+ten+" không");
        dialodxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete(id);
            }
        });
        dialodxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialodxoa.show();
    }
    public void delete(final int id){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, urldelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(context,"Xóa thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối internet", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("id",String.valueOf(id));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}

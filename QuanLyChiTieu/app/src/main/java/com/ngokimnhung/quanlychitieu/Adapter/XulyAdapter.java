package com.ngokimnhung.quanlychitieu.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.ngokimnhung.quanlychitieu.LoaiThu_Fragment;
import com.ngokimnhung.quanlychitieu.R;
import com.ngokimnhung.quanlychitieu.SuaLoaiThuActivity;

//import com.ngokimnhung.quanlychitieu.SuaLoaiThuActivity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class XulyAdapter extends BaseAdapter {
    String urldelete="http://quanlychitieuntknhung.000webhostapp.com/qlchitieu/xoaloaithu.php";
//    private LoaiThu_Fragment fragment;
    private Context context;
    int layout;
    List<Xuly> xulyList;

    public XulyAdapter(Context context,int layout,List<Xuly> xulyList){
       // this.fragment=fragment;
        this.context=context;
        this.layout=layout;
        this.xulyList=xulyList;
    }
    @Override
    public int getCount() { return xulyList.size();
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
        TextView tvten;
        ImageView imgxoa,imgghi;

    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);

            viewHolder.tvten=convertView.findViewById(R.id.tvten);
            viewHolder.imgghi=convertView.findViewById(R.id.ghi);
            viewHolder.imgxoa=convertView.findViewById(R.id.xoa);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        final Xuly xuly=xulyList.get(i);
        viewHolder.tvten.setText(xuly.getTenloaithu());
        viewHolder.imgghi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, SuaLoaiThuActivity.class);
                intent.putExtra("dataLoaithu",xuly);
                context.startActivity(intent);
            }
        });
        viewHolder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Xacnhanxoa(xuly.getTenloaithu(), xuly.getId_loaithu());

            }
        });

        return convertView;
    }
    private void Xacnhanxoa(String ten, final int id){
        AlertDialog.Builder dialogxoa= new AlertDialog.Builder(context);
        dialogxoa.setMessage("Bạn có muốn xóa " + ten + " không");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                LoaiThu_Fragment fragment= new LoaiThu_Fragment();
//                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
//                fragment.delete(id);
//                ft.add(R.id.framelayout, fragment).commit();
          //  LoaiThu_Fragment.delete(id);

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

    public  void delete(final int id){
        final RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urldelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Không thể xóa trường này vì tồn tại trong khoản thu", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "xảy ra lỗi", Toast.LENGTH_SHORT).show();
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

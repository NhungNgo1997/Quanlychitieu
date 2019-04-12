package com.ngokimnhung.quanlychitieu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ngokimnhung.quanlychitieu.R;

import java.util.List;

public class TKKhoanChiAdapter extends BaseAdapter {
    private Context context;
    int layout;
    List<KhoanChi> listkhoanchi;

    public TKKhoanChiAdapter(Context context, int layout, List<KhoanChi> listkhoanchi) {
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
            view=inflater.inflate(layout,null);
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
        KhoanChi khoanChi= listkhoanchi.get(i);
        viewHolder.txtkhoan.setText(khoanChi.getTenkhoanchi());
        viewHolder.txtgia.setText(""+khoanChi.getSotienchi());
        viewHolder.txtngay.setText(khoanChi.getNgaythangchi());


        return view;
    }
}

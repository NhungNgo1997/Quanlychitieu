package com.ngokimnhung.quanlychitieu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngokimnhung.quanlychitieu.R;
import com.ngokimnhung.quanlychitieu.SuaKhoanThuActivity;

import java.util.List;

public class TKKhoanThuAdapter extends BaseAdapter {
    private Context context;
    int layout;
    List<KhoanThu> listkhoanthu;

    TextView txtkhoan,txtgia,txtngay;
    ImageView imgghi,imgxoa;

    public TKKhoanThuAdapter(Context context, int layout, List<KhoanThu> listkhoanthu) {
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
            convertview=inflater.inflate(layout,null);
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


// show loi ra cho t

        return convertview;
    }

}

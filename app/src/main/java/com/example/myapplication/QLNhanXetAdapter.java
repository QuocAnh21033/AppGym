package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class QLNhanXetAdapter extends BaseAdapter {
    private QLNhanXetActivity context;
    private  int layout;
    private ArrayList<DanhGia> danhGias;

    public QLNhanXetAdapter(QLNhanXetActivity context, int layout, ArrayList<DanhGia> danhGias) {
        this.context = context;
        this.layout = layout;
        this.danhGias = danhGias;
    }

    @Override
    public int getCount() {
        return danhGias.size();
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
        TextView txtTen,txtNoidung;
        ImageView imgDelete;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        QLNhanXetAdapter.ViewHolder holder;
        if (view == null) {
            holder = new QLNhanXetAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTen = (TextView) view.findViewById(R.id.txtTen);
            holder.txtNoidung = (TextView) view.findViewById(R.id.txtNoidung);
            holder.imgDelete=(ImageView) view.findViewById(R.id.img_Delete);
            view.setTag(holder);
        } else {
            holder = (QLNhanXetAdapter.ViewHolder) view.getTag();
        }

        DanhGia danhGia = danhGias.get(i);
        holder.txtTen.setText(danhGia.getFullname());
        holder.txtNoidung.setText(danhGia.getNoidung());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.XoaNhanXet(
                        danhGia.getId(),
                        danhGia.getFullname()
                );
            }
        });
        return view;
    }
}

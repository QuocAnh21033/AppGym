package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class DanhGiaAdapter extends BaseAdapter {
    private DanhgiaActivity context;
    private  int layout;
    private ArrayList<DanhGia> danhGias;

    public DanhGiaAdapter(DanhgiaActivity context, int layout, ArrayList<DanhGia> danhGias) {
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
        DanhGiaAdapter.ViewHolder holder;
        if (view == null) {
            holder = new DanhGiaAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTen = (TextView) view.findViewById(R.id.txtTen);
            holder.txtNoidung = (TextView) view.findViewById(R.id.txtNoidung);
            holder.imgDelete=(ImageView) view.findViewById(R.id.img_Delete);
            view.setTag(holder);
        } else {
            holder = (DanhGiaAdapter.ViewHolder) view.getTag();
        }

        DanhGia danhGia = danhGias.get(i);
        holder.txtTen.setText(danhGia.getFullname());
        holder.txtNoidung.setText(danhGia.getNoidung());


        return view;
    }
}

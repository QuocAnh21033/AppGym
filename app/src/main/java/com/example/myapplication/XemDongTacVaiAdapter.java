package com.example.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class XemDongTacVaiAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<DongTac> dongTacList;

    public XemDongTacVaiAdapter(Context context, int layout, List<DongTac> dongTacList) {
        this.context = context;
        this.layout = layout;
        this.dongTacList = dongTacList;
    }
    @Override
    public int getCount() {
        return dongTacList.size();
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
        TextView txtTen,txtBuoc1,txtBuoc2,txtBuoc3;
        ImageView imgHinhDT,imgEdit,imgDelete;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        XemDongTacVaiAdapter.ViewHolder holder;
        if (view == null) {
            holder = new XemDongTacVaiAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTen = (TextView) view.findViewById(R.id.tvTenDT);
            holder.txtBuoc1 = (TextView) view.findViewById(R.id.tvBuoc1);
            holder.txtBuoc2 = (TextView) view.findViewById(R.id.tvBuoc2);
            holder.txtBuoc3 = (TextView) view.findViewById(R.id.tvBuoc3);
            holder.imgHinhDT = (ImageView) view.findViewById(R.id.imgHinhDT);
            holder.imgDelete = (ImageView) view.findViewById(R.id.img_Delete);
            holder.imgEdit = (ImageView) view.findViewById(R.id.img_Edit);
            view.setTag(holder);
        } else {
            holder = (XemDongTacVaiAdapter.ViewHolder) view.getTag();
        }
        DongTac dongTac = dongTacList.get(i);
        holder.txtTen.setText(dongTac.getTen());
        holder.txtBuoc1.setText(dongTac.getBuoc1());
        holder.txtBuoc2.setText(dongTac.getBuoc2());
        holder.txtBuoc3.setText(dongTac.getBuoc3());
        //chuyển byte[] sang bitmap
        byte[] HinhAnh = dongTac.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(HinhAnh, 0, HinhAnh.length);
        holder.imgHinhDT.setImageBitmap(bitmap);
        return view;
    }
}
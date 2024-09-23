package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CaNhanAdapter extends BaseAdapter {
    private CaNhanActivity context;
    private  int layout;
    private ArrayList<CaNhan> caNhans;

    public CaNhanAdapter(CaNhanActivity context, int layout, ArrayList<CaNhan> caNhans) {
        this.context = context;
        this.layout = layout;
        this.caNhans = caNhans;
    }

    @Override
    public int getCount() {
        return caNhans.size();

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
        TextView txtId,txtTK,txtMK,txtFullname,txtEmail;
        ImageView imgDelete;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CaNhanAdapter.ViewHolder holder;
        if (view == null) {
            holder = new CaNhanAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtId = (TextView) view.findViewById(R.id.txtId);
            holder.txtFullname = (TextView) view.findViewById(R.id.txtHoVaTen);
            holder.txtEmail = (TextView) view.findViewById(R.id.txtEmail);
            holder.txtTK = (TextView) view.findViewById(R.id.txtTaiKhoan);
            holder.txtMK = (TextView) view.findViewById(R.id.txtMatKhau);
            holder.imgDelete=(ImageView) view.findViewById(R.id.img_Delete);
            view.setTag(holder);
        } else {
            holder = (CaNhanAdapter.ViewHolder) view.getTag();
        }

        CaNhan caNhan = caNhans.get(i);
        holder.txtId.setText((String.valueOf(caNhan.getId())));
        holder.txtFullname.setText(caNhan.getFullname());
        holder.txtEmail.setText(caNhan.getEmail());
        holder.txtTK.setText(caNhan.getUsername());
        holder.txtMK.setText(caNhan.getPassword());

       holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.XoaDongTac(
                        caNhan.getId(),
                        caNhan.getFullname()
                );
            }
        });
        return view;
    }
}

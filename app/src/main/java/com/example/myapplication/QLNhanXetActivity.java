package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class QLNhanXetActivity extends AppCompatActivity {
    ListView lvNhanXet;
    Danhgiadatabse db;
    QLNhanXetAdapter adapter;
    ArrayList<DanhGia> danhGias=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlnhan_xet);
        lvNhanXet=(ListView) findViewById(R.id.lvAdminNhanXet);
        db=new Danhgiadatabse(this);
        adapter=new QLNhanXetAdapter(QLNhanXetActivity.this,R.layout.dong_admin_nhan_xet,danhGias);
        lvNhanXet.setAdapter(adapter);

        GetData();
    }
    public void GetData(){
        Cursor cursor= db.getAllData();
        while (cursor.moveToNext()){
            danhGias.add(new DanhGia(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
        }
        adapter.notifyDataSetChanged();
    }
    public void XoaNhanXet(int id,String fullname){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(this);
        dialogXoa.setTitle("Bạn muốn xóa nhận xét của: "+fullname+" ?");
        dialogXoa.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean bl=db.Delete((id));
                if(bl) {
                    Toast.makeText(QLNhanXetActivity.this, "Đã xóa nhận xét " + fullname, Toast.LENGTH_SHORT).show();
                    danhGias.clear();
                    GetData();
                }
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });;
        dialogXoa.show();
    }
}
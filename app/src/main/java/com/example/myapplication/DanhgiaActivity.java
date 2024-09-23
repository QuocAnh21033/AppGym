package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class DanhgiaActivity extends AppCompatActivity {

    EditText edtTenNX,edtNoidung;
    Button btnLuuNX;
    ListView lvNhanXet;
    Danhgiadatabse db;
    DanhGiaAdapter adapter;
    ArrayList<DanhGia> danhGias=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhgia);
        edtTenNX=(EditText) findViewById(R.id.edtTenDanhGia);
        edtNoidung=(EditText) findViewById(R.id.edtNoiDung);
        btnLuuNX=(Button) findViewById(R.id.btnNhanXet);
        lvNhanXet=(ListView) findViewById(R.id.lvNhanXet);
        db=new Danhgiadatabse(this);
        adapter=new DanhGiaAdapter(DanhgiaActivity.this,R.layout.dong_nhan_xet,danhGias);
        lvNhanXet.setAdapter(adapter);

        GetData();
        btnLuuNX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten=edtTenNX.getText().toString();
                String noidung=edtNoidung.getText().toString();
                boolean bl=db.addNhanXet(ten,noidung);
                if(bl){
                    Toast.makeText(DanhgiaActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DanhgiaActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
                edtTenNX.setText("");
                edtNoidung.setText("");
                danhGias.clear();
                GetData();
            }
        });
    }
    private ArrayList<DanhGia> getAll(){
        Cursor cursor=db.getAllData();
        while (cursor.moveToNext()){
            Integer id=cursor.getInt(0);
            String fullname=cursor.getString(1);
            String noidung=cursor.getString(2);
            DanhGia danhGia=new DanhGia(id,fullname,noidung);
            danhGias.add(danhGia);
        }
        return danhGias;
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
}
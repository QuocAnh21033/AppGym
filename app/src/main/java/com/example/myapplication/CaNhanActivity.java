package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CaNhanActivity extends AppCompatActivity {

    CaNhanAdapter adapter;
    ListView lvCaNhan;
    ArrayList<CaNhan>  caNhans=new ArrayList<>();
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_nhan);
        lvCaNhan=(ListView) findViewById(R.id.lvdongCaNhan);
        db = new Database(this);
        adapter=new CaNhanAdapter(CaNhanActivity.this,R.layout.dong_ca_nhan,caNhans);
        lvCaNhan.setAdapter(adapter);
        GetData();

    }
    private ArrayList<CaNhan> getAll(){
        Cursor cursor=db.getAllData();
        while (cursor.moveToNext()){
            Integer id=cursor.getInt(0);
            String fullname=cursor.getString(1);
            String email=cursor.getString(2);
            String username=cursor.getString(3);
            String password=cursor.getString(4);
            CaNhan caNhan=new CaNhan(id,fullname,email,username,password);
            caNhans.add(caNhan);
        }
        return caNhans;
    }
    public void GetData(){
        Cursor cursor= db.getAllData();
        while (cursor.moveToNext()){
            caNhans.add(new CaNhan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)

            ));
        }
        adapter.notifyDataSetChanged();
    }

   public void XoaDongTac(int id,String fullname){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(this);
        dialogXoa.setTitle("Bạn muốn xóa : "+fullname+" ?");
        dialogXoa.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean bl=db.Delete(id);
                if(bl) {
                    Toast.makeText(CaNhanActivity.this, "Đã xóa " + fullname, Toast.LENGTH_SHORT).show();
                    caNhans.clear();
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
package com.example.myapplication;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ChiTietDongTacLungActivity extends AppCompatActivity {

    ImageView imgThem,imgBack;
    ListView lvDongTacLung;
    ArrayList<DongTac> dongTacLungArrayList;
    DongTacLungAdapter adapterLung;
    public static DatabaseLung databaseLung;
    int REQUEST_CODE_OPEN=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dong_tac_lung);
        imgThem=(ImageView) findViewById(R.id.img_Add);
        imgBack=(ImageView) findViewById(R.id.img_Back);
        lvDongTacLung=(ListView) findViewById(R.id.lvDongTacLung);
        dongTacLungArrayList=new ArrayList<>();
        adapterLung=new DongTacLungAdapter(this,R.layout.dong_dongtac,dongTacLungArrayList);
        lvDongTacLung.setAdapter(adapterLung);
        databaseLung=new DatabaseLung(this,"QuanLy.sqlite",null,1);
        databaseLung.QueryData("CREATE TABLE IF NOT EXISTS DongTacLung(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(100),Buoc1 VARCHAR(100),Buoc2 VARCHAR(100),Buoc3 VARCHAR(100),HinhAnh BLOB)");
        //get data
        GetDataDongTacLung();

        imgThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ChiTietDongTacLungActivity.this,ThemDongTacLungActivity.class);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChiTietDongTacLungActivity.this,QuanlibaitapActivity.class);
                startActivity(intent);
            }
        });
    }
    public void GetDataDongTacLung(){
        Cursor cursor= databaseLung.GetData("SELECT * FROM DongTacLung");
        while (cursor.moveToNext()){
            dongTacLungArrayList.add(new DongTac(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getBlob(5)

            ));
        }
        adapterLung.notifyDataSetChanged();
    }
    public void XoaDongTac(String ten,int id){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(this);
        dialogXoa.setTitle("Bạn muốn xóa động tác: "+ten+" ?");
        dialogXoa.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseLung.QueryData("DELETE FROM DongTacLung WHERE Id='"+id+"'");
                Toast.makeText(ChiTietDongTacLungActivity.this, "Đã xóa động tác "+ten, Toast.LENGTH_SHORT).show();
                dongTacLungArrayList.clear();
                GetDataDongTacLung();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });;
        dialogXoa.show();
    }
    public void SuaDongTac(int id,String ten,String buoc1,String buoc2,String buoc3,byte[] hinh){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sua_dong_tac);
        EditText medtTenĐT=(EditText) dialog.findViewById(R.id.edtTenBT);
        EditText medtB1=(EditText) dialog.findViewById(R.id.edtBuoc1);
        EditText medtB2=(EditText) dialog.findViewById(R.id.edtBuoc2);
        EditText medtB3=(EditText) dialog.findViewById(R.id.edtBuoc3);
        Button mbtnXacNhan=(Button) dialog.findViewById(R.id.btnSua);
        Button mbtnHuy=(Button) dialog.findViewById(R.id.btnHuy);
        ImageButton ibtnOpenfile=(ImageButton) dialog.findViewById(R.id.ibtnOpen);
        ImageView imgHinh=(ImageView) dialog.findViewById(R.id.imgHinh);
        //Chuyển imgView sang byte[]
        BitmapDrawable bitmapDrawable= (BitmapDrawable) imgHinh.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);//định dạng kiểu dữ liệu xuất ra
        byte[] hinhanh=byteArrayOutputStream.toByteArray();
        //chuyển byte[] sang imgview
        Bitmap bitmap1= BitmapFactory.decodeByteArray(hinh,0,hinh.length);
        //lấy thông tin đã thêm ra
        medtTenĐT.setText(ten);
        medtB1.setText(buoc1);
        medtB2.setText(buoc2);
        medtB3.setText(buoc3);
        imgHinh.setImageBitmap(bitmap1);

        mbtnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medtĐTMoi=medtTenĐT.getText().toString().trim();
                String medtB1Moi=medtB1.getText().toString().trim();
                String medtB2Moi=medtB2.getText().toString().trim();
                String medtB3Moi=medtB3.getText().toString().trim();
                //update database
                databaseLung.QueryData("UPDATE DongTacLung SET Ten='"+medtĐTMoi+"',Buoc1='"+medtB1Moi+"',Buoc2='"+medtB2Moi+"',Buoc3='"+medtB3Moi+"' WHERE Id='"+id+"'");
                Toast.makeText(ChiTietDongTacLungActivity.this,"Đã cập nhật",Toast.LENGTH_SHORT).show();
                dialog.cancel();
                dongTacLungArrayList.clear();
                GetDataDongTacLung();
            }
        });

        ibtnOpenfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_OPEN);
            }
        });

        mbtnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();

    }
}
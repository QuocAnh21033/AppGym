package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemDongTacMongActivity extends AppCompatActivity {

    Button mbtnThem,mbtnHuy;
    ImageButton ibtnOpenfile;
    ImageView imgHinh;
    EditText medtTenĐT,medtB1,medtB2,medtB3;
    int REQUEST_CODE_OPEN=1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dong_tac_mong);
        mbtnHuy=(Button) findViewById(R.id.btnHuy);
        mbtnThem=(Button) findViewById(R.id.btnThem);
        medtTenĐT=(EditText) findViewById(R.id.edtTenBTMong);
        medtB1=(EditText) findViewById(R.id.edtBuoc1BTMong);
        medtB2=(EditText) findViewById(R.id.edtBuoc2BTMong);
        medtB3=(EditText) findViewById(R.id.edtBuoc3BTMong);
        ibtnOpenfile=(ImageButton) findViewById(R.id.ibtnOpen);
        imgHinh=(ImageView) findViewById(R.id.imgHinh);

        //nhấn vào ảnh openfile để chọn hình
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
                AlertDialog.Builder d=new AlertDialog.Builder(ThemDongTacMongActivity.this);
                d.setTitle("Bạn có chắc hủy ?");
                d.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                d.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent= new Intent(ThemDongTacMongActivity.this,ChiTietDongTacMongActivity.class);
                        startActivity(intent);
                    }
                });
                d.create().show();
            }
        });

        mbtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyển hình từ imgview sang mảng byte
                BitmapDrawable bitmapDrawable= (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);//định dạng kiểu dữ liệu xuất ra
                byte[] hinhanh=byteArrayOutputStream.toByteArray();//chứa dữ liệu
                String tendt=medtTenĐT.getText().toString();
                String buoc1=medtB1.getText().toString();
                String buoc2=medtB2.getText().toString();
                String buoc3=medtB3.getText().toString();

                if(tendt.equals("")||buoc1.equals("")||buoc2.equals("")||buoc3.equals("")) {
                    if (tendt.equals("")) {
                        Toast.makeText(ThemDongTacMongActivity.this, "Vui lòng nhập tên động tác", Toast.LENGTH_SHORT).show();
                    } else if (buoc1.equals("")) {
                        Toast.makeText(ThemDongTacMongActivity.this, "Vui lòng nhập Bước 1", Toast.LENGTH_SHORT).show();
                    } else if (buoc2.equals("")) {
                        Toast.makeText(ThemDongTacMongActivity.this, "Vui lòng nhập Bước 2", Toast.LENGTH_SHORT).show();
                    } else if (buoc3.equals("")) {
                        Toast.makeText(ThemDongTacMongActivity.this, "Vui lòng nhập Bước 3", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    ChiTietDongTacMongActivity.databaseMong.INSERT_DONGTACMONG(
                            medtTenĐT.getText().toString().trim(),
                            medtB1.getText().toString().trim(),
                            medtB2.getText().toString().trim(),
                            medtB3.getText().toString().trim(),
                            hinhanh
                    );
                    Toast.makeText(ThemDongTacMongActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemDongTacMongActivity.this, ChiTietDongTacMongActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
    //Đổ hình vào imgHinh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_OPEN && resultCode==RESULT_OK && data != null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}
package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AdminActivity extends AppCompatActivity {

    Button btnThoat;
    LinearLayout QlBaiTap,QlThongTin,QlDanhGia;
    ImageView imgBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnThoat=(Button) findViewById(R.id.buttonThoat);
        QlBaiTap=(LinearLayout) findViewById(R.id.linearQLBT);
        QlThongTin=(LinearLayout) findViewById(R.id.linearQLTT);
        QlDanhGia=(LinearLayout) findViewById(R.id.linearQLDG);
        imgBack=(ImageView) findViewById(R.id.imgBackHome);

        QlBaiTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this,QuanlibaitapActivity.class);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this,BaitapActivity.class);
                startActivity(intent);
            }
        });
        QlThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this,CaNhanActivity.class);
                startActivity(intent);
            }
        });
        QlDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this,QLNhanXetActivity.class);
                startActivity(intent);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
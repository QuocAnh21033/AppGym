package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class QuanlibaitapActivity extends AppCompatActivity {

    ImageView imgBack;
    RelativeLayout rlCoVai,rlCoBung,rlCoChan,rlCoNguc,rlCoMong,rlCoTay,rlCoLung;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlibaitap);
        imgBack=(ImageView) findViewById(R.id.img_QuayLai);
        rlCoVai= (RelativeLayout) findViewById(R.id.CoVai);
        rlCoBung= (RelativeLayout) findViewById(R.id.CoBung);
        rlCoChan= (RelativeLayout) findViewById(R.id.CoChan);
        rlCoNguc= (RelativeLayout) findViewById(R.id.CoNguc);
        rlCoMong= (RelativeLayout) findViewById(R.id.CoMong);
        rlCoTay= (RelativeLayout) findViewById(R.id.CoTay);
        rlCoLung= (RelativeLayout) findViewById(R.id.CoLung);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(QuanlibaitapActivity.this,AdminActivity.class);
            startActivity(intent);
            }
        });

        rlCoBung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanlibaitapActivity.this,ChiTietDongTacBungActivity.class);
                startActivity(intent);
            }
        });

        rlCoVai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanlibaitapActivity.this,ChiTietDongTacVaiActivity.class);
                startActivity(intent);
            }
        });

        rlCoChan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanlibaitapActivity.this,ChiTietDongTacChanActivity.class);
                startActivity(intent);
            }
        });

        rlCoNguc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanlibaitapActivity.this,ChiTietDongTacNgucActivity.class);
                startActivity(intent);
            }
        });

        rlCoLung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanlibaitapActivity.this,ChiTietDongTacLungActivity.class);
                startActivity(intent);
            }
        });

        rlCoTay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanlibaitapActivity.this,ChiTietDongTacTayActivity.class);
                startActivity(intent);
            }
        });

        rlCoMong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanlibaitapActivity.this,ChiTietDongTacMongActivity.class);
                startActivity(intent);
            }
        });
    }


}
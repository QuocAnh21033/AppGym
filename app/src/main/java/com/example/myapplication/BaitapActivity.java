package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class BaitapActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RelativeLayout rlCoVai,rlCoBung,rlCoChan,rlCoNguc,rlCoMong,rlCoTay,rlCoLung;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baitap);
        rlCoVai= (RelativeLayout) findViewById(R.id.rlCoVai);
        rlCoBung= (RelativeLayout) findViewById(R.id.rlCoBung);
        rlCoChan= (RelativeLayout) findViewById(R.id.rlCoChan);
        rlCoNguc= (RelativeLayout) findViewById(R.id.rlCoNguc);
        rlCoMong= (RelativeLayout) findViewById(R.id.rlCoMong);
        rlCoTay= (RelativeLayout) findViewById(R.id.rlCoTay);
        rlCoLung= (RelativeLayout) findViewById(R.id.rlCoLung);
        toolbar=findViewById(R.id.toolbar2);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        Menu menu = navigationView.getMenu();

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        rlCoBung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BaitapActivity.this,XemDongTacBungActivity.class);
                startActivity(intent);
            }
        });

        rlCoVai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BaitapActivity.this,XemDongTacVaiActivity.class);
                startActivity(intent);
            }
        });

        rlCoChan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BaitapActivity.this,XemDongTacChanActivity.class);
                startActivity(intent);
            }
        });

        rlCoNguc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BaitapActivity.this,XemDongTacNgucActivity.class);
                startActivity(intent);
            }
        });

        rlCoLung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BaitapActivity.this,XemDongTacLungActivity.class);
                startActivity(intent);
            }
        });

        rlCoTay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BaitapActivity.this,XemDongTacTayActivity.class);
                startActivity(intent);
            }
        });

        rlCoMong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BaitapActivity.this,XemDongTacMongActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{super.onBackPressed();}

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_danhgia:
                Intent intentTuVan = new Intent(BaitapActivity.this,DanhgiaActivity.class);
                startActivity(intentTuVan);
                break;
            case R.id.nav_login:
                Intent intentLoGin = new Intent(BaitapActivity.this,LoginActivity.class);
                startActivity(intentLoGin);
                break;
            case R.id.nav_logout:
                Intent intentLogOut = new Intent(BaitapActivity.this,LoginActivity.class);
                startActivity(intentLogOut);
                break;
            case R.id.nav_BMI:
                Intent intentBMI = new Intent(BaitapActivity.this,BMIActivity.class);
                startActivity(intentBMI);
                break;
            case R.id.nav_dangki:
                Intent intentDK = new Intent(BaitapActivity.this,DangkiActivity.class);
                startActivity(intentDK);
                break;
            case R.id.nav_gioithieu:
                Intent intentGT = new Intent(BaitapActivity.this,GioiThieuActivity.class);
                startActivity(intentGT);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
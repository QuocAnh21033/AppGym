package com.example.myapplication;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class XemDongTacNgucActivity extends AppCompatActivity {

    long lStartTime, lPauseTime, lSystemTime = 0L;
    Handler handler = new Handler();
    boolean isRun;
    ImageView imgDongHo;
    ImageView imgBack;
    ListView lvDongTacNguc;
    ArrayList<DongTac> dongTacNgucArrayList;
    XemDongTacNgucAdapter adapterNguc;
    public static DatabaseNguc databaseNguc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_dong_tac_nguc);
        imgBack=(ImageView) findViewById(R.id.img_Back);
        imgDongHo=(ImageView) findViewById(R.id.imgDongHo);
        lvDongTacNguc=(ListView) findViewById(R.id.lvDongTacNguc);
        dongTacNgucArrayList=new ArrayList<>();
        adapterNguc=new XemDongTacNgucAdapter(this,R.layout.dong_xemdongtac,dongTacNgucArrayList);
        lvDongTacNguc.setAdapter(adapterNguc);
        databaseNguc=new DatabaseNguc(this,"QuanLy.sqlite",null,1);
        databaseNguc.QueryData("CREATE TABLE IF NOT EXISTS DongTacNguc(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(100),Buoc1 VARCHAR(100),Buoc2 VARCHAR(100),Buoc3 VARCHAR(100),HinhAnh BLOB)");
        //get data
        GetDataDongTacNguc();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(XemDongTacNgucActivity.this,BaitapActivity.class);
                startActivity(intent);
            }
        });
        imgDongHo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DongHoBamGio();
            }
        });
    }
    public void GetDataDongTacNguc(){
        Cursor cursor= databaseNguc.GetData("SELECT * FROM DongTacNguc");
        while (cursor.moveToNext()){
            dongTacNgucArrayList.add(new DongTac(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getBlob(5)

            ));
        }
        adapterNguc.notifyDataSetChanged();
    }
    private void DongHoBamGio(){
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dong_ho_bam_gio);
        TextView txtDongHo=(TextView) dialog.findViewById(R.id.txtDongHo);
        TextView txtTimer=(TextView) dialog.findViewById(R.id.txtTimer);
        Button btnStart=(Button) dialog.findViewById(R.id.btnStart);
        Button btnPause=(Button) dialog.findViewById(R.id.btnPause);
        Button btnStop=(Button) dialog.findViewById(R.id.btnStop);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lSystemTime = SystemClock.uptimeMillis() - lStartTime;
                long lUpdateTime = lPauseTime + lSystemTime;
                long secs = (long)(lUpdateTime/1000);
                long mins= secs/60;
                secs = secs %60;
                long milliseconds = (long)(lUpdateTime%1000);
                txtTimer.setText(""+mins+":" + String.format("%02d",secs) + ":" + String.format("%03d",milliseconds));
                handler.postDelayed(this,0);
            }
        };
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRun)
                    return;
                isRun = true;
                lStartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRun)
                    return;
                isRun = false;
                lPauseTime = 0;
                handler.removeCallbacks(runnable);
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRun)
                    return;
                isRun = false;
                lPauseTime += lSystemTime;
                handler.removeCallbacks(runnable);
            }
        });

        dialog.show();

    }
}
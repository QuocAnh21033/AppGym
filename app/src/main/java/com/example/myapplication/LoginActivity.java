package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtname, edtpass;
    Button btndangnhap;
    TextView tv;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtname = findViewById(R.id.edtname);
        edtpass = findViewById(R.id.edtpass);
        btndangnhap = findViewById(R.id.btndangnhap);
        tv = findViewById(R.id.textView3);
        db = new Database(this);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtname.getText().toString();
                String password = edtpass.getText().toString();

                if(username.equals("") || password.equals(""))
                    Toast.makeText(LoginActivity.this, "Yeu cau nhap day du", Toast.LENGTH_SHORT).show();
                else {
                    Boolean check = db.checkpass(username,password);
                    if(check== true){
                        if(username.equals("admin")){
                            Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),AdminActivity.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),BaitapActivity.class);
                            startActivity(i);
                        }
                    }else
                        Toast.makeText(LoginActivity.this, "Tai khoan chua dang ky", Toast.LENGTH_SHORT).show();



                }

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, DangkiActivity.class);
                startActivity(i);
            }
        });
    }
}
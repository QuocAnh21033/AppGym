package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DangkiActivity extends AppCompatActivity {

    EditText edtname, edtpass, edtpass2,edtfullname,edtemail;
    Button btndangky;
    TextView tv1;

    Database db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        edtemail=findViewById(R.id.edtemail);
        edtfullname=findViewById(R.id.edtHoVaTen);
        edtname = findViewById(R.id.edtnamedk);
        edtpass = findViewById(R.id.edtpass1);
        edtpass2 = findViewById(R.id.edtpass2);
        btndangky = findViewById(R.id.btndangky);
        tv1 = findViewById(R.id.textView4);
        db = new Database(this);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtname.getText().toString();
                String fullname=edtfullname.getText().toString();
                String password = edtpass.getText().toString();
                String password2 = edtpass2.getText().toString();
                String gmail=edtemail.getText().toString();
                if (fullname.equals("")||username.equals("") || password.equals("") || password2.equals("")||gmail.equals(""))
                    Toast.makeText(DangkiActivity.this, "Yêu cầu nhập đầy đủ", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(password2)) {

                        Boolean checkname = db.checkname(username);
                        if(checkname== false){
                            Boolean insert = db.insertData(fullname,gmail,username,password);
                            if(insert == true){
                                Toast.makeText(DangkiActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(DangkiActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(DangkiActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(DangkiActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
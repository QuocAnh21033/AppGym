package com.example.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    Button btnkiemtra;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        final EditText edWeg,edHei;
        final TextView txtRes,txtInter;
        Button btnRes,btnReset;

        edWeg=(EditText) findViewById(R.id.edweg);
        edHei= (EditText) findViewById(R.id.edhei);

        txtInter=(TextView) findViewById(R.id.txtinter);
        txtRes=(TextView) findViewById(R.id.txtres);

        btnRes= (Button) findViewById(R.id.btnres);
        btnReset= (Button) findViewById(R.id.btnreset);

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strweg= edWeg.getText().toString();
                String strhei= edHei.getText().toString();

                if(strweg.equals("")){
                    edWeg.setError("Làm ơn nhập cân nặng!!! ");
                    edWeg.requestFocus();
                    return;
                }
                if(strhei.equals("")){
                    edHei.setError("Làm ơn nhập chiều cao!!!");
                    edHei.requestFocus();
                    return;
                }

                float weight = Float.parseFloat(strweg);
                float height = Float.parseFloat(strhei)/100;

                float bmiVlaue = BMICalculate(weight,height);

                txtInter.setText(interpreteBMI(bmiVlaue));
                txtRes.setText("BMI= "+bmiVlaue);


            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edHei.setText("");
                edWeg.setText("");
                txtInter.setText("");
                txtRes.setText("");
            }
        });
    }
    public float BMICalculate(float weight,float height){
        return weight / (height * height);
    }

    public String interpreteBMI(float bmiValue){
        if( bmiValue <18){
            return "Cơ thể đang gầy";
        }
        else if(bmiValue >=18 && bmiValue <25){
            return "Cơ thể bình thường";
        }
        else if(bmiValue >= 25 && bmiValue <30){
            return "Béo phì cấp độ 1";
        }
        else if(bmiValue >= 30 && bmiValue <35){
            return "Béo phì cấp độ 2";
        }
        else
            return "Béo phì cấp độ 3";
    }
}
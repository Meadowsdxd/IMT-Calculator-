package com.example.imtcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        result=findViewById(R.id.text_result);
        getIntentMain();
    }
    String I1,I2,I3,I4,I5,I6,I7,I8,Name;
    String xt;
    double x;
    @SuppressLint("SetTextI18n")
    private void getIntentMain(){
        Intent i=getIntent();
        if(i!=null){
            Name=  i.getStringExtra("dates");
            I1=  i.getStringExtra("i1");
            I2=  i.getStringExtra("i2");
            I3=  i.getStringExtra("i3");
            I4=  i.getStringExtra("i4");
            I5=  i.getStringExtra("i5");
            I6=  i.getStringExtra("i6");
            I7=  i.getStringExtra("i7");
            I8=  i.getStringExtra("i8");



            result.setText(
                    (Name)+"\n"+
                            getString(R.string.total_steps)+I1+"\n"+
                            getString(R.string.total_distance) +(I2)+"\n"+
                            getString(R.string.average_speed)+I3+"\n"+
                            getString(R.string.average_frequency)+I4+"\n"+
                            getString(R.string.burned_calories)+I5+"\n"+
                            getString(R.string.moving_time)+I6);


        }

    }
}
package com.example.imtcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imtcalculator.view.WeightView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
/*Button add;
EditText age,weight,height;
private  WeightView weightView;
TextView result,underweight,Insufficient,Norm,preobesity,firstDegree,secondDegree,thirdDegree;
RadioButton men,girl;
boolean sexCheck;*/
private ViewPager pager;
    private PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> list=new ArrayList<>();
        list.add(new MainFragment());
        list.add(new StepsFragment());

        pager=findViewById(R.id.pager);
        adapter=new Slider(getSupportFragmentManager(),list);
        pager.setAdapter(adapter);
     /*   add = findViewById(R.id.button);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        weightView = (WeightView) findViewById(R.id.indicator);
        result=findViewById(R.id.result);
        underweight=findViewById(R.id.underweight);
        Insufficient=findViewById(R.id.Insufficient);
        Norm=findViewById(R.id.Norm);
        preobesity=findViewById(R.id.preobesity);
        firstDegree=findViewById(R.id.firstDegree);
        secondDegree=findViewById(R.id.secondDegree);
        thirdDegree=findViewById(R.id.thirdDegree);
        men=findViewById(R.id.men);
        girl=findViewById(R.id.girl);

        men.setOnClickListener(radioButtonClickListener);
        girl.setOnClickListener(radioButtonClickListener);
        age.addTextChangedListener(next);
        height.addTextChangedListener(next);
        weight.addTextChangedListener(next);*/

}
/*    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.men: {sexCheck=true;}
                    break;
                case R.id.girl: {sexCheck=false;}
                    break;
                default:
                    break;
            }
        }
    };
Calculation calculation=new Calculation();
    private TextWatcher next=new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           // текст только что изменили
            try {
                if ((weight.getText().toString().equals(""))||(age.getText().toString().equals(""))||(height.getText().toString().equals(""))){weightView.Accept(0);
                    result.setText("");
                }
                else{ weightView.Accept(Math.abs(calculation.FLourenca(Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),Integer.parseInt(age.getText().toString()),sexCheck)));
                    result.setText(String.format("%.1f",Math.abs(calculation.FLourenca(Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),Integer.parseInt(age.getText().toString()),sexCheck))));


                        iniResult=Math.abs(calculation.FLourenca(Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),Integer.parseInt(age.getText().toString()),sexCheck));
                        if(iniResult==0){
                            underweight.setTextColor(Color.parseColor("#FF000000"));
                            Insufficient.setTextColor(Color.parseColor("#FF000000"));
                            Norm.setTextColor(Color.parseColor("#FF000000"));
                            preobesity.setTextColor(Color.parseColor("#FF000000"));
                            firstDegree.setTextColor(Color.parseColor("#FF000000"));
                            secondDegree.setTextColor(Color.parseColor("#FF000000"));
                            thirdDegree.setTextColor(Color.parseColor("#FF000000"));
                        }else
                        if(iniResult<16){
                            underweight.setTextColor(Color.parseColor("#FF03A9F4"));
                            Insufficient.setTextColor(Color.parseColor("#FF000000"));
                            Norm.setTextColor(Color.parseColor("#FF000000"));
                            preobesity.setTextColor(Color.parseColor("#FF000000"));
                            firstDegree.setTextColor(Color.parseColor("#FF000000"));
                            secondDegree.setTextColor(Color.parseColor("#FF000000"));
                            thirdDegree.setTextColor(Color.parseColor("#FF000000"));
                        }else
                        if(iniResult>16&&iniResult<=18.5){
                            underweight.setTextColor(Color.parseColor("#FF000000"));
                            Insufficient.setTextColor(Color.parseColor("#FF03A9F4"));
                            Norm.setTextColor(Color.parseColor("#FF000000"));
                            preobesity.setTextColor(Color.parseColor("#FF000000"));
                            firstDegree.setTextColor(Color.parseColor("#FF000000"));
                            secondDegree.setTextColor(Color.parseColor("#FF000000"));
                            thirdDegree.setTextColor(Color.parseColor("#FF000000"));
                        }else
                        if(iniResult>18.5&&iniResult<=25){
                            underweight.setTextColor(Color.parseColor("#FF000000"));
                            Insufficient.setTextColor(Color.parseColor("#FF000000"));
                            Norm.setTextColor(Color.parseColor("#FF03A9F4"));
                            preobesity.setTextColor(Color.parseColor("#FF000000"));
                            firstDegree.setTextColor(Color.parseColor("#FF000000"));
                            secondDegree.setTextColor(Color.parseColor("#FF000000"));
                            thirdDegree.setTextColor(Color.parseColor("#FF000000"));
                        }else
                        if(iniResult>25&&iniResult<=30){
                            underweight.setTextColor(Color.parseColor("#FF000000"));
                            Insufficient.setTextColor(Color.parseColor("#FF000000"));
                            Norm.setTextColor(Color.parseColor("#FF000000"));
                            preobesity.setTextColor(Color.parseColor("#FF03A9F4"));
                            firstDegree.setTextColor(Color.parseColor("#FF000000"));
                            secondDegree.setTextColor(Color.parseColor("#FF000000"));
                            thirdDegree.setTextColor(Color.parseColor("#FF000000"));
                        }
                        else
                        if(iniResult>30&&iniResult<=35){
                            underweight.setTextColor(Color.parseColor("#FF000000"));
                            Insufficient.setTextColor(Color.parseColor("#FF000000"));
                            Norm.setTextColor(Color.parseColor("#FF000000"));
                            preobesity.setTextColor(Color.parseColor("#FF000000"));
                            firstDegree.setTextColor(Color.parseColor("#FF03A9F4"));
                            secondDegree.setTextColor(Color.parseColor("#FF000000"));
                            thirdDegree.setTextColor(Color.parseColor("#FF000000"));
                        }
                        else
                        if(iniResult>35&&iniResult<=40){
                            underweight.setTextColor(Color.parseColor("#FF000000"));
                            Insufficient.setTextColor(Color.parseColor("#FF000000"));
                            Norm.setTextColor(Color.parseColor("#FF000000"));
                            preobesity.setTextColor(Color.parseColor("#FF000000"));
                            firstDegree.setTextColor(Color.parseColor("#FF000000"));
                            secondDegree.setTextColor(Color.parseColor("#FF03A9F4"));
                            thirdDegree.setTextColor(Color.parseColor("#FF000000"));
                        }
                        else
                        if(iniResult>40){
                            underweight.setTextColor(Color.parseColor("#FF000000"));
                            Insufficient.setTextColor(Color.parseColor("#FF000000"));
                            Norm.setTextColor(Color.parseColor("#FF000000"));
                            preobesity.setTextColor(Color.parseColor("#FF000000"));
                            firstDegree.setTextColor(Color.parseColor("#FF000000"));
                            secondDegree.setTextColor(Color.parseColor("#FF000000"));
                            thirdDegree.setTextColor(Color.parseColor("#FF03A9F4"));
                        }

                }
            }catch (NumberFormatException e){}


        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // текст будет изменен

        }

        @Override
        public void afterTextChanged(Editable s) {
            // текст уже изменили

        }
};

    double iniResult=0;*/

}







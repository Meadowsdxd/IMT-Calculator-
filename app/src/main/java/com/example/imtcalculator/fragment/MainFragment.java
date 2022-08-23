package com.example.imtcalculator.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.imtcalculator.R;
import com.example.imtcalculator.activity.HelperActivity;
import com.example.imtcalculator.more.Calculation;
import com.example.imtcalculator.view.WeightView;


public class MainFragment extends Fragment {


    EditText age,weight,height;
    private  WeightView weightView;
    TextView result,underweight,Insufficient,Norm,preobesity,firstDegree,secondDegree,thirdDegree;
    Button nextPage;
    RadioButton men,girl;
    boolean sexCheck;
    double iniResult=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        age = view.findViewById(R.id.age);
        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);
        weightView =  view.findViewById(R.id.indicator);
        result=view.findViewById(R.id.result);
        underweight=view.findViewById(R.id.underweight);
        Insufficient=view.findViewById(R.id.Insufficient);
        Norm=view.findViewById(R.id.Norm);
        preobesity=view.findViewById(R.id.preobesity);
        firstDegree=view.findViewById(R.id.firstDegree);
        secondDegree=view.findViewById(R.id.secondDegree);
        thirdDegree=view.findViewById(R.id.thirdDegree);
        men=view.findViewById(R.id.men);
        girl=view.findViewById(R.id.girl);
    nextPage=view.findViewById(R.id.button);
        men.setOnClickListener(radioButtonClickListener);
        girl.setOnClickListener(radioButtonClickListener);
        age.addTextChangedListener(next);
        height.addTextChangedListener(next);
        weight.addTextChangedListener(next);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(),HelperActivity.class);
                intent.putExtra("result", Math.abs(calculation.FLourenca(Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),Integer.parseInt(age.getText().toString()),sexCheck)));
                startActivity(intent);

            }
        });
        return view;
    }
    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
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

}
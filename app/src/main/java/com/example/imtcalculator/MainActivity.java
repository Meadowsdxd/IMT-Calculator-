package com.example.imtcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
Button add;
EditText age,weight,height;
private  WeightView weightView;
TextView result; RadioButton men,girl;
boolean sexCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.button);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        weightView = (WeightView) findViewById(R.id.indicator);
        result=findViewById(R.id.result);
        men=findViewById(R.id.men);
        girl=findViewById(R.id.girl);
        men.setOnClickListener(radioButtonClickListener);
        girl.setOnClickListener(radioButtonClickListener);
        age.addTextChangedListener(next);
        height.addTextChangedListener(next);
        weight.addTextChangedListener(next);

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
                }
                else{ weightView.Accept(Math.abs(calculation.FLourenca(Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),Integer.parseInt(age.getText().toString()),sexCheck)));
                    result.setText(String.format("%.1f",Math.abs(calculation.FLourenca(Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),Integer.parseInt(age.getText().toString()),sexCheck))));

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







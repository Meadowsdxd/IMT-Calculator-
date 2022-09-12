package com.example.imtcalculator.fragment;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
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
    RadioGroup ra;
    boolean sexCheck;
    double iniResult=0;
    Spinner spinnerHeight,spinnerWeight;
    String[] heightSpin;
    String[] weightSpin;
    int heightPos,weightPos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Resources resources=getResources();
        heightSpin = new String[]{resources.getString(R.string._SM), resources.getString(R.string._M), resources.getString(R.string._D), resources.getString(R.string._FT)};
         weightSpin = new String[]{resources.getString(R.string._KG), resources.getString(R.string._Funt)};

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
        age.addTextChangedListener(next);
        height.addTextChangedListener(next);
        weight.addTextChangedListener(next);
        ra=view.findViewById(R.id.sex);
        nextPage.setVisibility(View.GONE);
        Spinner1(view);
        Spinner2(view);

        nextPage.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(),HelperActivity.class);
            intent.putExtra("result",iniResult);
            startActivity(intent);
        });

        return view;
    }
   View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
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
    private final TextWatcher next=new TextWatcher() {
        @SuppressLint("DefaultLocale")
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                if ((weight.getText().toString().equals(""))||(age.getText().toString().equals(""))||(height.getText().toString().equals(""))||ra.isSelected()){weightView.Accept(0);
                    result.setText("");
                    nextPage.setVisibility(View.GONE);
                }
                else{
                    ra.setOnCheckedChangeListener((group, checkedId) -> {
                        switch (checkedId) {
                            case -1:

                                break;
                            case R.id.men:{sexCheck=true;transfer();  }

                                break;
                            case R.id.girl:{sexCheck=false; transfer();}

                                break;


                            default:
                                break;
                        }
                    });
                    transfer();

                    }
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

            }catch (NumberFormatException e) {
                e.printStackTrace();
            }}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void afterTextChanged(Editable s) {}
    };

private void Spinner1(View view){
    spinnerHeight  = view.findViewById(R.id.spinnerHeight);
    // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
    ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, heightSpin);
    // Определяем разметку для использования при выборе элемента
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Применяем адаптер к элементу spinner
    spinnerHeight.setAdapter(adapter);
    spinnerHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
            switch (selectedItemPosition){
                case 0: {heightPos=1; }break;
                case 1:{ heightPos=2;}break;
                case 2: {heightPos=3; }break;
                case 3: {heightPos=4; }break;

            }}
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });
}
    private void Spinner2(View view){
        spinnerWeight  = view.findViewById(R.id.spinnerWeight);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, weightSpin);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerWeight.setAdapter(adapter);
        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                switch (selectedItemPosition){
                    case 0:{ weightPos=1;}break;
                    case 1:{ weightPos=2;}break;


                }}
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
    }
    void transfer(){
        weightView.Accept(Math.abs(calculation.FLourenca(Double.parseDouble(weight.getText().toString()), Double.parseDouble(height.getText().toString()), Integer.parseInt(age.getText().toString()), sexCheck,heightPos,weightPos)));
        iniResult = Math.abs(calculation.FLourenca(Double.parseDouble(weight.getText().toString()), Double.parseDouble(height.getText().toString()), Integer.parseInt(age.getText().toString()), sexCheck,heightPos,weightPos));
        nextPage.setVisibility(View.VISIBLE);
        result.setText(String.format("%.1f", iniResult));
    }

}
package com.example.imtcalculator;

public class Calculation implements FormulaLourenca {
    @Override
    public double FLourenca(double weight, double height,int age,boolean sex) {
       /* double difference1=(height-100)-(height-150)/2;
        double difference=weight-difference1;*/
        double difference=0;
        if(sex){
            difference=(weight/Math.pow(height,2))*10000;
        }else{difference=((weight+5)/Math.pow(height,2))*10000;}
        return difference;
    }
}

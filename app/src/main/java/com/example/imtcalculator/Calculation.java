package com.example.imtcalculator;

public class Calculation implements FormulaLourenca {
    @Override
    public double FLourenca(double weight, double height) {
       /* double difference1=(height-100)-(height-150)/2;
        double difference=weight-difference1;*/
        double difference=(weight/Math.pow(height,2))*10000;
        return difference;
    }
}

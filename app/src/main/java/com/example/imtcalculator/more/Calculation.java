package com.example.imtcalculator.more;

public class Calculation implements FormulaLourenca {
    @Override
    public double FLourenca(double weight, double height,int age,boolean sex,int heightPos,int weightPos) {
        double difference=0;
    if(heightPos==1&&weightPos==1){
        if(sex){
            if(age<18){
            difference=((weight+weight*0.3)/Math.pow(height,2))*10000;}
            else
                if (age>=18&&age<=50){difference=((weight)/Math.pow(height,2))*10000;}
                else
                    if(age>50&&age<=70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
                    else
                        if(age>70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
        }else{
            if(age<18){
                difference=((weight+10+weight*0.6)/Math.pow(height,2))*10000;}
            else
                if(age>18&&age<=50){difference=((weight+2)/Math.pow(height,2))*10000;}
                else
                    if(age>50&&age<=70){difference=((weight+2+weight*0.4)/Math.pow(height,2))*10000;}
                else
                    if(age>70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
        }}
        if(heightPos==1&&weightPos==2){
            if(sex){
                if(age<18){
                    difference=((weight+weight*0.3)/Math.pow(height,2))*10000;}
                else
                if (age>=18&&age<=50){difference=((weight)/Math.pow(height,2))*10000;}
                else
                if(age>50&&age<=70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
                else
                if(age>70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
            }else{
                if(age<18){
                    difference=((weight+10+weight*0.6)/Math.pow(height,2))*10000;}
                else
                if(age>18&&age<=50){difference=((weight+2)/Math.pow(height,2))*10000;}
                else
                if(age>50&&age<=70){difference=((weight+2+weight*0.4)/Math.pow(height,2))*10000;}
                else
                if(age>70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
            }}

        return difference;
    }

}

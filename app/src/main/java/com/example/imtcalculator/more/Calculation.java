package com.example.imtcalculator.more;

public class Calculation implements FormulaLourenca {
    @Override
    public double FLourenca(double weight, double height,int age,boolean sex,int heightPos,int weightPos) {
        double difference=0;
    if(heightPos==1&&weightPos==1){
       height=height;
       weight=weight;
    }else
        if(heightPos==1&&weightPos==2){
            weight=weight/2.2;
           }else
        if(heightPos==2&&weightPos==1){
            height=height*100;

            }else
        if(heightPos==2&&weightPos==2){
            height=height*100;
            weight=weight/2.20462;
           }else
        if(heightPos==3&&weightPos==1){
            height=height*2.54;
           }else
        if(heightPos==3&&weightPos==2){
            height=height*2.54;
            weight=weight/2.20462;
          }else
        if(heightPos==4&&weightPos==1){
            height=height*30.48;
        }else
        if(heightPos==4&&weightPos==2){
            height=height*30.48;
            weight=weight/2.20462;
        }

        if(sex){
            if(age<18){
                difference=((weight+weight*0.08)/Math.pow(height,2))*10000;}
            else
            if (age>=18&&age<=50){difference=((weight)/Math.pow(height,2))*10000;}
            else
            if(age>50&&age<=70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
            else
            if(age>70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
        }else{
            if(age<18){
                difference=(((weight+weight*0.1))/Math.pow(height,2))*10000;}
            else
            if(age>=18&&age<=50){difference=((weight+2)/Math.pow(height,2))*10000;}
            else
            if(age>50&&age<=70){difference=((weight+2+weight*0.4)/Math.pow(height,2))*10000;}
            else
            if(age>70){difference=((weight+weight*0.4)/Math.pow(height,2))*10000;}
        }
        return difference;
    }

}

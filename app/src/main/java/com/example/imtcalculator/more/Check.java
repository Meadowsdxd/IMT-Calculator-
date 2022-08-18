

        package com.example.imtcalculator.more;

        import android.content.Context;
        import android.widget.Toast;

        public class Check {
            public boolean ISNull(double weight, double height, int age, Context context){
                boolean trus = true;
                if (weight>300&&weight<30){
                    Toast.makeText(context,"Не правильний діапазон ваги",Toast.LENGTH_LONG).show();
                    trus = false;}
                else {
                    if (height>270&&weight<70) {
                        Toast.makeText(context, "Не правильний діапазон зросту", Toast.LENGTH_LONG).show();
                        trus = false;
                    } else {
                        if (age>120&&age<0) {
                            Toast.makeText(context, "Не правильний діапазон років", Toast.LENGTH_LONG).show();
                            trus = false;
                        }
                        }
                    }
                return trus;
            }
            public String Regu(String in){
                String out;
                int index = in.indexOf('.');
                out = in.substring(0, index+3);
                return out;
            }

        }
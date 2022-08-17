

        package com.example.imtcalculator.more;

        import android.content.Context;
        import android.widget.Toast;

        public class Check {
            public boolean ISNull(String name, String kolovo, String a, String b, String tempstart, String tempend,String woltag,String osvet,String holost, Context context){
                boolean trus = true;
                if (name.equals("")){
                    Toast.makeText(context,"Пусте поле!!\nНазвание",Toast.LENGTH_LONG).show();
                    trus = false;}
                else {
                    if (kolovo.equals("")) {
                        Toast.makeText(context, "Пусте поле!!\nКількість панелей", Toast.LENGTH_LONG).show();
                        trus = false;
                    } else {
                        if (a.equals("")) {
                            Toast.makeText(context, "Пусте поле!!\nСумарна довжина панелей", Toast.LENGTH_LONG).show();
                            trus = false;
                        } else {
                            if (b.equals("")) {
                                Toast.makeText(context, "Пусте поле!!\nСумарна ширина панелей", Toast.LENGTH_LONG).show();
                                trus = false;
                            } else {
                                if (tempstart.equals("")) {
                                    Toast.makeText(context, "Пусте поле!!\nПочаткова температура ", Toast.LENGTH_LONG).show();
                                    trus = false;
                                } else {
                                    if (tempend.equals("")) {
                                        Toast.makeText(context, "Пусте поле!!\nКінцева температура ", Toast.LENGTH_LONG).show();
                                        trus = false;
                                    } else {
                                        if (woltag.equals("")) {
                                            Toast.makeText(context, "Пусте поле!!\nВольтаж", Toast.LENGTH_LONG).show();
                                            trus = false;
                                        } else {
                                            if (holost.equals("")) {
                                                Toast.makeText(context, "Пусте поле!!\nЧас зарядки акумулятора", Toast.LENGTH_LONG).show();
                                                trus = false;
                                            } else if (Float.parseFloat(holost) < 60) {
                                                Toast.makeText(context, "час повинен бути більше години чи рівен їй!!\n час зарядки акумулятора", Toast.LENGTH_LONG).show();
                                                trus = false;
                                            } else if (Float.parseFloat(woltag) < 100) {
                                                Toast.makeText(context, "Вольтаж повинен бути більшим сотні!!\nВольтаж", Toast.LENGTH_LONG).show();
                                                trus = false;
                                            } else {
                                                if (osvet.equals("")) {
                                                    Toast.makeText(context, "Пустое поле!!\nОсвітленість", Toast.LENGTH_LONG).show();
                                                    trus = false;
                                                } else {
                                                    if (Float.parseFloat(kolovo) > 100) {
                                                        Toast.makeText(context, "Кількість повинне бути меньше 100!!!!\nКількість", Toast.LENGTH_LONG).show();
                                                        trus = false;
                                                    } else {
                                                        if (Float.parseFloat(tempend) > 51) {
                                                            Toast.makeText(context, "Температура повинна бути меньше 51 градуса!!!!\nТемпература", Toast.LENGTH_LONG).show();
                                                            trus = false;
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }}
                return trus;
            }
            public String Regu(String in){
                String out;
                int index = in.indexOf('.');
                out = in.substring(0, index+3);
                return out;
            }

        }
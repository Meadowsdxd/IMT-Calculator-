package com.example.imtcalculator.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class WeightView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    double value;

    public WeightView(Context context, int value) {
        super(context);
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public WeightView(Context context) {
        super(context);
    }

    public WeightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.save();

        canvas.scale(.5f * getWidth(), -1f * getHeight());
        canvas.translate(1.f, -1.f);

        int maxValue = 40;

    if(value<=16) {//Выраженный дефицит массы тела
      maxValue = 17 * 10;
    }else
        if(value>16&&value<=18.5){//Недостаточная (дефицит) масса тела
        maxValue = 17 *5;
    }else
        if(value>18.5&&value<=25){//Норма
        maxValue = 40;
    }else
        if(value>25&&value<=30){//Избыточная масса тела (предожирение)
            maxValue = 40;
        }else
        if(value>30&&value<=35){//Ожирение первой степени
            maxValue = 40;
        }else
        if(value>35&&value<40){//Ожирение второй степени
            maxValue = 40;
        }else
        if(value>35&&value<40){//Ожирение третьей степени (морбидное)
            maxValue = 40;
        }




        float scale = 0.9f;

        double step = Math.PI / maxValue;
        for (int i = 0; i <= maxValue; i++) {
            float x1 = (float) Math.cos(Math.PI - step*i);
            float y1 = (float) Math.sin(Math.PI - step*i);
            float x2;
            float y2;
            if (i % 20 == 0) {
                x2 = x1 * scale * 0.9f;
                y2 = y1 * scale * 0.9f;
            } else {
                x2 = x1 * scale;
                y2 = y1 * scale;
            }

        }

        canvas.save();

        canvas.rotate((float) (90 - (float) 180 * (value / (float) maxValue)));

        paint.setColor(0xFF000000);
        paint.setStrokeWidth(0.02f);


        canvas.drawLine(0.01f, 0, 0, 1f, paint);
        canvas.drawLine(-0.01f, 0, 0, 1f, paint);



        canvas.restore();

        canvas.restore();
    }
    public void Accept(double ivalue){
        if(ivalue<=40) {
            value = ivalue;
        }else{ value=39;}
        postInvalidate();
    }

}

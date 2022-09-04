package com.example.imtcalculator.stepscounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.imtcalculator.R;

public class ShowActivity extends AppCompatActivity {
    private TextView textview_results_total_steps, textview_results_walking_steps, textview_results_jogging_steps, textview_results_running_steps,transfer_to_next_page_text,
            textview_results_total_distance, textview_results_average_speed, textview_results_average_frequency, textview_results_burned_calories, textview_results_total_moving_time;
    private TextView textView_amount_steps, textView_type_of_step,
            textView_pedometer_is_running, textView_pedometer_toggle_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        getIntentMain();
    }
    String I1,I2,I3,I4,I5,I6,I7,I8,I9,Name;
    String xt;
    double x;
    @SuppressLint("SetTextI18n")
    private void getIntentMain(){
        Intent i=getIntent();
        if(i!=null){
            Name=  i.getStringExtra("dates");
            I1=  i.getStringExtra("i1");
            I2=  i.getStringExtra("i2");
            I3=  i.getStringExtra("i3");
            I4=  i.getStringExtra("i4");
            I5=  i.getStringExtra("i5");
            I6=  i.getStringExtra("i6");
            I7=  i.getStringExtra("i7");
            I8=  i.getStringExtra("i8");
            I9=  i.getStringExtra("i9");

            textView_amount_steps = findViewById(R.id.textview_amount_steps);
            textView_type_of_step = findViewById(R.id.textview_pedometer_type_of_step);
            textView_pedometer_is_running = findViewById(R.id.textview_pedometer_isRunning);

            textview_results_total_steps = findViewById(R.id.textview_results_total_steps);
            textview_results_walking_steps = findViewById(R.id.textview_results_walking_steps);
            textview_results_jogging_steps = findViewById(R.id.textview_results_jogging_steps);
            textview_results_running_steps = findViewById(R.id.textview_results_running_steps);
            textview_results_total_distance = findViewById(R.id.textview_results_total_distance);
            textview_results_average_speed = findViewById(R.id.textview_results_average_speed);
            textview_results_average_frequency = findViewById(R.id.textview_results_average_frequency);
            textview_results_burned_calories = findViewById(R.id.textview_results_burned_calories);
            textview_results_total_moving_time = findViewById(R.id.textview_results_total_moving_time);

            textview_results_total_steps.setText(I1);
            textview_results_total_distance.setText(I2);
            textview_results_average_speed.setText(I3);
            textview_results_average_frequency.setText(I4);
            textview_results_burned_calories.setText(I5);
            textview_results_total_moving_time.setText(I6);
            textview_results_walking_steps.setText(I7);
            textview_results_jogging_steps.setText(I8);
            textview_results_running_steps.setText(I9);


        }

    }
}
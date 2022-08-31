package com.example.imtcalculator.stepscounter.maininfo;

import androidx.cardview.widget.CardView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.SENSOR_SERVICE;

import com.example.imtcalculator.R;
import com.example.imtcalculator.stepscounter.AccelerationData;
import com.example.imtcalculator.stepscounter.StepDetector;
import com.example.imtcalculator.stepscounter.StepListener;
import com.example.imtcalculator.stepscounter.StepType;

public class StepsFragmentCount extends Fragment implements StepListener,SensorEventListener  {

    private  int allsteps;
    private double distance;
    private double midllespeed;
    private double timewalk;
    private double lostcalorian;
    public String dateandtime;

    public StepsFragmentCount() {
    }

    public StepsFragmentCount(int allsteps, double distance, double midllespeed, double timewalk, double lostcalorian) {
        this.allsteps = allsteps;
        this.distance = distance;
        this.midllespeed = midllespeed;
        this.timewalk = timewalk;
        this.lostcalorian = lostcalorian;
    }

    private static final String TAG = "PedometerFragment";

    private CardView cardViewToggleStepCounting;
    private TextView textView_amount_steps, textView_type_of_step,
            textView_pedometer_is_running, textView_pedometer_toggle_text;

    // Ergebnisse - Textviews
    private TextView textview_results_total_steps, textview_results_walking_steps, textview_results_jogging_steps, textview_results_running_steps,
            textview_results_total_distance, textview_results_average_speed, textview_results_average_frequency, textview_results_burned_calories, textview_results_total_moving_time;

    // ViewModel - speichert alle relevanten Daten hier. --> Gehen nicht verloren wenn
    // das Fragment neu erstellt wird (Orientierung ändert sich; App im Hintergrund etc.)
    private ViewModelCounter mViewModelCounter;


    public static StepsFragmentCount newInstance() {
        return new StepsFragmentCount();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stepsfragmentcount, container, false);

        cardViewToggleStepCounting = view.findViewById(R.id.btn_pedometer_toggle_tracking);
        cardViewToggleStepCounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mViewModelCounter.isCountingSteps()) stopCounting();
                else startCounting();
            }
        });
        textView_pedometer_toggle_text = view.findViewById(R.id.textview_pedometer_toggle_text);

        textView_amount_steps = view.findViewById(R.id.textview_amount_steps);
        textView_type_of_step = view.findViewById(R.id.textview_pedometer_type_of_step);
        textView_pedometer_is_running = view.findViewById(R.id.textview_pedometer_isRunning);

        textview_results_total_steps = view.findViewById(R.id.textview_results_total_steps);
        textview_results_walking_steps = view.findViewById(R.id.textview_results_walking_steps);
        textview_results_jogging_steps = view.findViewById(R.id.textview_results_jogging_steps);
        textview_results_running_steps = view.findViewById(R.id.textview_results_running_steps);
        textview_results_total_distance = view.findViewById(R.id.textview_results_total_distance);
        textview_results_average_speed = view.findViewById(R.id.textview_results_average_speed);
        textview_results_average_frequency = view.findViewById(R.id.textview_results_average_frequency);
        textview_results_burned_calories = view.findViewById(R.id.textview_results_burned_calories);
        textview_results_total_moving_time = view.findViewById(R.id.textview_results_total_moving_time);

        if(mViewModelCounter.getSensorManager() == null) {
            mViewModelCounter.setSensorManager((SensorManager) getActivity().getSystemService(SENSOR_SERVICE));
        }
        if(mViewModelCounter.getAccelerationSensor() == null){
            if(mViewModelCounter.getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                mViewModelCounter.setAccelerationSensor(mViewModelCounter.getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            }
        }
        if(mViewModelCounter.getStepDetector() == null){
            mViewModelCounter.setStepDetector(new StepDetector());
        }
        mViewModelCounter.getStepDetector().registerStepListener(this);

        if(mViewModelCounter.getAccelerationDataArrayList() == null){
            mViewModelCounter.setAccelerationDataArrayList(new ArrayList<AccelerationData>());
        }

        if(mViewModelCounter.isCountingSteps()){
            textView_pedometer_toggle_text.setText(getResources().getText(R.string.disable_pedometer));
            textView_pedometer_is_running.setText(getResources().getText(R.string.pedometer_running));
            /*textView_pedometer_is_running.setTextColor(getResources().getColor(R.color.green));*/
            textView_amount_steps.setText(String.valueOf(mViewModelCounter.getAmountOfSteps()));
            mViewModelCounter.getSensorManager().registerListener(this, mViewModelCounter.getAccelerationSensor(), SensorManager.SENSOR_DELAY_NORMAL);
        }
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModelCounter = new ViewModelProvider(this).get(ViewModelCounter.class);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDetach() {
        mViewModelCounter.getSensorManager().unregisterListener(this);
        super.onDetach();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        AccelerationData newAccelerationData = new AccelerationData();
        newAccelerationData.setX(sensorEvent.values[0]);
        newAccelerationData.setY(sensorEvent.values[1]);
        newAccelerationData.setZ(sensorEvent.values[2]);
        newAccelerationData.setTime(sensorEvent.timestamp);

        mViewModelCounter.getAccelerationDataArrayList().add(new AccelerationData());
        mViewModelCounter.getStepDetector().addAccelerationData(newAccelerationData);

        // Vorherige Version (jetzt in StepDetector gehandhabt):
        /*
        // bei 200 Millisekunden Delay ca. 5 Sekunden
        if(mViewModel.getAccelerationDataArrayList().size() >= 25){
            sendDataArray();
        }*/
    }

/*
    private void sendDataArray(){
        mViewModel.getStepDetector().handleData(mViewModel.getAccelerationDataArrayList());
        mViewModel.getAccelerationDataArrayList().clear();
    }*/


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    public void step(AccelerationData accelerationData, StepType stepType) {
        // Step event coming back from StepDetector
        mViewModelCounter.setAmountOfSteps(mViewModelCounter.getAmountOfSteps() + 1);
        textView_amount_steps.setText(String.valueOf(mViewModelCounter.getAmountOfSteps()));
        if(stepType == StepType.WALKING) {
            mViewModelCounter.setWalkingSteps(mViewModelCounter.getWalkingSteps() + 1);
            textView_type_of_step.setText(getResources().getText(R.string.walking));
        }
        else if(stepType == StepType.JOGGING) {
            mViewModelCounter.setJoggingSteps(mViewModelCounter.getJoggingSteps() + 1);
            textView_type_of_step.setText(getResources().getText(R.string.jogging));
        }
        else {
            mViewModelCounter.setRunningSteps(mViewModelCounter.getRunningSteps() + 1);
            textView_type_of_step.setText(getResources().getText(R.string.running));
        }
    }


    private void calculateResults(){
        int totalSteps = mViewModelCounter.getAmountOfSteps();
        textview_results_total_steps.setText(String.valueOf(totalSteps));

        int walkingSteps = mViewModelCounter.getWalkingSteps();
        int joggingSteps = mViewModelCounter.getJoggingSteps();
        int runningSteps = mViewModelCounter.getRunningSteps();

        textview_results_walking_steps.setText(String.valueOf(walkingSteps));
        textview_results_jogging_steps.setText(String.valueOf(joggingSteps));
        textview_results_running_steps.setText(String.valueOf(runningSteps));

        float totalDistance = walkingSteps * 0.5f + joggingSteps * 1.0f + runningSteps * 1.5f;
        String distance = totalDistance + " v";
        textview_results_total_distance.setText(distance);

        float totalDuration = walkingSteps * 1.0f + joggingSteps * 0.75f + runningSteps * 0.5f;
        float hours = totalDuration / 3600;
        float minutes = (totalDuration % 3600) / 60;
        float seconds = totalDuration % 60;
        String duration = String.format("%.0f", hours) + "год " +
                String.format( "%.0f", minutes) + "хв " +
                String.format( "%.0f", seconds) + "с";
        textview_results_total_moving_time.setText(duration);

        // Average speed:
        String averageSpeed = String.format( "%.2f", totalDistance / totalDuration) + " м/с";
        textview_results_average_speed.setText(averageSpeed);

        // Average step frequency
        String averageStepFrequency = String.format("%.0f", totalSteps / minutes) + "Кроки/хв";
        textview_results_average_frequency.setText(averageStepFrequency);

        // Calories
        float totalCaloriesBurned = walkingSteps + 0.05f + joggingSteps * 0.1f + runningSteps * 0.2f;
        String totalCalories = String.format("%.0f", totalCaloriesBurned) + " Калорії";
        textview_results_burned_calories.setText(totalCalories);
    }


    private void resetUI(){
        mViewModelCounter.setAmountOfSteps(0);
        mViewModelCounter.setWalkingSteps(0);
        mViewModelCounter.setJoggingSteps(0);
        mViewModelCounter.setRunningSteps(0);
        textView_amount_steps.setText(String.valueOf(mViewModelCounter.getWalkingSteps()));
    }


    private void startCounting(){
        if(!mViewModelCounter.isCountingSteps()){
            try {
                resetUI();
                mViewModelCounter.getSensorManager().registerListener(this, mViewModelCounter.getAccelerationSensor(), SensorManager.SENSOR_DELAY_NORMAL);
                mViewModelCounter.setCountingSteps(true);
                textView_pedometer_toggle_text.setText(getResources().getText(R.string.disable_pedometer));
                textView_pedometer_is_running.setText(getResources().getText(R.string.pedometer_running));
               /* textView_pedometer_is_running.setTextColor(getResources().getColor(R.color.green));*/
            } catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }
    }


    private void stopCounting(){
        if(mViewModelCounter.isCountingSteps()){
            try {

                //sendDataArray();

                mViewModelCounter.getSensorManager().unregisterListener(this);
                mViewModelCounter.setCountingSteps(false);
                calculateResults();
                textView_pedometer_toggle_text.setText(getResources().getText(R.string.acitvate_pedometer));
                textView_pedometer_is_running.setText(getResources().getText(R.string.pedometer_not_running));
               /* textView_pedometer_is_running.setTextColor(getResources().getColor(R.color.red));*/
            } catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }
    }


}
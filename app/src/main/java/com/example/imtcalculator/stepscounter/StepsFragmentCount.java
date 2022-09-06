package com.example.imtcalculator.stepscounter;

import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static android.content.Context.SENSOR_SERVICE;

import com.example.imtcalculator.R;
import com.example.imtcalculator.stepscounter.maininfo.AccelerationData;
import com.example.imtcalculator.stepscounter.maininfo.BackgroundService;
import com.example.imtcalculator.stepscounter.maininfo.StepListener;
import com.example.imtcalculator.stepscounter.maininfo.StepType;
import com.example.imtcalculator.stepscounter.maininfo.Steps;
import com.example.imtcalculator.stepscounter.maininfo.ViewModelCounter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cjh.WaveProgressBarlibrary.WaveProgressBar;


public class StepsFragmentCount extends Fragment implements StepListener,SensorEventListener  {




    private static final String TAG = "PedometerFragment";

    private CardView cardViewToggleStepCounting;
    private TextView textView_amount_steps, textView_type_of_step,
            textView_pedometer_is_running, textView_pedometer_toggle_text;


    private TextView textview_results_total_steps, textview_results_walking_steps, textview_results_jogging_steps, textview_results_running_steps,transfer_to_next_page_text,
            textview_results_total_distance, textview_results_average_speed, textview_results_average_frequency, textview_results_burned_calories, textview_results_total_moving_time;

    private ViewModelCounter mViewModelCounter;
    WaveProgressBar waveProgressBar;

    public static StepsFragmentCount newInstance() {
        return new StepsFragmentCount();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stepsfragmentcount, container, false);

        cardViewToggleStepCounting = view.findViewById(R.id.btn_pedometer_toggle_tracking);

        textView_pedometer_toggle_text = view.findViewById(R.id.textview_pedometer_toggle_text);
        transfer_to_next_page_text=view.findViewById(R.id.transfer_to_next_page_text);
        transfer_to_next_page_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ListActivity.class);

                startActivity(intent);

            }
        });
        textView_amount_steps = view.findViewById(R.id.textview_amount_steps);
        textView_type_of_step = view.findViewById(R.id.textview_pedometer_type_of_step);
        textView_pedometer_is_running = view.findViewById(R.id.textview_pedometer_isRunning);

         waveProgressBar=view.findViewById(R.id.waveProgressbar);


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
        init();
        Glasses();
        cardViewToggleStepCounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Intent intent = new Intent(getContext(), BackgroundService.class);
                if(mViewModelCounter.isCountingSteps()) {stopCounting();getActivity().stopService(intent);


                    try{
                        final String ANDROID_ID = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                      /*  DatabaseReference myRef = database.getReference();*/
                        DatabaseReference   mDataBase=FirebaseDatabase.getInstance().getReference(ANDROID_ID);
                        String id=mDataBase.getKey();
                        String results_total_steps=String.valueOf(textview_results_total_steps.getText());
                        String results_total_distance=String.valueOf(textview_results_total_distance.getText());
                        String results_average_speed=String.valueOf(textview_results_average_speed.getText());
                        String results_average_frequency=String.valueOf(textview_results_average_frequency.getText());
                        String results_burned_calories=  String.valueOf(textview_results_burned_calories.getText());
                        String results_total_moving_time=  String.valueOf(textview_results_total_moving_time.getText());
                        String results_results_jogging_steps=  String.valueOf(textview_results_jogging_steps.getText());
                        String results_running_steps=  String.valueOf(textview_results_running_steps.getText());
                        String results_walking_steps=  String.valueOf(textview_results_walking_steps.getText());
                          /*ArrayList<Steps> arrayList=new ArrayList<>();*/
                        Date currentDate = new Date();
                // Форматирование времени как "день.месяц.год"
                        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                        String dateText = dateFormat.format(currentDate);
                // Форматирование времени как "часы:минуты:секунды"
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        String timeText = timeFormat.format(currentDate);
                        String dates=dateText+" "+timeText;
                        Steps steps=new Steps(id,dates,results_total_steps,results_total_distance,results_average_speed,results_average_frequency,results_burned_calories,results_total_moving_time,
                                results_results_jogging_steps,results_running_steps,results_walking_steps);

                        /*arrayList.add(new Steps(id,dates,results_total_steps,results_total_distance,results_average_speed,results_average_frequency,results_burned_calories,results_total_moving_time));
                       */ mDataBase.push().setValue(steps);
                     //   Toast.makeText(getContext(), "put in DataBase", Toast.LENGTH_LONG).show();


                    }catch(Exception e){e.printStackTrace();}
                   // Toast.makeText(getContext(), "\tERROR\nCan`t put in DataBase", Toast.LENGTH_LONG).show();
                    }

                else {startCounting();   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ContextCompat.startForegroundService(getContext(), intent);
                } else {
                    getActivity().startService(intent);
                }
            }
            }
        });
        return view;
    }
    private float mBorderRadius;// = dp2px(2);
    private float mHaftBorderRadius = mBorderRadius / 2;
    private float mAngularVelocity = 2.0f;
    private float mBeerProgressHeight = 50;
    private int mBorderWidth;// = dp2px(3);
    private int mBeerWidth;
    private int mBeerHeight;

    private int mAngle = 0;
    private int mMax = 100;
    private int mBeerProgress = 0;
    private int mBubbleCount;
    public void setBeerProgress(int beerProgress,View view) {
        mBeerProgress = beerProgress;
        if (mBeerProgress > mMax){
            mBeerProgress = mMax;
        }
        if (mBeerProgress < 0){
            mBeerProgress = 0;
        }
        float pecent = mBeerProgress * 1.0f / mMax;
        mBeerProgressHeight = pecent * mBeerHeight;
     view.invalidate();
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
        String distance = totalDistance + " м";
        textview_results_total_distance.setText(distance);

        float totalDuration = walkingSteps * 1.0f + joggingSteps * 0.75f + runningSteps * 0.5f;
        float hours = totalDuration / 3600;
        float minutes = (totalDuration % 3600) / 60;
        float seconds = totalDuration % 60;
        String duration = String.format("%.0f", hours) + " год " +
                String.format( "%.0f", minutes) + " хв " +
                String.format( "%.0f", seconds) + " с";
        textview_results_total_moving_time.setText(duration);

        // Average speed:
        String averageSpeed = String.format( "%.0f", totalDistance / totalDuration) + " м/с";
        textview_results_average_speed.setText(averageSpeed);

        // Average step frequency
        String averageStepFrequency = String.format("%.0f", totalSteps / minutes) + " rроки/хв";
        textview_results_average_frequency.setText(averageStepFrequency);

        // Calories
        float totalCaloriesBurned = walkingSteps + 0.05f + joggingSteps * 0.1f + runningSteps * 0.2f;
        String totalCalories = String.format("%.0f", totalCaloriesBurned) + " rалорії";
        textview_results_burned_calories.setText(totalCalories);
    }


    private void resetUI(){
        mViewModelCounter.setAmountOfSteps(0);
        mViewModelCounter.setWalkingSteps(0);
        mViewModelCounter.setJoggingSteps(0);
        mViewModelCounter.setRunningSteps(0);
        textView_amount_steps.setText(String.valueOf(mViewModelCounter.getWalkingSteps()));
    }


    public void startCounting(){
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


    public void stopCounting(){
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

    public ArrayAdapter<Steps> adapter;
    private List<String> listData;
    private List<Steps> listTemp;
    DatabaseReference myRef;
    private void init() {

        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        final String ANDROID_ID = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(ANDROID_ID);
        getDataFromDB();



    }
    private void getDataFromDB() {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listData.size() > 0) listData.clear();
                if (listTemp.size() > 0) listData.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Steps formula = ds.getValue(Steps.class);
                    assert formula != null;

                    listData.add(formula.results_total_steps);

                    listTemp.add(formula);
                    textview_results_total_steps.setText(formula.results_total_steps);
                    textview_results_total_distance.setText(formula.results_total_distance);
                    textview_results_average_speed.setText(formula.results_average_speed);
                    textview_results_average_frequency.setText(formula.results_average_frequency);
                    textview_results_burned_calories.setText(formula.results_burned_calories);
                    textview_results_total_moving_time.setText(formula.results_total_moving_time);
                    textview_results_jogging_steps.setText(formula.results_jogging_steps);
                    textview_results_running_steps.setText(formula.results_running_steps);
                    textview_results_walking_steps.setText(formula.results_total_steps);
                    textview_results_burned_calories.setText(formula.results_burned_calories);
                    textview_results_total_moving_time.setText(formula.results_total_moving_time);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myRef.addValueEventListener(eventListener);
    }

private void Glasses(){
    waveProgressBar.setProgress(50);
    waveProgressBar.setWaveDuration(100);
    waveProgressBar.setBorderColor(R.color.blue);


}


}
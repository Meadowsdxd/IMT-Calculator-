package com.example.imtcalculator.stepscounter;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import static android.content.Context.SENSOR_SERVICE;
import com.example.imtcalculator.R;
import com.example.imtcalculator.stepscounter.glass.glasswater;
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
    private TextView textView_amount_steps, textView_type_of_step,textview_percebt_in_l,
            textView_pedometer_is_running, textView_pedometer_toggle_text;
    private Button addFirstWater,addSecondWater,addThirdWater,addLastWater;

    private TextView textview_results_total_steps, textview_results_walking_steps, textview_results_jogging_steps, textview_results_running_steps,transfer_to_next_page_text,
            textview_results_total_distance, textview_results_average_speed, textview_results_average_frequency, textview_results_burned_calories, textview_results_total_moving_time;
    private ViewModelCounter mViewModelCounter;
    WaveProgressBar waveProgressBar;
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stepsfragmentcount, container, false);

        cardViewToggleStepCounting = view.findViewById(R.id.btn_pedometer_toggle_tracking);

        textView_pedometer_toggle_text = view.findViewById(R.id.textview_pedometer_toggle_text);
       transfer_to_next_page_text=view.findViewById(R.id.transfer_to_next_page_text);
       transfer_to_next_page_text.setOnClickListener(view1 -> {
           Intent intent=new Intent(getContext(), ListActivity.class);
           startActivity(intent);

       });
        textView_amount_steps = view.findViewById(R.id.textview_amount_steps);
        textView_type_of_step = view.findViewById(R.id.textview_pedometer_type_of_step);
        textView_pedometer_is_running = view.findViewById(R.id.textview_pedometer_isRunning);

         waveProgressBar=view.findViewById(R.id.waveProgressbar);

        addFirstWater=view.findViewById(R.id.firstml);
        addSecondWater=view.findViewById(R.id.secondml);
        addThirdWater=view.findViewById(R.id.thirdml);
        addLastWater=view.findViewById(R.id.ffortml);
        textview_results_total_steps = view.findViewById(R.id.textview_results_total_steps);
        textview_results_walking_steps = view.findViewById(R.id.textview_results_walking_steps);
        textview_results_jogging_steps = view.findViewById(R.id.textview_results_jogging_steps);
        textview_results_running_steps = view.findViewById(R.id.textview_results_running_steps);
        textview_results_total_distance = view.findViewById(R.id.textview_results_total_distance);
        textview_results_average_speed = view.findViewById(R.id.textview_results_average_speed);
        textview_results_average_frequency = view.findViewById(R.id.textview_results_average_frequency);
        textview_results_burned_calories = view.findViewById(R.id.textview_results_burned_calories);
        textview_results_total_moving_time = view.findViewById(R.id.textview_results_total_moving_time);
        textview_percebt_in_l=view.findViewById(R.id.textview_percebt_in_l);
        if(mViewModelCounter.getSensorManager() == null) {
            mViewModelCounter.setSensorManager((SensorManager) requireActivity().getSystemService(SENSOR_SERVICE));
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
            mViewModelCounter.setAccelerationDataArrayList(new ArrayList<>());
        }

        if(mViewModelCounter.isCountingSteps()){
            textView_pedometer_toggle_text.setText(getResources().getText(R.string.disable_pedometer));
            textView_pedometer_is_running.setText(getResources().getText(R.string.pedometer_running));
            textView_amount_steps.setText(String.valueOf(mViewModelCounter.getAmountOfSteps()));
            mViewModelCounter.getSensorManager().registerListener(this, mViewModelCounter.getAccelerationSensor(), SensorManager.SENSOR_DELAY_NORMAL);
        }
        init();
         glass.setFirst(1);
        Glasses();
        start();
        cardViewToggleStepCounting.setOnClickListener(view12 -> {
            if(mViewModelCounter.isCountingSteps()) {stopCounting(); requireActivity().stopService(new Intent(getActivity(),BackgroundService.class));
            SaveInDB();
                }

            else {startCounting();
             requireActivity().startService(new Intent(getActivity(),BackgroundService.class));
        }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModelCounter = new ViewModelProvider(this).get(ViewModelCounter.class);
        super.onCreate(savedInstanceState);
    }
    private void SaveInDB(){
        try{
            @SuppressLint("HardwareIds") final String ANDROID_ID = Settings.Secure.getString(requireContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            DatabaseReference   mDataBase=FirebaseDatabase.getInstance().getReference(ANDROID_ID);
            String id=mDataBase.getKey();
            String results_total_steps=String.valueOf(textview_results_total_steps.getText());
            String results_total_distance=String.valueOf(distance);
            String results_average_speed=String.valueOf(averageSpeed);
            String results_average_frequency=String.valueOf(averageStepFrequency);
            String results_burned_calories=  String.valueOf(totalCalories);
            String results_total_moving_time=  String.valueOf(textview_results_total_moving_time.getText());
            String results_results_jogging_steps=  String.valueOf(textview_results_jogging_steps.getText());
            String results_running_steps=  String.valueOf(textview_results_running_steps.getText());
            String results_walking_steps=  String.valueOf(textview_results_walking_steps.getText());

            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);

            DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String timeText = timeFormat.format(currentDate);
            String dates=dateText+" "+timeText;
            Steps steps=new Steps(id,dates,results_total_steps,results_total_distance,results_average_speed,results_average_frequency,results_burned_calories,results_total_moving_time,
                    results_results_jogging_steps,results_running_steps,results_walking_steps,String.valueOf(hours),String.valueOf(minutes),String.valueOf(seconds));

            mDataBase.push().setValue(steps);

        }catch(Exception e){e.printStackTrace();}

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

    }


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

    String distance;
    String duration;
    float hours, minutes,seconds;
    String averageSpeed;
    String averageStepFrequency;
    String totalCalories;
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void calculateResults(){
        Resources resources=getResources();
        int totalSteps = mViewModelCounter.getAmountOfSteps();
        textview_results_total_steps.setText(String.valueOf(totalSteps));

        int walkingSteps = mViewModelCounter.getWalkingSteps();
        int joggingSteps = mViewModelCounter.getJoggingSteps();
        int runningSteps = mViewModelCounter.getRunningSteps();

        textview_results_walking_steps.setText(String.valueOf(walkingSteps));
        textview_results_jogging_steps.setText(String.valueOf(joggingSteps));
        textview_results_running_steps.setText(String.valueOf(runningSteps));

        float totalDistance = walkingSteps * 0.5f + joggingSteps * 1.0f + runningSteps * 1.5f;
         distance = String.valueOf(totalDistance);
        textview_results_total_distance.setText(distance + " "+resources.getString(R.string.totalDistance));

        float totalDuration = walkingSteps * 1.0f + joggingSteps * 0.75f + runningSteps * 0.5f;
         hours = totalDuration / 3600;
         minutes = (totalDuration % 3600) / 60;
         seconds = totalDuration % 60;
         duration = String.format("%.0f", hours) + " " +resources.getString(R.string.hours)+" " +
                String.format( "%.0f", minutes) +  " " +resources.getString(R.string.minutes)+" " +
                String.format( "%.0f", seconds) +  " " +resources.getString(R.string.seconds)+" " ;
        textview_results_total_moving_time.setText(duration);
         averageSpeed = String.format( "%.0f", totalDistance / totalDuration) ;
        if(averageSpeed.equals("NaN")){
            averageSpeed= String.valueOf(0);
            textview_results_average_speed.setText("0 "+resources.getString(R.string.mc));
        }else
            textview_results_average_speed.setText(averageSpeed+ " "+resources.getString(R.string.mc));
         averageStepFrequency = String.format("%.0f", totalSteps / minutes) ;
        if(averageStepFrequency.equals("NaN")){
            averageStepFrequency= String.valueOf(0);
            textview_results_average_frequency.setText("0 "+resources.getString(R.string.stepsmin));
        }else
        textview_results_average_frequency.setText(averageStepFrequency+" "+ resources.getString(R.string.stepsmin));

        // Calories
        float totalCaloriesBurned = walkingSteps + 0.05f + joggingSteps * 0.1f + runningSteps * 0.2f;
         totalCalories = String.format("%.0f", totalCaloriesBurned) ;
        textview_results_burned_calories.setText(totalCalories+ " "+ resources.getString(R.string.kalorian));
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
            } catch (Exception e){
                Log.e(TAG, e.getMessage());
            }

        }
    }


    public void stopCounting(){
        if(mViewModelCounter.isCountingSteps()){
            try {
                mViewModelCounter.getSensorManager().unregisterListener(this);
                mViewModelCounter.setCountingSteps(false);
                calculateResults();
                textView_pedometer_toggle_text.setText(getResources().getText(R.string.acitvate_pedometer));
                textView_pedometer_is_running.setText(getResources().getText(R.string.pedometer_not_running));
            } catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        }
    }
    private List<String> listData;
    private List<Steps> listTemp;
    DatabaseReference myRef;
    private void init() {
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        @SuppressLint("HardwareIds") final String ANDROID_ID = Settings.Secure.getString(requireContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(ANDROID_ID);
        getDataFromDB();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        calculateResults();
        SaveInDB();
        stop();
    }

    private void getDataFromDB() {
        ValueEventListener eventListener = new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Resources resources=getResources();
                if (listData.size() > 0) listData.clear();
                if (listTemp.size() > 0) listData.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Steps formula = ds.getValue(Steps.class);
                    assert formula != null;
                    listData.add(formula.results_total_steps);
                    listTemp.add(formula);
                    textview_results_total_steps.setText(formula.results_total_steps);
                    textview_results_total_distance.setText(formula.results_total_distance+ " "+resources.getString(R.string.totalDistance));
                    textview_results_average_speed.setText(formula.results_average_speed+ " "+resources.getString(R.string.mc));
                    textview_results_average_frequency.setText(formula.results_average_frequency+" "+ resources.getString(R.string.stepsmin));
                    textview_results_burned_calories.setText(formula.results_burned_calories+ " "+ resources.getString(R.string.kalorian));
                    textview_results_total_moving_time.setText(formula.results_total_moving_time);
                    textview_results_jogging_steps.setText(formula.results_jogging_steps);
                    textview_results_running_steps.setText(formula.results_running_steps);
                    textview_results_walking_steps.setText(formula.results_total_steps);
                    @SuppressLint("DefaultLocale") String totatime=String.format("%.0f", Float.parseFloat(formula.hours)) + " " +resources.getString(R.string.hours)+" " + String.format( "%.0f", Float.parseFloat(formula.minutes)) +  " " +resources.getString(R.string.minutes)+" " + String.format( "%.0f", Float.parseFloat(formula.seconds)) +  " " +resources.getString(R.string.seconds)+" ";
                    textview_results_total_moving_time.setText(totatime);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myRef.addValueEventListener(eventListener);
    }

int i=0;
    glasswater glass=new glasswater();

    private Timer mTimer;
    private TimerTask mTimerTask;
    double result;
    double waterdrank;
@RequiresApi(api = Build.VERSION_CODES.N)
private void Glasses(){
        requireActivity().startService(new Intent(getActivity(),BackgroundService.class));


    mTimer = new Timer();
    mTimerTask = new TimerTask() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            i=i+3;
            requireActivity().runOnUiThread(() -> {
            addFirstWater.setOnClickListener(view -> {
                double res=glass.getResult()+((0.25*100)/2.4);
                if(res>100){res=100;}
                glass.setResult(res);
                i=0;
                glass.setFirst((2.4*res)/100);
            });
                addSecondWater.setOnClickListener(view -> {
                    double res=glass.getResult()+((0.33*100)/2.4);
                    if(res>100){res=100;}
                    glass.setResult(res);
                    i=0;
                    glass.setFirst((2.4*res)/100);
                });
                addThirdWater.setOnClickListener(view -> {
                    double res=glass.getResult()+((0.5*100)/2.4);
                    if(res>100){res=100;}
                    glass.setResult(res);
                    i=0;
                    glass.setFirst((2.4*res)/100);
                });
                addLastWater.setOnClickListener(view -> {
                    double res=glass.getResult()+((100)/2.4);
                    if(res>100){res=100;}
                    glass.setResult(res);
                    i=0;
                    glass.setFirst((2.4*res)/100);
                });

            double percent=((glass.getFirst())*100)/2.4;
            result=percent-i;
            if(result<0){glass.setResult(0);} else glass.setResult(result);
                waterdrank= ((2.4*glass.getResult())/100);
                DecimalFormat formattedDouble = new DecimalFormat("#0.00");
                textview_percebt_in_l.setText((formattedDouble.format(waterdrank)));


            });



        }
    };
     Handler handler = new Handler();
    new Thread(() -> {
        while (glass.getFirst() < 100) {

            handler.post(() -> waveProgressBar.setProgress((int) glass.getResult()));
            waterdrank= ((2.4*glass.getResult())/100);
            DecimalFormat formattedDouble = new DecimalFormat("#0.00");
            textview_percebt_in_l.setText((formattedDouble.format(waterdrank)));
            try {
                Thread.sleep(28);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }).start();
}



    public void start() {
        mTimer.schedule(mTimerTask, 0, 180000);
    }

    public void stop() {
        mTimer.cancel();
    }


}



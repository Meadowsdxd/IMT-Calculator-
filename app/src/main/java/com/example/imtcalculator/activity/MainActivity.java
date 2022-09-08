package com.example.imtcalculator.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.imtcalculator.R;
import com.example.imtcalculator.fragment.MainFragment;
import com.example.imtcalculator.stepscounter.StepsFragmentCount;


import java.util.ArrayList;
import java.util.List;

import cjh.WaveProgressBarlibrary.WaveProgressBar;


public class MainActivity extends AppCompatActivity {
private ViewPager pager;
    private PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> list = new ArrayList<>();
        list.add(new MainFragment());
        list.add(new StepsFragmentCount());
        pager = findViewById(R.id.pager);
        adapter = new Slider(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }

}







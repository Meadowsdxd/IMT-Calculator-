package com.example.imtcalculator.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.imtcalculator.R;
import com.example.imtcalculator.fragment.MainFragment;
import com.example.imtcalculator.stepscounter.maininfo.StepsFragmentCount;

import java.util.ArrayList;
import java.util.List;

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
    }
}







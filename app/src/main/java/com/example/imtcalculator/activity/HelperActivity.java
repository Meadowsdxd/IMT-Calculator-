package com.example.imtcalculator.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.imtcalculator.R;
import com.example.imtcalculator.fragment.RecyclerFragment;

public class HelperActivity extends SingleFragment {


    @Override
    protected Fragment createFragmenty() {
        return new RecyclerFragment().newInstance();
    }


}
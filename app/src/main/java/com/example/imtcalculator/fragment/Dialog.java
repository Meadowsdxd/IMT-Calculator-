package com.example.imtcalculator.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.imtcalculator.R;
import com.example.imtcalculator.activity.HelperActivity;

public class Dialog extends DialogFragment implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
double reuslt=0;

    public double getReuslt() {
        return reuslt;
    }

    public void setReuslt(double reuslt) {
        this.reuslt = reuslt;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog, null);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);
        return v;
    }

    public void onClick(View v) {
        Toast.makeText(getContext(),"Чудово",Toast.LENGTH_LONG).show();
        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        Intent intent=new Intent(getContext(),HelperActivity.class);
        intent.putExtra("result",reuslt);

        startActivity(intent);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}
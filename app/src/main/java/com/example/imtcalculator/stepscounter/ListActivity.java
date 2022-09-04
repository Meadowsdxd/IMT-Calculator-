package com.example.imtcalculator.stepscounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.imtcalculator.R;
import com.example.imtcalculator.stepscounter.maininfo.Steps;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static ListView listView;
    public ArrayAdapter<Steps> adapter;
    private List<String> listData;
    private List<Steps> listTemp;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();


    }

    private void init() {
        listView = findViewById(R.id.list);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();

        final String ANDROID_ID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTemp);
        listView.setAdapter(adapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(ANDROID_ID);
        getDataFromDB();
        setOnClickItem();


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

                    listData.add(formula.dates);

                    listTemp.add(formula);


                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.addValueEventListener(eventListener);
    }

    private void setOnClickItem() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Steps steps=listTemp.get(position);
                Intent i=new Intent(ListActivity.this, ShowActivity.class);
                i.putExtra("dates",steps.dates);
                i.putExtra("i1",steps.results_total_steps);
                i.putExtra("i2",steps.results_total_distance);
                i.putExtra("i3",steps.results_average_speed);
                i.putExtra("i4",steps.results_average_frequency);
                i.putExtra("i5",steps.results_burned_calories);
                i.putExtra("i6",steps.results_total_moving_time);
                i.putExtra("i7",steps.results_jogging_steps);
                i.putExtra("i8",steps.results_running_steps);
                i.putExtra("i9",steps.results_walking_steps);
                startActivity(i);
            }
        });


    }
}
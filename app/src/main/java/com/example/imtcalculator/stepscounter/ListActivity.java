package com.example.imtcalculator.stepscounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.imtcalculator.R;
import com.example.imtcalculator.more.Card;
import com.example.imtcalculator.stepscounter.maininfo.StepsFragmentCount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    public static ListView listView;
    public ArrayAdapter<StepsFragmentCount> adapter;
    private List<String> listData;
    private List<StepsFragmentCount> listTemp;
    DatabaseReference myRef;
    private String FORMULI = "items";

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


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTemp);
        listView.setAdapter(adapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FORMULI);
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
                    StepsFragmentCount formula = ds.getValue(StepsFragmentCount.class);
                    assert formula != null;
                    String name, i1;
                    listData.add(formula.dateandtime);

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

    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
     /*           Formula formula=listTemp.get(position);
                Intent i=new Intent(ReadActivity.this,ShowActivity.class);
                i.putExtra("name",formula.name);
                i.putExtra("i1",formula.KVT);
                i.putExtra("i2",formula.Oborot);
                i.putExtra("i3",formula.i);
                i.putExtra("i4",formula.KPD);
                i.putExtra("i5",formula.KOEF);
                i.putExtra("i6",formula.In);
                i.putExtra("i7",formula.Om);
                i.putExtra("i8",formula.Old);*/
             /*   startActivity(i);*/
            }
        });
    }
}
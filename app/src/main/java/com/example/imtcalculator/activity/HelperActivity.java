package com.example.imtcalculator.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.imtcalculator.R;

public class HelperActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyCardData[] myCardData = new MyCardData[]{
                new MyCardData("Avengers","2019 film",R.drawable.men),
                new MyCardData("Venom","2018 film",R.drawable.men),
                new MyCardData("Batman Begins","2005 film",R.drawable.men),
                new MyCardData("Jumanji","2019 film",R.drawable.men),
                new MyCardData("Good Deeds","2012 film",R.drawable.men),
                new MyCardData("Hulk","2003 film",R.drawable.men),
                new MyCardData("Avatar","2009 film",R.drawable.men),
        };

        MyCardAdapter myCardAdapter = new MyCardAdapter(myCardData,HelperActivity.this);
        recyclerView.setAdapter(myCardAdapter);
    }
/*

    @Override
    protected Fragment createFragmenty() {
        return new RecyclerFragment().newInstance();
    }

*/

}
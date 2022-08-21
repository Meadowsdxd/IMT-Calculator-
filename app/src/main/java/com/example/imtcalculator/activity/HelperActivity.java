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
                new MyCardData("https://wowbody.com","Прагнення до здорового, красивого та яскравого способу життя. Тренуй тіло, впроваджуй нові звички, готуй корисну страву та харчуйся із задоволенням. Будь WOW!",R.drawable.wowbody),
                new MyCardData("Зхуднення 2","Зхуднення 2",R.drawable.men),
                new MyCardData("Зхуднення 3","Зхуднення 3",R.drawable.men),
                new MyCardData("Зхуднення 4","Зхуднення 4",R.drawable.men),
                new MyCardData("Зхуднення 5","Зхуднення 1",R.drawable.men),
                new MyCardData("Зхуднення 6" ,"Зхуднення 1",R.drawable.men),
                new MyCardData("Зхуднення 7","Зхуднення 1",R.drawable.men),
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
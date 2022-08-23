package com.example.imtcalculator.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.imtcalculator.R;

public class HelperActivity extends AppCompatActivity{
    double result=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyCardData[] myCardData = new MyCardData[0];
      Bundle arguments = getIntent().getExtras();
         result = (double) arguments.get("result");

       if(result<18){ myCardData = new MyCardData[]{
                new MyCardData("https://wowbody.com", "Прагнення до здорового, красивого та яскравого способу життя. Тренуй тіло, впроваджуй нові звички, готуй корисну страву та харчуйся із задоволенням. Будь WOW!", R.drawable.wowbody),
                new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/", "Дієта для лінивих: які правила допоможуть скинути зайву вагу без зусиль", R.drawable.img),
                new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", "Как похудеть на 30 кг. Моя история похудения. Жиросжигатель", R.drawable.img_1),
                new MyCardData("Зхуднення 4", "Зхуднення 4", R.drawable.men),
                new MyCardData("Зхуднення 5", "Зхуднення 1", R.drawable.men),
                new MyCardData("Зхуднення 6", "Зхуднення 1", R.drawable.men),
                new MyCardData("Зхуднення 7", "Зхуднення 1", R.drawable.men),
        };}else
        if(result>=18.5&&result<25) {
             myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", "Прагнення до здорового, красивого та яскравого способу життя. Тренуй тіло, впроваджуй нові звички, готуй корисну страву та харчуйся із задоволенням. Будь WOW!", R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/", "Дієта для лінивих: які правила допоможуть скинути зайву вагу без зусиль", R.drawable.img),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", "Как похудеть на 30 кг. Моя история похудения. Жиросжигатель", R.drawable.img_1),
                    new MyCardData("Зхуднення 4", "Зхуднення 4", R.drawable.men),
                    new MyCardData("Зхуднення 5", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 6", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 7", "Зхуднення 1", R.drawable.men),
            };
        }else if(result>=25&&result<30) {
            myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", "Прагнення до здорового, красивого та яскравого способу життя. Тренуй тіло, впроваджуй нові звички, готуй корисну страву та харчуйся із задоволенням. Будь WOW!", R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/", "Дієта для лінивих: які правила допоможуть скинути зайву вагу без зусиль", R.drawable.img),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", "Как похудеть на 30 кг. Моя история похудения. Жиросжигатель", R.drawable.img_1),
                    new MyCardData("Зхуднення 4", "Зхуднення 4", R.drawable.men),
                    new MyCardData("Зхуднення 5", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 6", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 7", "Зхуднення 1", R.drawable.men),
            };}else if(result>=30&&result<35) {
            myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", "Прагнення до здорового, красивого та яскравого способу життя. Тренуй тіло, впроваджуй нові звички, готуй корисну страву та харчуйся із задоволенням. Будь WOW!", R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/", "Дієта для лінивих: які правила допоможуть скинути зайву вагу без зусиль", R.drawable.img),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", "Как похудеть на 30 кг. Моя история похудения. Жиросжигатель", R.drawable.img_1),
                    new MyCardData("Зхуднення 4", "Зхуднення 4", R.drawable.men),
                    new MyCardData("Зхуднення 5", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 6", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 7", "Зхуднення 1", R.drawable.men),
            };}else if(result>=35&&result<40) {
            myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", "Прагнення до здорового, красивого та яскравого способу життя. Тренуй тіло, впроваджуй нові звички, готуй корисну страву та харчуйся із задоволенням. Будь WOW!", R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/", "Дієта для лінивих: які правила допоможуть скинути зайву вагу без зусиль", R.drawable.img),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", "Как похудеть на 30 кг. Моя история похудения. Жиросжигатель", R.drawable.img_1),
                    new MyCardData("Зхуднення 4", "Зхуднення 4", R.drawable.men),
                    new MyCardData("Зхуднення 5", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 6", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 7", "Зхуднення 1", R.drawable.men),
            };}else if(result>=40) {
            myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", "Прагнення до здорового, красивого та яскравого способу життя. Тренуй тіло, впроваджуй нові звички, готуй корисну страву та харчуйся із задоволенням. Будь WOW!", R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/", "Дієта для лінивих: які правила допоможуть скинути зайву вагу без зусиль", R.drawable.img),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", "Как похудеть на 30 кг. Моя история похудения. Жиросжигатель", R.drawable.img_1),
                    new MyCardData("Зхуднення 4", "Зхуднення 4", R.drawable.men),
                    new MyCardData("Зхуднення 5", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 6", "Зхуднення 1", R.drawable.men),
                    new MyCardData("Зхуднення 7", "Зхуднення 1", R.drawable.men),
            };}else  if(result==0){
            Toast.makeText(this,"Data is empty",Toast.LENGTH_LONG).show();


        }
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
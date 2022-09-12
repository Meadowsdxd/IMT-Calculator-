package com.example.imtcalculator.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.imtcalculator.R;

public class HelperActivity extends AppCompatActivity{
    public double result=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_helper);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyCardData[] myCardData = new MyCardData[0];
     Bundle arguments = getIntent().getExtras();
         double result = (double) arguments.get("result");

       if(result<18.5){ myCardData = new MyCardData[]{
               new MyCardData("https://www.youtube.com/watch?v=4s3QStdR-HU", R.string.link1, R.drawable.img_1),
            new MyCardData("https://www.youtube.com/watch?v=k9nlPis5u7s", R.string.link2, R.drawable.img_3),
               new MyCardData("https://gymmaxx.com", R.string.link3, R.drawable.gymaxx),
               new MyCardData("https://fitness.org.ua/iak-shvidko-nabrati-vagy-prikladi-menu-prodyktiv-i-vprav/",R.string.link4 , R.drawable.img_5),
               new MyCardData("https://bodia.online/subcategory/sportivni-zali-sektsiyi/trenazhernii-zal-ta-fitnes", R.string.link5, R.drawable.img_4),
               new MyCardData("https://sportzal.com.ua/ru", R.string.link6, R.drawable.sportazal),
               new MyCardData("https://5element.ua/ua/",R.string.link7, R.drawable.fiveelement),
               new MyCardData("https://www.youtube.com/watch?v=DIxS6b0ZNlE",R.string.link8 , R.drawable.inhome),
               new MyCardData("https://www.youtube.com/watch?v=Ex1XOU1wr64",R.string.link9 , R.drawable.gymfit),
               new MyCardData("https://www.youtube.com/watch?v=e1GbGNYIGB4", R.string.link10, R.drawable.popsport),

       };}else
        if(result>=18.5&&result<25) {
             myCardData = new MyCardData[]{
                    new MyCardData("https://gymmaxx.com", R.string.link3, R.drawable.gymaxx),
                    new MyCardData("https://sportzal.com.ua/ru", R.string.link6, R.drawable.sportazal),
                    new MyCardData("https://5element.ua/ua/", R.string.link7, R.drawable.fiveelement),
                    new MyCardData("https://www.youtube.com/watch?v=DIxS6b0ZNlE", R.string.link8, R.drawable.inhome),
                    new MyCardData("https://www.youtube.com/watch?v=Ex1XOU1wr64", R.string.link9, R.drawable.gymfit),
                    new MyCardData("https://www.youtube.com/watch?v=e1GbGNYIGB4",  R.string.link10, R.drawable.popsport),
            };
        }else if(result>=25&&result<30) {
            myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", R.string.link11 , R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/",R.string.link12 , R.drawable.img_2),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", R.string.link13 , R.drawable.img_3),
                    new MyCardData("https://gymmaxx.com", R.string.link3, R.drawable.gymaxx),
                    new MyCardData("https://sportzal.com.ua/ru", R.string.link6, R.drawable.sportazal),
                    new MyCardData("https://5element.ua/ua/", R.string.link7, R.drawable.fiveelement),
                    new MyCardData("https://www.youtube.com/watch?v=DIxS6b0ZNlE", R.string.link8, R.drawable.inhome),
                    new MyCardData("https://www.youtube.com/watch?v=Ex1XOU1wr64", R.string.link9, R.drawable.gymfit),
                    new MyCardData("https://www.youtube.com/watch?v=e1GbGNYIGB4",  R.string.link10, R.drawable.popsport),
                    new MyCardData("https://brovko.clinic/?gclid=CjwKCAjwgaeYBhBAEiwAvMgp2lyaIyJJuJthPyUtEhTGaTTqGGdW5VVoTBLkQ_X7FD33DBUIdpFL9hoC39cQAvD_BwE", R.string.link14, R.drawable.brovko),
                    new MyCardData("https://eurecamed.com.ua/ru/services/overweight_correction_case_2", R.string.link15, R.drawable.eureca),
            };}else if(result>=30&&result<35) {
            myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", R.string.link11 , R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/",R.string.link12 , R.drawable.img_2),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", R.string.link13 , R.drawable.img_3),
                    new MyCardData("https://gymmaxx.com", R.string.link3, R.drawable.gymaxx),
                    new MyCardData("https://sportzal.com.ua/ru", R.string.link6, R.drawable.sportazal),
                    new MyCardData("https://5element.ua/ua/", R.string.link7, R.drawable.fiveelement),
                    new MyCardData("https://www.youtube.com/watch?v=DIxS6b0ZNlE", R.string.link8, R.drawable.inhome),
                    new MyCardData("https://www.youtube.com/watch?v=Ex1XOU1wr64", R.string.link9, R.drawable.gymfit),
                    new MyCardData("https://www.youtube.com/watch?v=e1GbGNYIGB4",  R.string.link10, R.drawable.popsport),
                    new MyCardData("https://brovko.clinic/?gclid=CjwKCAjwgaeYBhBAEiwAvMgp2lyaIyJJuJthPyUtEhTGaTTqGGdW5VVoTBLkQ_X7FD33DBUIdpFL9hoC39cQAvD_BwE", R.string.link14, R.drawable.brovko),
                    new MyCardData("https://eurecamed.com.ua/ru/services/overweight_correction_case_2", R.string.link15, R.drawable.eureca),};}else if(result>=35&&result<40) {
            myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", R.string.link11 , R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/",R.string.link12 , R.drawable.img_2),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", R.string.link13 , R.drawable.img_3),
                    new MyCardData("https://gymmaxx.com", R.string.link3, R.drawable.gymaxx),
                    new MyCardData("https://sportzal.com.ua/ru", R.string.link6, R.drawable.sportazal),
                    new MyCardData("https://5element.ua/ua/", R.string.link7, R.drawable.fiveelement),
                    new MyCardData("https://www.youtube.com/watch?v=DIxS6b0ZNlE", R.string.link8, R.drawable.inhome),
                    new MyCardData("https://www.youtube.com/watch?v=Ex1XOU1wr64", R.string.link9, R.drawable.gymfit),
                    new MyCardData("https://www.youtube.com/watch?v=e1GbGNYIGB4",  R.string.link10, R.drawable.popsport),
                    new MyCardData("https://brovko.clinic/?gclid=CjwKCAjwgaeYBhBAEiwAvMgp2lyaIyJJuJthPyUtEhTGaTTqGGdW5VVoTBLkQ_X7FD33DBUIdpFL9hoC39cQAvD_BwE", R.string.link14, R.drawable.brovko),
                    new MyCardData("https://eurecamed.com.ua/ru/services/overweight_correction_case_2", R.string.link15, R.drawable.eureca),};}else if(result>=40) {
            myCardData = new MyCardData[]{
                    new MyCardData("https://wowbody.com", R.string.link11 , R.drawable.wowbody),
                    new MyCardData("https://hochu.ua/cat-health/diet-and-nutrition/article-64731-dieta-dlya-lenivyih-kakie-pravila-pomogut-sbrosit-lishney-ves-bez-usiliy/",R.string.link12 , R.drawable.img_2),
                    new MyCardData("https://www.youtube.com/watch?v=hduA_n3_qMc", R.string.link13 , R.drawable.img_3),
                    new MyCardData("https://gymmaxx.com", R.string.link3, R.drawable.gymaxx),
                    new MyCardData("https://sportzal.com.ua/ru", R.string.link6, R.drawable.sportazal),
                    new MyCardData("https://5element.ua/ua/", R.string.link7, R.drawable.fiveelement),
                    new MyCardData("https://www.youtube.com/watch?v=DIxS6b0ZNlE", R.string.link8, R.drawable.inhome),
                    new MyCardData("https://www.youtube.com/watch?v=Ex1XOU1wr64", R.string.link9, R.drawable.gymfit),
                    new MyCardData("https://www.youtube.com/watch?v=e1GbGNYIGB4",  R.string.link10, R.drawable.popsport),
                    new MyCardData("https://brovko.clinic/?gclid=CjwKCAjwgaeYBhBAEiwAvMgp2lyaIyJJuJthPyUtEhTGaTTqGGdW5VVoTBLkQ_X7FD33DBUIdpFL9hoC39cQAvD_BwE", R.string.link14, R.drawable.brovko),
                    new MyCardData("https://eurecamed.com.ua/ru/services/overweight_correction_case_2", R.string.link15, R.drawable.eureca),};}else  if(result==0){
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
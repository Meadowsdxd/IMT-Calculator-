package com.example.imtcalculator.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imtcalculator.R;
import com.example.imtcalculator.more.Card;

public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.ViewHolder> {
    MyCardData[] myCardData;
    Context context;
    public MyCardAdapter(MyCardData[] myCardData, HelperActivity helperActivity){
        this.myCardData=myCardData;
        this.context=helperActivity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyCardData myMovieDataList = myCardData[position];
        holder.textViewName.setText(myMovieDataList.getCardData());
        holder.textViewDate.setText(myMovieDataList.getCardData());
        holder.movieImage.setImageResource(myMovieDataList.getCardImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse( myMovieDataList.getCardName()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCardData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textdate);

        }
    }
}

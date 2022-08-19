package com.example.imtcalculator.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imtcalculator.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment {
     public static Fragment newInstance() {
    return new RecyclerFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        Info();
        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerViewAdapter(list));

        return view;
    }
    private class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private CardView mCard;
        private TextView mText;
            public RecyclerViewHolder(@NonNull View itemView) {
             super(itemView);
            }
        public RecyclerViewHolder(LayoutInflater inflater,ViewGroup container){
            super(inflater.inflate(R.layout.card_view,container,false));
             mCard=itemView.findViewById(R.id.card_container);
             mText=itemView.findViewById(R.id.text_holder);
            }
    }
    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
       private List<String> mList;
        public RecyclerViewAdapter(List<String>list) {
            this.mList=list;

        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           LayoutInflater inflater=LayoutInflater.from(getActivity());
            return new RecyclerViewHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.mText.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
   private List<String> list;
    private void Info(){
       list=new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
    }
}

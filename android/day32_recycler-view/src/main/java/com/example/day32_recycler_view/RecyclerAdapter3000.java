package com.example.day32_recycler_view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter3000 extends RecyclerView.Adapter<RecyclerAdapter3000.MyViewHolder> {

    private List<String> values;

    public RecyclerAdapter3000(List<String> values) {
        this.values = values;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.largeTextView.setText(values.get(position));
        holder.smallTextView.setText("кот");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView largeTextView;
        TextView smallTextView;

        public MyViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            largeTextView = itemView.findViewById(R.id.textViewLarge);
            smallTextView = itemView.findViewById(R.id.textViewSmall);
        }
    }

}

package com.sukrit.mckkrs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrit.mckkrs.R;

public class CompletedActivitiesAdapter extends RecyclerView.Adapter<CompletedActivitiesAdapter.MyViewHolder> {

    private Context context;

    public CompletedActivitiesAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public CompletedActivitiesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.complete_acti_adapter, parent, false);
        return new CompletedActivitiesAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull CompletedActivitiesAdapter.MyViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
//        return buyers.size();

        return  10;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }}

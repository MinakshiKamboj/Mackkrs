package com.sukrit.mckkrs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrit.mckkrs.Activities.CPDWorkShopRequestActivity;
import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.Models.FtoFCpdWorkShop;
import com.sukrit.mckkrs.R;

import java.util.ArrayList;

public class FtoFCpdWorkShopAdapter extends RecyclerView.Adapter<FtoFCpdWorkShopAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<FtoFCpdWorkShop> bookPrivateStudies;

    public FtoFCpdWorkShopAdapter(Context context , ArrayList<FtoFCpdWorkShop> bookPrivateStudies) {
        this.context = context;
        this.bookPrivateStudies = bookPrivateStudies;
    }
    @NonNull
    @Override
    public FtoFCpdWorkShopAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ftof_workshop_adapter, parent, false);
        return new FtoFCpdWorkShopAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final FtoFCpdWorkShopAdapter.MyViewHolder holder, int position) {
        holder.tv_city.setText(bookPrivateStudies.get(position).getCity_schedule() + bookPrivateStudies.get(position).getVenue());
        holder.tv_date.setText(bookPrivateStudies.get(position).getDate_schedule());
        holder.tv_capacity.setText("30");
        holder.tv_cpd_price.setText(bookPrivateStudies.get(position).getTotal_price());
        holder.linear_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CPDWorkShopRequestActivity.class);
                i.putExtra("id_schedule",bookPrivateStudies.get(position).getId_schedule());
                context.startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        return bookPrivateStudies.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_city, tv_date, tv_capacity, tv_cpd_price;
        LinearLayout linear_booknow;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_capacity = itemView.findViewById(R.id.tv_capacity);
            tv_cpd_price = itemView.findViewById(R.id.tv_cpd_price);
            linear_booknow = itemView.findViewById(R.id.linear_booknow);

        }
    }

}

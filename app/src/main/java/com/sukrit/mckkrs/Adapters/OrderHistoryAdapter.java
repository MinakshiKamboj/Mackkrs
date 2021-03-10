package com.sukrit.mckkrs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.Models.OrderHistory;
import com.sukrit.mckkrs.R;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<OrderHistory> orderHistories;


    public OrderHistoryAdapter(Context context, ArrayList<OrderHistory> orderHistories) {
        this.context = context;
        this.orderHistories = orderHistories;
    }
    @NonNull
    @Override
    public OrderHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_adapter, parent, false);
        return new OrderHistoryAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.MyViewHolder holder, int position) {

        holder.txt_name.setText(orderHistories.get(position).getActivity_name());
        holder.txt_invoice_name1.setText(orderHistories.get(position).getId_invoice());
        holder.txt_date1.setText(orderHistories.get(position).getOrder_date());
        holder.txt_price1.setText(orderHistories.get(position).getPrice());

    }
    @Override
    public int getItemCount() {
        return orderHistories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_invoice_name1, txt_date1, txt_price1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_invoice_name1 = itemView.findViewById(R.id.txt_invoice_name1);
            txt_date1 = itemView.findViewById(R.id.txt_date1);
            txt_price1 = itemView.findViewById(R.id.txt_price1);

        }
    }}
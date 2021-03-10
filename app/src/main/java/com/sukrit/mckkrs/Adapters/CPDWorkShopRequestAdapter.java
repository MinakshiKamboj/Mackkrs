package com.sukrit.mckkrs.Adapters;

import android.content.Context;
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
import com.sukrit.mckkrs.Models.workshopdetails;
import com.sukrit.mckkrs.R;

import java.util.ArrayList;

public class CPDWorkShopRequestAdapter extends RecyclerView.Adapter<CPDWorkShopRequestAdapter.MyViewHolder> {
    private CPDWorkShopRequestActivity context;
    private ArrayList<workshopdetails> bookPrivateStudies;
    OnClickStudy OnClickPrivate;
    String type="";

    public CPDWorkShopRequestAdapter(CPDWorkShopRequestActivity context , ArrayList<workshopdetails> bookPrivateStudies, OnClickStudy OnClickPrivate) {
        this.context = context;
        this.bookPrivateStudies = bookPrivateStudies;
        this.OnClickPrivate = OnClickPrivate;
    }

    @NonNull
    @Override
    public CPDWorkShopRequestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_prvt_study_adapter, parent, false);
        return new CPDWorkShopRequestAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final CPDWorkShopRequestAdapter.MyViewHolder holder, int position) {
        holder.checkbox_name.setText(bookPrivateStudies.get(position).getActivity_name());
        holder.txt_point_no.setText(bookPrivateStudies.get(position).getPoints());
        holder.txt_price.setText("$ "+bookPrivateStudies.get(position).getPrice());

        if (bookPrivateStudies.get(position).getPoints() == null){
            holder.txt_po.setVisibility(View.GONE);
            holder.txt_time.setVisibility(View.GONE);
            holder.linear1.setVisibility(View.GONE);
        }
        else {
            holder.txt_po.setVisibility(View.VISIBLE);
            holder.txt_time.setVisibility(View.VISIBLE);
            holder.linear1.setVisibility(View.VISIBLE);
        }

        holder.checkbox_name.setTag(position);
    }
    @Override
    public int getItemCount() {
        return bookPrivateStudies.size();
    }
    public ArrayList<workshopdetails> getList(){
        return this.bookPrivateStudies;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_private, txt_act, txt_price;
        TextView txt_point_no, txt_po, txt_time;
        CheckBox checkbox_name;
        LinearLayout linear1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_point_no = itemView.findViewById(R.id.txt_point_no);
            txt_po = itemView.findViewById(R.id.txt_po);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_private = itemView.findViewById(R.id.txt_private);
            txt_act = itemView.findViewById(R.id.txt_act);
            txt_act.setVisibility(View.GONE);
            txt_price = itemView.findViewById(R.id.txt_price);
            linear1 = itemView.findViewById(R.id.linear1);
            checkbox_name = itemView.findViewById(R.id.checkbox_name);
            checkbox_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    bookPrivateStudies.get(getAdapterPosition()).setSelected(b);
                    OnClickPrivate.itemClick(bookPrivateStudies.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface OnClickStudy{
        public void itemClick(workshopdetails id);
    }
}

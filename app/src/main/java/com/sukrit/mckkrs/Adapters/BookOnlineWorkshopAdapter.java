package com.sukrit.mckkrs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.Models.OnlineWorkShopList;
import com.sukrit.mckkrs.R;

import java.util.ArrayList;

public class BookOnlineWorkshopAdapter extends RecyclerView.Adapter<BookOnlineWorkshopAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<OnlineWorkShopList> onlineWorkShopLists;
    OnClickStudy1 OnClickPrivate;
    public BookOnlineWorkshopAdapter(Context context, ArrayList<OnlineWorkShopList> onlineWorkShopLists, OnClickStudy1 OnClickPrivate ) {
        this.context = context;
        this.onlineWorkShopLists = onlineWorkShopLists;
        this.OnClickPrivate = OnClickPrivate;

    }
    @NonNull
    @Override
    public BookOnlineWorkshopAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_online_wrkshp_adapter, parent, false);
        return new BookOnlineWorkshopAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull BookOnlineWorkshopAdapter.MyViewHolder holder, int position) {
        holder.checkbox_name.setText(onlineWorkShopLists.get(position).getActivity_name());
        holder.txt_date.setText(onlineWorkShopLists.get(position).getDate_schedule());
        holder.txt_time.setText(onlineWorkShopLists.get(position).getTime());
        holder.txt_price.setText("$"+onlineWorkShopLists.get(position).getPrice());
        holder.checkbox_name.setTag(position);

    }
    @Override
    public int getItemCount() {
        return onlineWorkShopLists.size();
    }

    public ArrayList<OnlineWorkShopList> getList(){
        return this.onlineWorkShopLists;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_private, txt_date, txt_time, txt_price;
        CheckBox checkbox_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_private = itemView.findViewById(R.id.txt_private);
            checkbox_name = itemView.findViewById(R.id.checkbox_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_price = itemView.findViewById(R.id.txt_price);
            checkbox_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    onlineWorkShopLists.get(getAdapterPosition()).setSelected(b);
                    OnClickPrivate.itemClick(onlineWorkShopLists.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface OnClickStudy1{
        public void itemClick(OnlineWorkShopList id);
    }

}

package com.sukrit.mckkrs.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.Models.CountItem;
import com.sukrit.mckkrs.Models.OnlineWorkShopList;
import com.sukrit.mckkrs.R;

import java.util.ArrayList;
public class BookPrivateStudyAdapter extends RecyclerView.Adapter<BookPrivateStudyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<BookPrivateStudy> bookPrivateStudies;
    OnClickStudy OnClickPrivate;
    String type="";

    public BookPrivateStudyAdapter(Context context , ArrayList<BookPrivateStudy> bookPrivateStudies, OnClickStudy OnClickPrivate) {
        this.context = context;
        this.bookPrivateStudies = bookPrivateStudies;
        this.OnClickPrivate = OnClickPrivate;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_prvt_study_adapter, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.checkbox_name.setText(bookPrivateStudies.get(position).getActivity_name());
        holder.txt_price.setText("$"+bookPrivateStudies.get(position).getGlobalPrice());

        if (bookPrivateStudies.get(position).getActivity_mandatory().equals("1")){
            holder.txt_act.setText("Mandatory Activity");
        }
        else {

        }
        holder.checkbox_name.setTag(position);

    }
    @Override
    public int getItemCount() {
        return bookPrivateStudies.size();
    }
    public ArrayList<BookPrivateStudy> getList(){
        return this.bookPrivateStudies;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_private, txt_act, txt_price;
        CheckBox checkbox_name;
        LinearLayout lr_point;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lr_point = itemView.findViewById(R.id.lr_point);
            lr_point.setVisibility(View.GONE);
            txt_private = itemView.findViewById(R.id.txt_private);
            txt_act = itemView.findViewById(R.id.txt_act);
            txt_price = itemView.findViewById(R.id.txt_price);
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
        public void itemClick(BookPrivateStudy id);
    }
}

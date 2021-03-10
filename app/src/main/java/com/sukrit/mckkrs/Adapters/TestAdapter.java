package com.sukrit.mckkrs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrit.mckkrs.Activities.TestActivity;
import com.sukrit.mckkrs.Models.AnswerModel;
import com.sukrit.mckkrs.Models.PrivateStudy;
import com.sukrit.mckkrs.Models.QuestionModel;
import com.sukrit.mckkrs.Models.Test;
import com.sukrit.mckkrs.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<QuestionModel> tests;

    public TestAdapter(Context context, ArrayList<QuestionModel> tests) {
        this.context = context;
        this.tests = tests;
    }

    @NonNull
    @Override
    public TestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_adapter, parent, false);
        return new TestAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.MyViewHolder holder, int position) {

        QuestionModel questionModel = tests.get(position);
        ArrayList<AnswerModel> answerList = questionModel.getAnswerModels();

        int answerSize = answerList.size();

        if (questionModel.getQuestion_txt() == null) {
            holder.txt_ques.setVisibility(View.GONE);
        } else {
        //    holder.tv_sr_no.setText(": "+String.valueOf(position+1));

            holder.txt_ques.setText("Ques "+ String.valueOf(position+1) + ".    "+questionModel.getQuestion_txt());
        }
        holder.option1.setVisibility(View.GONE);
        holder.option2.setVisibility(View.GONE);
        holder.option3.setVisibility(View.GONE);
        holder.option4.setVisibility(View.GONE);
        for (int i = 0; i < answerSize; i++) {
            if (i == 0) {
                holder.option1.setVisibility(View.VISIBLE);
                holder.option1.setText(answerList.get(i).getAnswer_txt());

            } else if (i == 1) {
                holder.option2.setVisibility(View.VISIBLE);
                holder.option2.setText(answerList.get(i).getAnswer_txt());

            } else if (i == 2) {
                holder.option3.setVisibility(View.VISIBLE);
                holder.option3.setText(answerList.get(i).getAnswer_txt());

            } else if (i == 3) {
                holder.option4.setVisibility(View.VISIBLE);
                holder.option4.setText(answerList.get(i).getAnswer_txt());
            }


        }
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.option1:
                        Toast.makeText(context,
                                holder.option1.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.option2:
                        Toast.makeText(context,
                                holder.option2.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.option3:
                        Toast.makeText(context,
                                holder.option3.getText(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }
    @Override
    public int getItemCount() {
        return tests.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_ques, txt_ans ;
        RadioButton option1;
        RadioButton option2;
        RadioButton option3;
        RadioButton option4;
        RadioGroup radioGroup;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ques= itemView.findViewById(R.id.txt_ques);
           option1= itemView.findViewById(R.id.option1);
            option2= itemView.findViewById(R.id.option2);
            option3= itemView.findViewById(R.id.option3);
            option4= itemView.findViewById(R.id.option4);
            txt_ans= itemView.findViewById(R.id.txt_ans);
            radioGroup= itemView.findViewById(R.id.radio_group2);
        }
    }}
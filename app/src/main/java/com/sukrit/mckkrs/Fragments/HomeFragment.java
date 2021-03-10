package com.sukrit.mckkrs.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sukrit.mckkrs.Activities.CompletedActivitiesActivity;
import com.sukrit.mckkrs.Activities.FeedbackActivity;
import com.sukrit.mckkrs.Activities.OnlineWorkActivity;
import com.sukrit.mckkrs.Activities.OrderActivitiesActivity;
import com.sukrit.mckkrs.Activities.OrderHistoryActivity;
import com.sukrit.mckkrs.Activities.PrivateStudyActivity;
import com.sukrit.mckkrs.Activities.ProfileDetailsActivity;
import com.sukrit.mckkrs.R;


public class HomeFragment extends Fragment {

    LinearLayout linear_pri_study, linear_workshop, linear_order,
            orderhistory_linear, linear_profile, linear_comp_his, linear_feedback;


    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        linear_pri_study = view.findViewById(R.id.linear_pri_study);
        linear_workshop = view.findViewById(R.id.linear_workshop);
        linear_order = view.findViewById(R.id.linear_order);
        orderhistory_linear = view.findViewById(R.id.orderhistory_linear);
        linear_profile = view.findViewById(R.id.linear_profile);
        linear_comp_his = view.findViewById(R.id.linear_comp_his);
        linear_feedback = view.findViewById(R.id.linear_feedback);
        linear_pri_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PrivateStudyActivity.class);
                startActivity(i);

             /*   Fragment fragment = new PrivateStudyFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();*/
            }
        });
        linear_workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), OnlineWorkActivity.class);
                startActivity(i);

              /*  Fragment fragment = new OnlineWorkFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();*/
            }
        });
        linear_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), OrderActivitiesActivity.class);
                startActivity(i);
               /* Fragment fragment = new OrderActivitiesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();*/
            }
        });
        orderhistory_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), OrderHistoryActivity.class);
                startActivity(i);

              /*  Fragment fragment = new OrderHistoryFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();*/
            }
        });
        linear_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ProfileDetailsActivity.class);
                startActivity(i);
            }
        });
        linear_comp_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CompletedActivitiesActivity.class);
                startActivity(i);

            }
        });
        linear_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), FeedbackActivity.class);
                startActivity(i);
            }
        });


        return view;
    }
}
package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sukrit.mckkrs.Adapters.CompletedActivitiesAdapter;
import com.sukrit.mckkrs.R;

public class CompletedActivitiesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_activities);
        image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CompletedActivitiesActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        CompletedActivitiesAdapter adapter = new CompletedActivitiesAdapter(CompletedActivitiesActivity.this);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(CompletedActivitiesActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }
}
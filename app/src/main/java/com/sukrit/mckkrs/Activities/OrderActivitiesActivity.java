package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sukrit.mckkrs.R;

public class OrderActivitiesActivity extends AppCompatActivity {
   ImageView image;
    LinearLayout  linear, linear1, linear2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_activities);

        image = findViewById(R.id.image);
        linear = findViewById(R.id.linear);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderActivitiesActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderActivitiesActivity.this, FtoFCpdWorkShopActivity.class);
                startActivity(i);
                finish();
            }
        });
        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderActivitiesActivity.this, BookPrivateStudyActivity.class);
                startActivity(i);
                finish();
            }
        });
        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderActivitiesActivity.this, BookOnlineWorkshopActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
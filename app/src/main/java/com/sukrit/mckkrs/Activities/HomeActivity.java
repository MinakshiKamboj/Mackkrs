package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sukrit.mckkrs.Fragments.HomeFragment;
import com.sukrit.mckkrs.R;

public class HomeActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigationView = findViewById(R.id.naviganation);
        drawerLayout = findViewById(R.id.drawer);

        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,new HomeFragment()).commit();
        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open, // nav drawer open - description for accessibility
                R.string.close // nav drawer close - description for accessibility
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        ImageView hamMenu = findViewById(R.id.ham_menu);
        hamMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!drawerLayout.isDrawerOpen(Gravity.START)) drawerLayout.openDrawer(Gravity.START);
                else drawerLayout.closeDrawer(Gravity.END);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){

                    case   R.id.profile:
                        Toast.makeText(HomeActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        break;

                 /*   case R.id.logout:
                        MyPreferences.getActiveInstance(HomeActivity.this).setIsLoggedIn(false);
                        Intent i2 = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(i2);
                        finish();
                        break;*/
                }

                return true;
            }
        });

    }


    }

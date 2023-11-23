package com.example.mobilesoftware;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMenuChart fragmentChart = new MainMenuChart();
    private MainMenuMoreFragment fragmentMore = new MainMenuMoreFragment();
    private MainMenuSearchFragment fragmentSearch = new MainMenuSearchFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragmentChart).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_input:
                    transaction.replace(R.id.menu_frame_layout, fragmentChart).commitAllowingStateLoss();
                    break;
                case R.id.menu_search:
                    transaction.replace(R.id.menu_frame_layout, fragmentSearch).commitAllowingStateLoss();
                    break;
                case R.id.menu_month:
                    transaction.replace(R.id.menu_frame_layout, fragmentMore).commitAllowingStateLoss();
                    break;
                case R.id.menu_home:
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        }
    }
}

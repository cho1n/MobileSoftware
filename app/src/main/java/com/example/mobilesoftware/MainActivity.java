package com.example.mobilesoftware;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner placeSpinner = (Spinner) findViewById(R.id.spinner_place);
        ArrayAdapter placeAdapter = ArrayAdapter.createFromResource(this,
                R.array.place_for_food, android.R.layout.simple_spinner_item);
        placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeSpinner.setAdapter(placeAdapter);
    }
}
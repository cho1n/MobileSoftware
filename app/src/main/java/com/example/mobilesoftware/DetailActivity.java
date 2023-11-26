package com.example.mobilesoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Button returnBtn = findViewById(R.id.return_btn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String foodName = intent.getStringExtra("foodName");
        String cost = intent.getStringExtra("cost");
        String place = intent.getStringExtra("place");
        String time = intent.getStringExtra("time");
        String calory = intent.getStringExtra("calory");
        String text = intent.getStringExtra("text");

        ImageView foodImgView = findViewById(R.id.food_image_detail);

        String imagePath = intent.getStringExtra("imagePath");
        if (imagePath != null) {
            Bitmap image = BitmapFactory.decodeFile(imagePath);
            foodImgView.setImageBitmap(image);
        } else {
            Log.e("DetailActivity", "Image path is null");
        }
        TextView foodNameTextView = findViewById(R.id.foodname_text);
        foodNameTextView.setText(foodName);
        TextView foodPlaceTextView = findViewById(R.id.place_text);
        foodPlaceTextView.setText(place);
        TextView foodCostTextView = findViewById(R.id.cost_text);
        foodCostTextView.setText(cost);
        TextView foodTimeTextView = findViewById(R.id.time_text);
        foodTimeTextView.setText(time);
        TextView foodCalTextView = findViewById(R.id.cal_text);
        foodCalTextView.setText(calory);
        TextView foodTextTextView = findViewById(R.id.text_text);
        foodTextTextView.setText(text);


    }
}
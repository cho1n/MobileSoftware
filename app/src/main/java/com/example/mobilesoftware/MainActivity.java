package com.example.mobilesoftware;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Random;


import com.example.mobilesoftware.DataStructure.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Uri uri;
    ImageView imageView;
    byte[] imageByte;

    TextView dateText;
    DatePickerDialog datePickerDialog;
    Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.date_text_view);
        Button datePickerBtn = findViewById(R.id.date_picker_btn);
        Button submitBtn = findViewById(R.id.submitButton);
        rnd = new Random();

        submitBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             String date = dateText.getText().toString();
                                             String kind = ((Spinner) findViewById(R.id.spinner_kind)).getSelectedItem().toString();
                                             String place = ((Spinner) findViewById(R.id.spinner_place)).getSelectedItem().toString();
                                             String foodName = ((EditText) findViewById(R.id.foodNameInput)).getText().toString();
                                             String cost = ((EditText) findViewById(R.id.foodCostInput)).getText().toString();
                                             String time = ((EditText) findViewById(R.id.foodTimeInput)).getText().toString();
                                             String rating = ((EditText) findViewById(R.id.foodRatingInput)).getText().toString();
                                             int calory = rnd.nextInt(300) + 300 ;


                                             Food food = new Food(date, kind, imageByte, place, foodName, cost, time, rating, calory);

                                             FoodDatabaseHelper dbHelper = new FoodDatabaseHelper(MainActivity.this);
                                             dbHelper.addFood(food);

                                             Toast.makeText(MainActivity.this, "입력되었습니다.", Toast.LENGTH_SHORT).show();

                                             Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                             startActivity(intent);
                                         }
                                     });

        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR);
                int pMonth = calendar.get(Calendar.MONTH);
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;
                                String date = year + "/" + month + "/" + day;

                                dateText.setText(date);
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
            }
        });

        Button selectImageBtn = findViewById(R.id.selectImageBtn);
        imageView = findViewById(R.id.imageView);

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);
            }
        });

        Spinner placeSpinner = (Spinner) findViewById(R.id.spinner_place);
        ArrayAdapter placeAdapter = ArrayAdapter.createFromResource(this,
                R.array.place_for_food, android.R.layout.simple_spinner_item);
        placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeSpinner.setAdapter(placeAdapter);

        Spinner kindSpinner = (Spinner) findViewById(R.id.spinner_kind);
        ArrayAdapter kindAdapter = ArrayAdapter.createFromResource(this,
                R.array.kind_for_food, android.R.layout.simple_spinner_item);
        kindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kindSpinner.setAdapter(kindAdapter);

    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        uri = result.getData().getData();

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageView.setImageBitmap(bitmap);
                            imageByte = getBytesFromBitmap(bitmap);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }}
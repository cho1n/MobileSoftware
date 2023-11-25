package com.example.mobilesoftware;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilesoftware.DataStructure.Food;
import com.example.mobilesoftware.DataStructure.FoodDatabaseHelper;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainMenuChart extends Fragment {

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일의 식단입니다.");
    TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_menu_chart, container, false);

        mTextView = (TextView) rootView.findViewById(R.id.dateView);
        mTextView.setText(getTime());

        displayFoodData(rootView);

        return rootView;
    }

    private void displayFoodData(ViewGroup rootView) {
        FoodDatabaseHelper dbHelper = new FoodDatabaseHelper(requireContext());
        List<Food> foodList = dbHelper.getAllFoods();
        for (int i = 0; i < foodList.size(); i++) {
            ImageView imageView;
            TextView nameView, timeView, costView, textView, placeView, calView;
            if (foodList.get(i).getKind().equals("조식")) {
                imageView = rootView.findViewById(R.id.morning_img);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodList.get(i).getImage(), 0, foodList.get(i).getImage().length));
                nameView = rootView.findViewById(R.id.morning_food);
                nameView.setText(foodList.get(i).getFoodName());
                timeView = rootView.findViewById(R.id.morning_time);
                timeView.setText(foodList.get(i).getTime());
                costView = rootView.findViewById(R.id.morning_cost);
                costView.setText(foodList.get(i).getCost());
                textView = rootView.findViewById(R.id.morning_text);
                textView.setText(foodList.get(i).getRating());
                textView = rootView.findViewById(R.id.morning_place);
                textView.setText(foodList.get(i).getPlace());
                textView = rootView.findViewById(R.id.morning_cal);
                textView.setText(String.valueOf(foodList.get(i).getCalory()));
            }
            else if (foodList.get(i).getKind().equals("중식")) {
                imageView = rootView.findViewById(R.id.afternoon_img);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodList.get(i).getImage(), 0, foodList.get(i).getImage().length));
                nameView = rootView.findViewById(R.id.afternoon_name);
                nameView.setText(foodList.get(i).getFoodName());
                timeView = rootView.findViewById(R.id.afternoon_time);
                timeView.setText(foodList.get(i).getTime());
                costView = rootView.findViewById(R.id.afternoon_cost);
                costView.setText(foodList.get(i).getCost());
                textView = rootView.findViewById(R.id.afternoon_text);
                textView.setText(foodList.get(i).getRating());
                textView = rootView.findViewById(R.id.afternoon_place);
                textView.setText(foodList.get(i).getPlace());
                textView = rootView.findViewById(R.id.afternoon_cal);
                textView.setText(String.valueOf(foodList.get(i).getCalory()));
            }
            else if (foodList.get(i).getKind().equals("석식")) {
                imageView = rootView.findViewById(R.id.evening_img);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodList.get(i).getImage(), 0, foodList.get(i).getImage().length));
                nameView = rootView.findViewById(R.id.evening_name);
                nameView.setText(foodList.get(i).getFoodName());
                timeView = rootView.findViewById(R.id.evening_time);
                timeView.setText(foodList.get(i).getTime());
                costView = rootView.findViewById(R.id.evening_cost);
                costView.setText(foodList.get(i).getCost());
                textView = rootView.findViewById(R.id.evening_text);
                textView.setText(foodList.get(i).getRating());
                textView = rootView.findViewById(R.id.evening_place);
                textView.setText(foodList.get(i).getPlace());
                textView = rootView.findViewById(R.id.evening_cal);
                textView.setText(String.valueOf(foodList.get(i).getCalory()));
            }
            else if (foodList.get(i).getKind().equals("음료 혹은 간식")) {
                imageView = rootView.findViewById(R.id.snack_img);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodList.get(i).getImage(), 0, foodList.get(i).getImage().length));
                nameView = rootView.findViewById(R.id.snack_name);
                nameView.setText(foodList.get(i).getFoodName());
                timeView = rootView.findViewById(R.id.snack_time);
                timeView.setText(foodList.get(i).getTime());
                costView = rootView.findViewById(R.id.snack_cost);
                costView.setText(foodList.get(i).getCost());
                textView = rootView.findViewById(R.id.snack_text);
                textView.setText(foodList.get(i).getRating());
                textView = rootView.findViewById(R.id.snack_place);
                textView.setText(foodList.get(i).getPlace());
                textView = rootView.findViewById(R.id.snack_cal);
                textView.setText(String.valueOf(foodList.get(i).getCalory()));
            }

        }
    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}
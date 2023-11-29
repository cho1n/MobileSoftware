package com.example.mobilesoftware;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobilesoftware.DataStructure.Food;
import com.example.mobilesoftware.DataStructure.FoodDatabaseHelper;

import java.util.Calendar;
import java.util.List;

public class MainMenuMoreFragment extends Fragment {

    private TextView dateText;
    private ViewGroup rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_menu_more, container, false);
        dateText = rootView.findViewById(R.id.date_view_box);

        DatePicker datePicker = rootView.findViewById(R.id.search_date_Picker);

        Calendar calendar = Calendar.getInstance();
        int pYear = calendar.get(Calendar.YEAR);
        int pMonth = calendar.get(Calendar.MONTH);
        int pDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(pYear, pMonth, pDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                dateText.setText(date);
                Log.d("settime", date);
                displayFoodData(date);
            }
        });

        return rootView;
    }

    private void displayFoodData(String selectedDate) {
        FoodDatabaseHelper dbHelper = new FoodDatabaseHelper(requireContext());
        List<Food> foodList = dbHelper.getAllFoodss();

        for (Food food : foodList) {
            String foodDate = food.getDate();
            if (selectedDate.equals(foodDate)) {
                TextView nameView, costView, placeView, calView;

                switch (food.getKind()) {
                    case "조식":
                        nameView = rootView.findViewById(R.id.morning_food_text);
                        costView = rootView.findViewById(R.id.morning_price_text);
                        placeView = rootView.findViewById(R.id.morning_place_text);
                        calView = rootView.findViewById(R.id.morning_cal_text);
                        break;

                    case "중식":
                        nameView = rootView.findViewById(R.id.afternoon_food_text);
                        costView = rootView.findViewById(R.id.afternoon_price_text);
                        placeView = rootView.findViewById(R.id.afternoon_place_text);
                        calView = rootView.findViewById(R.id.afternoon_cal_text);
                        break;

                    case "석식":
                        nameView = rootView.findViewById(R.id.evening_food_text);
                        costView = rootView.findViewById(R.id.evening_price_text);
                        placeView = rootView.findViewById(R.id.evening_place_text);
                        calView = rootView.findViewById(R.id.evening_cal_text);
                        break;

                    case "음료 혹은 간식":
                        nameView = rootView.findViewById(R.id.snack_name_text);
                        costView = rootView.findViewById(R.id.snack_price_text);
                        placeView = rootView.findViewById(R.id.snack_place_text);
                        calView = rootView.findViewById(R.id.snack_cal_text);
                        break;

                    default:
                        continue;
                }

                nameView.setText(food.getFoodName());
                costView.setText(food.getCost());
                placeView.setText(food.getPlace());
                calView.setText(String.valueOf(food.getCalory()));
            }
        }
    }

    private void clearTextViews() {
        // 모든 TextView 초기화
        int[] textViewIds = {
                R.id.morning_food_text, R.id.morning_price_text, R.id.morning_place_text, R.id.morning_cal_text,
                R.id.afternoon_food_text, R.id.afternoon_price_text, R.id.afternoon_place_text, R.id.afternoon_cal_text,
                R.id.evening_food_text, R.id.evening_price_text, R.id.evening_place_text, R.id.evening_cal_text,
                R.id.snack_name_text, R.id.snack_price_text, R.id.snack_place_text, R.id.snack_cal_text
        };

        for (int id : textViewIds) {
            TextView textView = rootView.findViewById(id);
            textView.setText("");
        }
    }
}

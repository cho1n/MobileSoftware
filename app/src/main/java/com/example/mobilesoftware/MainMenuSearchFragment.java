package com.example.mobilesoftware;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilesoftware.DataStructure.Food;
import com.example.mobilesoftware.DataStructure.FoodDatabaseHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainMenuSearchFragment extends Fragment {

    private LineChart lineChart;
    private TextView mTextView;
    private TextView morningTotalPriceTextView;
    private TextView morningTotalCalTextView;
    private TextView afternoonTotalPriceTextView;
    private TextView afternoonTotalCalTextView;
    private TextView eveningTotalPriceTextView;
    private TextView eveningTotalCalTextView;
    private TextView snackTotalPriceTextView;
    private TextView snackTotalCalTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_menu_search, container, false);
        mTextView = (TextView) rootView.findViewById(R.id.month_textView);


        morningTotalPriceTextView = rootView.findViewById(R.id.morning_total_price);
        morningTotalCalTextView = rootView.findViewById(R.id.morning_total_cal);
        afternoonTotalPriceTextView = rootView.findViewById(R.id.afternoon_total_price);
        afternoonTotalCalTextView = rootView.findViewById(R.id.afternoon_total_cal);
        eveningTotalPriceTextView = rootView.findViewById(R.id.evening_total_price);
        eveningTotalCalTextView = rootView.findViewById(R.id.evening_total_cal);
        snackTotalPriceTextView = rootView.findViewById(R.id.snack_total_price);
        snackTotalCalTextView = rootView.findViewById(R.id.snack_total_cal);

        FoodDatabaseHelper databaseHelper = new FoodDatabaseHelper(getContext());  // FoodDatabaseHelper의 인스턴스 생성
        List<Food> foodList = databaseHelper.monthlyGetAllFoods();

        int morningTotalPrice = 0;
        int morningTotalCal = 0;
        int afternoonTotalPrice = 0;
        int afternoonTotalCal = 0;
        int eveningTotalPrice = 0;
        int eveningTotalCal = 0;
        int snackTotalPrice = 0;
        int snackTotalCal = 0;

        for (Food food : foodList) {
            switch (food.getKind()) {
                case "조식":
                    morningTotalPrice += Integer.parseInt(food.getCost());
                    morningTotalCal += food.getCalory();
                    break;
                case "중식":
                    afternoonTotalPrice += Integer.parseInt(food.getCost());
                    afternoonTotalCal += food.getCalory();
                    break;
                case "석식":
                    eveningTotalPrice += Integer.parseInt(food.getCost());
                    eveningTotalCal += food.getCalory();
                    break;
                case "음료 혹은 간식":
                    snackTotalPrice += Integer.parseInt(food.getCost());
                    snackTotalCal += food.getCalory();
                    break;
                // 필요에 따라 다른 식사 시간에 대한 처리도 추가할 수 있습니다.
            }
        }

        // 계산된 값들을 텍스트 뷰에 설정
        morningTotalPriceTextView.setText(String.valueOf(morningTotalPrice));
        morningTotalCalTextView.setText(String.valueOf(morningTotalCal));
        afternoonTotalPriceTextView.setText(String.valueOf(afternoonTotalPrice));
        afternoonTotalCalTextView.setText(String.valueOf(afternoonTotalCal));
        eveningTotalPriceTextView.setText(String.valueOf(eveningTotalPrice));
        eveningTotalCalTextView.setText(String.valueOf(eveningTotalCal));
        snackTotalPriceTextView.setText(String.valueOf(snackTotalPrice));
        snackTotalCalTextView.setText(String.valueOf(snackTotalCal));

        ArrayList<Entry> entry_chart1 = new ArrayList<>(); // 데이터를 담을 Arraylist
        ArrayList<Entry> entry_chart2 = new ArrayList<>();

        lineChart = (LineChart) rootView.findViewById(R.id.chart);

        LineData chartData = new LineData();

        entry_chart1.add(new Entry(1, morningTotalPrice)); //entry_chart1에 좌표 데이터를 담는다.
        entry_chart1.add(new Entry(2, afternoonTotalPrice));
        entry_chart1.add(new Entry(3, eveningTotalPrice));
        entry_chart1.add(new Entry(4, snackTotalPrice));

        entry_chart2.add(new Entry(1, morningTotalCal)); //entry_chart2에 좌표 데이터를 담는다.
        entry_chart2.add(new Entry(2, afternoonTotalCal));
        entry_chart2.add(new Entry(3, eveningTotalCal));
        entry_chart2.add(new Entry(4, snackTotalCal));

        LineDataSet lineDataSet1 = new LineDataSet(entry_chart1, "총 비용");
        LineDataSet lineDataSet2 = new LineDataSet(entry_chart2, "총 Kcal");

        ArrayList<String> xLabels = new ArrayList<>();
        xLabels.add("");
        xLabels.add("아침");
        xLabels.add("점심");
        xLabels.add("저녁");
        xLabels.add("음료 및 간식");

        chartData.addDataSet(lineDataSet1);
        chartData.addDataSet(lineDataSet2);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        lineDataSet1.setColor(Color.parseColor("#F4CE14"));
        lineDataSet1.setLineWidth(4);
        lineDataSet2.setColor(Color.parseColor("#FFFFFF"));
        lineDataSet2.setLineWidth(4);

        lineChart.setData(chartData);
        lineChart.invalidate();
        lineChart.setTouchEnabled(false);

        String currentMonth = getCurrentMonth();
        mTextView.setText(currentMonth);


        return rootView;
    }

    private String getCurrentMonth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}





package com.example.mobilesoftware;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilesoftware.DataStructure.Food;
import com.example.mobilesoftware.DataStructure.FoodDatabaseHelper;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
            final int position = i;
            ImageView imageView;
            TextView nameView, timeView, costView, textView, placeView, calView;
            if (foodList.get(i).getKind().equals("조식")) {
                imageView = rootView.findViewById(R.id.morning_img);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodList.get(i).getImage(), 0, foodList.get(i).getImage().length));
                nameView = rootView.findViewById(R.id.morning_food);
                nameView.setText(foodList.get(i).getFoodName());
                costView = rootView.findViewById(R.id.morning_cost);
                costView.setText(foodList.get(i).getCost());
                placeView = rootView.findViewById(R.id.morning_place);
                placeView.setText(foodList.get(i).getPlace());
                calView = rootView.findViewById(R.id.morning_cal);
                calView.setText(String.valueOf(foodList.get(i).getCalory()));
                ConstraintLayout morningBox = rootView.findViewById(R.id.morning_box);
                morningBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(requireContext(), DetailActivity.class);

                        byte[] imageBytes = foodList.get(position).getImage();
                        if (imageBytes != null) {
                            String imagePath = saveImageToInternalStorage(imageBytes);
                            if (imagePath != null) {
                                intent.putExtra("imagePath", imagePath);
                            } else {
                                Log.e("MainMenuChart", "Failed to save image to internal storage");
                            }
                        } else {
                            Log.e("MainMenuChart", "Image bytes is null");
                        }
                        intent.putExtra("foodName", foodList.get(position).getFoodName());
                        intent.putExtra("cost", foodList.get(position).getCost());
                        intent.putExtra("place", foodList.get(position).getPlace());
                        intent.putExtra("time", foodList.get(position).getTime());
                        intent.putExtra("calory", String.valueOf(foodList.get(position).getCalory()));
                        intent.putExtra("text", foodList.get(position).getRating());

                        startActivity(intent);
                    }
                });
            }
            else if (foodList.get(i).getKind().equals("중식")) {
                imageView = rootView.findViewById(R.id.afternoon_img);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodList.get(i).getImage(), 0, foodList.get(i).getImage().length));
                nameView = rootView.findViewById(R.id.afternoon_name);
                nameView.setText(foodList.get(i).getFoodName());
                costView = rootView.findViewById(R.id.afternoon_cost);
                costView.setText(foodList.get(i).getCost());
                placeView = rootView.findViewById(R.id.afternoon_place);
                placeView.setText(foodList.get(i).getPlace());
                calView = rootView.findViewById(R.id.afternoon_cal);
                calView.setText(String.valueOf(foodList.get(i).getCalory()));

                ConstraintLayout afternoonBox = rootView.findViewById(R.id.afternoon_box);
                afternoonBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(requireContext(), DetailActivity.class);

                        byte[] imageBytes = foodList.get(position).getImage();
                        if (imageBytes != null) {
                            String imagePath = saveImageToInternalStorage(imageBytes);
                            if (imagePath != null) {
                                intent.putExtra("imagePath", imagePath);
                            } else {
                                Log.e("MainMenuChart", "Failed to save image to internal storage");
                            }
                        } else {
                            Log.e("MainMenuChart", "Image bytes is null");
                        }                        intent.putExtra("foodName", foodList.get(position).getFoodName());
                        intent.putExtra("cost", foodList.get(position).getCost());
                        intent.putExtra("place", foodList.get(position).getPlace());
                        intent.putExtra("time", foodList.get(position).getTime());
                        intent.putExtra("calory", String.valueOf(foodList.get(position).getCalory()));
                        intent.putExtra("text", foodList.get(position).getRating());

                        startActivity(intent);
                    }
                });
            }
            else if (foodList.get(i).getKind().equals("석식")) {
                imageView = rootView.findViewById(R.id.evening_img);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodList.get(i).getImage(), 0, foodList.get(i).getImage().length));
                nameView = rootView.findViewById(R.id.evening_name);
                nameView.setText(foodList.get(i).getFoodName());
                costView = rootView.findViewById(R.id.evening_cost);
                costView.setText(foodList.get(i).getCost());
                placeView = rootView.findViewById(R.id.evening_place);
                placeView.setText(foodList.get(i).getPlace());
                calView = rootView.findViewById(R.id.evening_cal);
                calView.setText(String.valueOf(foodList.get(i).getCalory()));

                ConstraintLayout eveningBox = rootView.findViewById(R.id.evening_box);
                eveningBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(requireContext(), DetailActivity.class);
                        byte[] imageBytes = foodList.get(position).getImage();
                        if (imageBytes != null) {
                            String imagePath = saveImageToInternalStorage(imageBytes);
                            if (imagePath != null) {
                                intent.putExtra("imagePath", imagePath);
                            } else {
                                Log.e("MainMenuChart", "Failed to save image to internal storage");
                            }
                        } else {
                            Log.e("MainMenuChart", "Image bytes is null");
                        }                        intent.putExtra("foodName", foodList.get(position).getFoodName());
                        intent.putExtra("cost", foodList.get(position).getCost());
                        intent.putExtra("place", foodList.get(position).getPlace());
                        intent.putExtra("time", foodList.get(position).getTime());
                        intent.putExtra("calory", String.valueOf(foodList.get(position).getCalory()));
                        intent.putExtra("text", foodList.get(position).getRating());
                        intent.putExtra("image", foodList.get(position).getImage());

                        startActivity(intent);
                    }
                });
            }
            else if (foodList.get(i).getKind().equals("음료 혹은 간식")) {
                imageView = rootView.findViewById(R.id.snack_img);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(foodList.get(i).getImage(), 0, foodList.get(i).getImage().length));
                nameView = rootView.findViewById(R.id.snack_name);
                nameView.setText(foodList.get(i).getFoodName());
                costView = rootView.findViewById(R.id.snack_cost);
                costView.setText(foodList.get(i).getCost());
                placeView = rootView.findViewById(R.id.snack_place);
                placeView.setText(foodList.get(i).getPlace());
                calView = rootView.findViewById(R.id.snack_cal);
                calView.setText(String.valueOf(foodList.get(i).getCalory()));

                ConstraintLayout snackBox = rootView.findViewById(R.id.snack_box);
                snackBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(requireContext(), DetailActivity.class);

                        byte[] imageBytes = foodList.get(position).getImage();
                        if (imageBytes != null) {
                            String imagePath = saveImageToInternalStorage(imageBytes);
                            if (imagePath != null) {
                                intent.putExtra("imagePath", imagePath);
                            } else {
                                Log.e("MainMenuChart", "Failed to save image to internal storage");
                            }
                        } else {
                            Log.e("MainMenuChart", "Image bytes is null");
                        }                        intent.putExtra("foodName", foodList.get(position).getFoodName());
                        intent.putExtra("cost", foodList.get(position).getCost());
                        intent.putExtra("place", foodList.get(position).getPlace());
                        intent.putExtra("time", foodList.get(position).getTime());
                        intent.putExtra("calory", String.valueOf(foodList.get(position).getCalory()));
                        intent.putExtra("text", foodList.get(position).getRating());

                        startActivity(intent);
                    }
                });
            }

        }
    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    private String saveImageToInternalStorage(byte[] imageBytes) {
        try {
            File outputDir = requireContext().getCacheDir();
            File imageFile = File.createTempFile("tempImage", null, outputDir);

            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(imageBytes);
            fos.close();

            return imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}



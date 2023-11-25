package com.example.mobilesoftware.DataStructure;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mobilesoftware.DataStructure.Food;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FoodDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FoodDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "food_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_KIND = "kind";
    private static final String COLUMN_IMAGE_URL = "image_url";
    private static final String COLUMN_PLACE = "place";
    private static final String COLUMN_FOOD_NAME = "food_name";
    private static final String COLUMN_COST = "cost";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_Cal = "calory";

    // 생성자, onCreate, onUpgrade 등 필요한 메서드를 추가하세요

    // 예시: 생성자
    public FoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_KIND + " TEXT, " +
                COLUMN_IMAGE_URL + " TEXT, " +
                COLUMN_PLACE + " TEXT, " +
                COLUMN_FOOD_NAME + " TEXT, " +
                COLUMN_COST + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_RATING + " TEXT, " +
                COLUMN_Cal + " INTEGER);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 기존 테이블을 삭제하고 변경하는 작업을 수행
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, food.getDate());
        values.put(COLUMN_KIND, food.getKind());
        values.put(COLUMN_IMAGE_URL, food.getImage());
        values.put(COLUMN_PLACE, food.getPlace());
        values.put(COLUMN_FOOD_NAME, food.getFoodName());
        values.put(COLUMN_COST, food.getCost());
        values.put(COLUMN_TIME, food.getTime());
        values.put(COLUMN_RATING, food.getRating());
        values.put(COLUMN_Cal, String.valueOf(food.getCalory()));
        // 데이터베이스에 데이터 삽입
        db.insert(TABLE_NAME, null, values);

    }

    public List<Food> getAllFoods() {
        List<Food> foodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String todayDate = dateFormat.format(calendar.getTime());

        String selection = COLUMN_DATE + "=?";
        String[] sectionArgs = { todayDate };

        Cursor cursor = db.query(TABLE_NAME, null, selection, sectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst() ) {
            do {
                Food food = new Food();
                food.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                food.setKind(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KIND)));
                food.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_URL)));
                food.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLACE)));
                food.setFoodName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOOD_NAME)));
                food.setCost(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COST)));
                food.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
                food.setRating(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RATING)));
                food.setCalory(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_Cal))));

                foodList.add(food);
            } while (cursor.moveToNext());

            // 커서를 닫습니다.
            cursor.close();
        }

        // 데이터베이스 연결을 닫습니다.
        db.close();

        return foodList;
    }
}

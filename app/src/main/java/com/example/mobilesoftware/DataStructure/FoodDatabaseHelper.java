package com.example.mobilesoftware.DataStructure;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mobilesoftware.DataStructure.Food;

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
                COLUMN_RATING + " TEXT)";
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
        values.put(COLUMN_IMAGE_URL, food.getImageUrl());
        values.put(COLUMN_PLACE, food.getPlace());
        values.put(COLUMN_FOOD_NAME, food.getFoodName());
        values.put(COLUMN_COST, food.getCost());
        values.put(COLUMN_TIME, food.getTime());
        values.put(COLUMN_RATING, food.getRating());

        // 데이터베이스에 데이터 삽입
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}

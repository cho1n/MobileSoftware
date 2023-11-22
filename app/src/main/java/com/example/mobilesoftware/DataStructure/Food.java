package com.example.mobilesoftware.DataStructure;

public class Food {
    private String date;
    private String kind;
    private String imageUrl;
    private String place;
    private String foodName;
    private String cost;
    private String time;
    private String rating;

    // 생성자
    public Food(String date, String kind, String imageUrl, String place, String foodName, String cost, String time, String rating) {
        this.date = date;
        this.kind = kind;
        this.imageUrl = imageUrl;
        this.place = place;
        this.foodName = foodName;
        this.cost = cost;
        this.time = time;
        this.rating = rating;
    }

    // Getter 메서드들
    public String getDate() {
        return date;
    }

    public String getKind() {
        return kind;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPlace() {
        return place;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getCost() {
        return cost;
    }

    public String getTime() {
        return time;
    }

    public String getRating() {
        return rating;
    }

    // Setter 메서드들
    public void setDate(String date) {
        this.date = date;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

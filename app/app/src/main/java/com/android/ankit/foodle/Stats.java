package com.android.ankit.foodle;

public class Stats {

    String bmi, weight, recCalory, date, time, consumedCalory, foodName;

    public Stats(String bmi, String weight, String recCalory, String date, String time, String consumedCalory, String foodName) {
        this.bmi = bmi;
        this.weight = weight;
        this.recCalory = recCalory;
        this.date = date;
        this.time = time;
        this.consumedCalory = consumedCalory;
        this.foodName = foodName;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setRecCalory(String recCalory) {
        this.recCalory = recCalory;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setConsumedCalory(String consumedCalory) {
        this.consumedCalory = consumedCalory;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getBmi() {
        return bmi;
    }

    public String getWeight() {
        return weight;
    }

    public String getRecCalory() {
        return recCalory;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getConsumedCalory() {
        return consumedCalory;
    }

    public String getFoodName() {
        return foodName;
    }
}

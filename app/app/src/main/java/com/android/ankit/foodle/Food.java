package com.android.ankit.foodle;

public class Food {
    private String title, calory;
    private int image;

    public Food(String title, String calory, int image) {
        this.title = title;
        this.calory = calory;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getCalory() {
        return calory;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

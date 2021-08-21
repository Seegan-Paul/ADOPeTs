package com.example.adopets;

import android.graphics.Bitmap;

public class FishModelClass {
    int pid;
    Bitmap image;
    String breed;
    float price;


    public FishModelClass(int pid, Bitmap image, String breed, float price) {
        this.pid = pid;
        this.image = image;
        this.breed = breed;
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getBreed() {
        return breed;
    }

    public float getPrice() {
        return price;
    }

    public int getPid() {
        return pid;
    }
}

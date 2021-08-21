package com.example.adopets;

import android.graphics.Bitmap;

public class ItemModelClass {

    Bitmap image;
    String item;
    int pid;
    float price;

    public ItemModelClass(int pid, Bitmap image, String item, float price) {
        this.image = image;
        this.item = item;
        this.pid = pid;
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getItem() {
        return item;
    }

    public int getPid() {
        return pid;
    }

    public float getPrice() {
        return price;
    }
}

package com.example.adopets;

import android.graphics.Bitmap;

public class ModelClass {
    Bitmap image;
    String breed,age;
    float price;

    public ModelClass(Bitmap image,String age, String breed, float price) {
        this.image = image;
        this.age = age;
        this.breed = breed;
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public float getPrice() {
        return price;
    }
}

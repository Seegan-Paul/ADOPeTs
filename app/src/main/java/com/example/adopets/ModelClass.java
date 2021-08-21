package com.example.adopets;

import android.graphics.Bitmap;

public class ModelClass {
    Bitmap image;
    int pid;
    String breed,age,weight,height,sex;
    float price;



    public ModelClass(Bitmap image, int pid, String breed, String age, String sex, String weight, String height, float price) {
        this.image = image;
        this.pid = pid;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.price = price;
    }


    public String getSex() {
        return sex;
    }

    public int getPid() {
        return pid;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
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

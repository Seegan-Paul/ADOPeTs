package com.example.adopets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RVadapter extends RecyclerView.Adapter<RVadapter.RVHolderClass> {


    ArrayList<ModelClass> ModelClassList;

    public RVadapter(ArrayList<ModelClass> modelClassList) {
        ModelClassList = modelClassList;
    }

    public class RVHolderClass extends RecyclerView.ViewHolder {
        //TextView age,breed,price;
        TextView price;
        ImageView image;
        public RVHolderClass(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView2);
           // age = itemView.findViewById(R.id.age);
           // breed = itemView.findViewById(R.id.breed);
            price = itemView.findViewById(R.id.textView13);
        }
    }
    @NonNull
    @NotNull
    @Override
    public RVHolderClass onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RVHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RVadapter.RVHolderClass holder, int position) {
        try {
            ModelClass obj = ModelClassList.get(position);
           // holder.age.setText(obj.getAge());
           // holder.breed.setText(obj.getBreed());
            holder.price.setText(Float.toString(obj.getPrice()));
            holder.image.setImageBitmap(obj.getImage());
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return ModelClassList.size();
    }


}

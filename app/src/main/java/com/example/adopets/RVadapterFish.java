package com.example.adopets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RVadapterFish  extends RecyclerView.Adapter<RVadapterFish.RVHolderClass> implements Filterable {

    ArrayList<FishModelClass> ModelClassList;
    ArrayList<FishModelClass> ModelClassListAll;
    Context context;

    public RVadapterFish(ArrayList<FishModelClass> modelClassList,Context context) {
        ModelClassList = modelClassList;
        ModelClassListAll = new ArrayList<FishModelClass>(modelClassList);
        this.context = context;
    }

    @Override
    public Filter getFilter() {//added
        return filter;
    }

    Filter filter = new Filter() {//added
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<FishModelClass> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(ModelClassListAll);
            } else {
                for (FishModelClass data:ModelClassListAll){
                    if (data.getBreed().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(data);
                    }
                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ModelClassList.clear();
            ModelClassList.addAll((Collection<? extends FishModelClass>) results.values);
            notifyDataSetChanged();
        }
    };

    public class RVHolderClass extends RecyclerView.ViewHolder {
        //TextView age,breed,price;
        TextView price,breed;
        ImageView image;
        public RVHolderClass(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView2);
            // age = itemView.findViewById(R.id.age);
            breed = itemView.findViewById(R.id.textView15);
            price = itemView.findViewById(R.id.textView13);
        }
    }
    @NonNull
    @NotNull
    @Override
    public RVadapterFish.RVHolderClass onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RVadapterFish.RVHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RVadapterFish.RVHolderClass holder, int position) {
        try {
            FishModelClass obj = ModelClassList.get(position);
            // holder.age.setText(obj.getAge());
            holder.breed.setText(obj.getBreed());
            holder.price.setText(Float.toString(obj.getPrice()));
            holder.image.setImageBitmap(obj.getImage());

            holder.itemView.setOnClickListener(v -> {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                obj.getImage().compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                byte [] ImageByte = byteArrayOutputStream.toByteArray();

                Intent intent = new Intent(context,DetailFish.class);
                intent.putExtra("image",ImageByte);
                intent.putExtra("breed",obj.getBreed());
                intent.putExtra("price",obj.getPrice());
                intent.putExtra("pid",obj.getPid());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show();
            });
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return ModelClassList.size();
    }
}

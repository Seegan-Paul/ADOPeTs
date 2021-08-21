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

public class RVadapterItem extends RecyclerView.Adapter<RVadapterItem.RVHolderClass> implements Filterable {

    ArrayList<ItemModelClass> ModelClassList;
    ArrayList<ItemModelClass> ModelClassListAll;
    Context context;

    public RVadapterItem(ArrayList<ItemModelClass> modelClassList,Context context) {
        ModelClassList = modelClassList;
        ModelClassListAll = new ArrayList<ItemModelClass>(modelClassList);
        this.context = context;
    }

    @Override
    public Filter getFilter() {//added
        return filter;
    }

    Filter filter = new Filter() {//added
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<ItemModelClass> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(ModelClassListAll);
            } else {
                for (ItemModelClass data:ModelClassListAll){
                    if (data.getItem().toLowerCase().contains(constraint.toString().toLowerCase())) {
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
            ModelClassList.addAll((Collection<? extends ItemModelClass>) results.values);
            notifyDataSetChanged();
        }
    };

    public class RVHolderClass extends RecyclerView.ViewHolder {
        //TextView age,breed,price;
        TextView price,name;
        ImageView image;
        public RVHolderClass(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView2);
            // age = itemView.findViewById(R.id.age);
            name = itemView.findViewById(R.id.textView15);
            price = itemView.findViewById(R.id.textView13);
        }
    }
    @NonNull
    @NotNull
    @Override
    public RVadapterItem.RVHolderClass onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RVadapterItem.RVHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RVadapterItem.RVHolderClass holder, int position) {
        try {
            ItemModelClass obj = ModelClassList.get(position);
            // holder.age.setText(obj.getAge());
            holder.name.setText(obj.getItem());
            holder.price.setText(Float.toString(obj.getPrice()));
            holder.image.setImageBitmap(obj.getImage());
            holder.itemView.setOnClickListener(v -> {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                obj.getImage().compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                byte [] ImageByte = byteArrayOutputStream.toByteArray();

                Intent intent = new Intent(context,DetailAccessories.class);
                intent.putExtra("image",ImageByte);
                intent.putExtra("item",obj.getItem());
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

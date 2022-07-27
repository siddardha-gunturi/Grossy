package com.example.cynep;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Fragment_Home context;
    ArrayList<Category_model>categoryList;
    DatabaseReference databaseReference;



    public CustomAdapter(Fragment_Home context, ArrayList<Category_model> data) {
        this.context = context;
        this.categoryList = data;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item layout is inflated
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view,parent,false);


        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //we set data to holder
        final Category_model dat = categoryList.get(position);
        Glide.with(context)
                .load(dat.getImage())
                .into(holder.image);

        holder.name.setText(dat.getName());
        holder.cardView.setCardBackgroundColor(((int)(Math.random()*16777215)) | (0xFF << 24));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("key", dat.getName());
               Fragment_productsLayout newFragment = new Fragment_productsLayout();
               newFragment.setArguments(b);
                FragmentManager fragmentManager = context.getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack(null).commit();
            }
        });


    }



    @Override
    public int getItemCount() {
        return categoryList.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        CardView cardView;
        private final Context context;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.action_image);
            cardView = (CardView) itemView.findViewById(R.id.cardview);


        }
    }
}



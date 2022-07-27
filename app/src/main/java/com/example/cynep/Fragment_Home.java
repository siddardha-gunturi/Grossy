package com.example.cynep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;


public class Fragment_Home extends Fragment   {
            View view;
            RecyclerView recyclerView;
            CarouselView carouselView;
            ArrayList<Category_model> categoryList;
            DatabaseReference databaseReference;



    int corsol[]={R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four};



            @Nullable
            @Override
            public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_home,container,false);

                recyclerView = (RecyclerView) view.findViewById(R.id.rv_category);

                GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
                //assign to recyclerview
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);

                categoryList=new ArrayList<>();
                databaseReference= FirebaseDatabase.getInstance().getReference("catgories");
                databaseReference.keepSynced(true);
                getImageData();




                ImageListener imageListener = new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                        imageView.setImageResource(corsol[position]);
                    }
                }; // for coursol view


                carouselView = (CarouselView) view.findViewById(R.id.carouselview);
                carouselView.setPageCount(corsol.length);
                carouselView.setImageListener(imageListener);




                return view;
            }

    private void getImageData() {
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot di:dataSnapshot.getChildren()){
                    Category_model dat=di.getValue(Category_model.class);
                    categoryList.add(dat);
                }

                CustomAdapter customAdapter = new CustomAdapter(Fragment_Home.this,categoryList);
                recyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





}

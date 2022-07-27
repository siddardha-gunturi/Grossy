package com.example.cynep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Fragment_productsLayout extends Fragment {

        RecyclerView recyclerView;

        ArrayList<Product_model> productList;

        DatabaseReference databaseReference;




        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_products_layout,container,false);

            recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recyclerview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            //assign to recyclerview
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);


            String value=getArguments().getString("key");

            if(value.equals("Fruits and vegetables"))
            {
                productList=new ArrayList<>();


                databaseReference= FirebaseDatabase.getInstance().getReference("Fruits and vegetables");
                databaseReference.keepSynced(true);
                getImageData();
            }
            else if(value.equals("Dairy & Bakery"))
            {
                productList=new ArrayList<>();


                databaseReference= FirebaseDatabase.getInstance().getReference("Dairy and Bakery");
                databaseReference.keepSynced(true);
                getImageData();
            }
            else if(value.equals("Staples"))
            {
                productList=new ArrayList<>();


                databaseReference= FirebaseDatabase.getInstance().getReference("Staples");
                databaseReference.keepSynced(true);
                getImageData();
            }
            else if(value.equals("Snacks"))
            {
                productList=new ArrayList<>();


                databaseReference= FirebaseDatabase.getInstance().getReference("Snacks");
                databaseReference.keepSynced(true);
                getImageData();
            }
            else if(value.equals("Beverages"))
            {
                productList=new ArrayList<>();




                databaseReference= FirebaseDatabase.getInstance().getReference("Beverages");
                databaseReference.keepSynced(true);
                getImageData();
            }
            else if(value.equals("Personal Care"))
            {
                productList=new ArrayList<>();


                databaseReference= FirebaseDatabase.getInstance().getReference("Personal care");
                databaseReference.keepSynced(true);
                getImageData();
            }



            return view;
        }

        private void getImageData() {
            databaseReference.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot di:dataSnapshot.getChildren()){
                        Product_model dat=di.getValue(Product_model.class);
                        productList.add(dat);



                    }

                    ProductCustomAdapter productCustomAdapter = new ProductCustomAdapter(Fragment_productsLayout.this,productList);
                    recyclerView.setAdapter(productCustomAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
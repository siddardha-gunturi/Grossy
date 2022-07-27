package com.example.cynep;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProductCustomAdapter extends RecyclerView.Adapter<ProductCustomAdapter.MyViewHolder> {
    Fragment_productsLayout context;
    ArrayList<Product_model>productList;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    DatabaseReference cartref,itemsref;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Cart").child(String.valueOf(auth.getUid()));







    public ProductCustomAdapter(Fragment_productsLayout context, ArrayList<Product_model> data) {
        this.context = context;
        this.productList = data;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item layout is inflated
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view,parent,false);


        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //we set data to holder
        final Product_model dat = productList.get(position);

        Glide.with(context)
                .load(dat.getImage())
                .into(holder.image);

        holder.name.setText(dat.getName());
        holder.price.setText( String.valueOf(dat.getPrice()));





        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Cart_model cart_model = new Cart_model(dat.getName(),dat.getImage(),dat.getPrice());
              //  writeData(cart_model);



               // Bundle bundle = new Bundle();
               // bundle.putString("Name",dat.getName());
              //  bundle.putString("price", String.valueOf(dat.getPrice()));


              //  bundle.putString("image", dat.getImage());



                Toast.makeText(v.getContext(),"Added",Toast.LENGTH_LONG).show();


                Cart_model cart_model = new Cart_model();
                cart_model.setName(dat.getName().toString());
                cart_model.setImage(dat.getImage().toString());
                cart_model.setPrice((dat.getPrice()));
                cart_model.setQuantity(1);

                String id = myRef.push().getKey();

                itemsref = myRef.child(id);
                itemsref.setValue(cart_model);

    



          //      Fragment_Cart newFragment = new Fragment_Cart();
             //   newFragment.setArguments(bundle);
            //    FragmentManager fragmentManager = context.getParentFragmentManager();

              //  fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack(null).commit();

                /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("RefName");

                myRef.setValue(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice()
                ));*/
            }
        });

    }




    @Override
    public int getItemCount() {
        return productList.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        TextView price;
        Button button;
        CardView cardView;

        private final Context context;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = (TextView) itemView.findViewById(R.id.namein1thing);
            image = (ImageView) itemView.findViewById(R.id.imagefor1thing);
            price = (TextView) itemView.findViewById(R.id.pricein1nothing);
            button = (Button) itemView.findViewById(R.id.btnaddtocartin1thing);
            cardView = (CardView) itemView.findViewById(R.id.cardview_products);







        }
    }
}



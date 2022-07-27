package com.example.cynep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomAdapterCart extends RecyclerView.Adapter<CustomAdapterCart.MyViewHolder> {
    Fragment_Cart context;
    ArrayList<Cart_model> cartList;
    DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Cart").child(String.valueOf(FirebaseAuth.getInstance().getUid()));
    int cartprice = 0;

    public CustomAdapterCart(Fragment_Cart context,ArrayList<Cart_model> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartview,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Cart_model dat = cartList.get(position);
        Glide.with(context)
                .load(dat.getImage())
               .into(holder.image);


        holder.name.setText(dat.getName());
        holder.itemscost.setText( String.valueOf(dat.getPrice()));
        holder.noitems.setText(String.valueOf(dat.getQuantity()));







    }





    @Override
    public int getItemCount() { return cartList.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        TextView mrphere;
        TextView itemscost;
        Button add;
        Button sub;
        TextView noitems;
        CardView cardView;

        private final Context context;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            name = (TextView) itemView.findViewById(R.id.nameincart);
            image = (ImageView) itemView.findViewById(R.id.imageincart);
            mrphere = (TextView) itemView.findViewById(R.id.mrpincart);
            itemscost = (TextView) itemView.findViewById(R.id.priceincart);
            add = (Button) itemView.findViewById(R.id.btnaddcart);
            sub = (Button) itemView.findViewById(R.id.btnsubcart);
            noitems = (TextView) itemView.findViewById(R.id.finalitems);
            cardView = (CardView) itemView.findViewById(R.id.cardviewincart);





            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String names = name.getText().toString().trim();
                    myref.orderByChild("name").equalTo(names).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int a = Integer.parseInt(noitems.getText().toString().trim());
                            a++;
                            for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                String keys = childSnapshot.getKey();
                                myref.child(keys).child("quantity").setValue(a);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            });

            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String names = name.getText().toString().trim();
                    int a = Integer.parseInt(noitems.getText().toString().trim());
                    if (a > 1 ) {
                        myref.orderByChild("name").equalTo(names).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int a = Integer.parseInt(noitems.getText().toString().trim());
                                a--;
                                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                    String keys = childSnapshot.getKey();
                                    myref.child(keys).child("quantity").setValue(a);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                    else
                    {
                        myref.orderByChild("name").equalTo(names).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                    String keys = childSnapshot.getKey();
                                    myref.child(keys).removeValue();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            });
        }
    }
}

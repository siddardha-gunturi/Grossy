package com.example.cynep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Cart extends Fragment {

    ArrayList<Cart_model> cartList;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Button checkout;
    TextView totalprice;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    Button add,sub,fetchprice;
    int cartprice =0;
    Button buynow;
    TextView noitems;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclercart);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        checkout = (Button) view.findViewById(R.id.buynow);

        noitems = (TextView) view.findViewById(R.id.finalitems);

        add= (Button) view.findViewById(R.id.btnaddcart);

        sub = (Button) view.findViewById(R.id.btnsubcart);

        totalprice = (TextView) view.findViewById(R.id.totalprice);

        buynow = (Button) view.findViewById(R.id.buynow);

        





    //    String Name=getArguments().getString("Name");

      //  String price=getArguments().getString("price");

     //   String image_url = getArguments().getString("image");

     //   Toast.makeText(getActivity(), Name, Toast.LENGTH_SHORT).show();

      //  int priceint = Integer.parseInt(price);

      //  Cart_model dat = new Cart_model(Name,image_url,priceint);

        cartList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(auth.getUid());
        databaseReference.keepSynced(true);

        getImageData();

        fetchprice = (Button) view.findViewById(R.id.fetchprice);
        fetchprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<cartList.size();i++)
                {
                    Cart_model cartModel = cartList.get(i);
                    cartprice = cartprice + cartModel.getPrice()*cartModel.getQuantity();
                }
                totalprice.setText(String.valueOf(cartprice));
            }
        });

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_address fragment_address = new Fragment_address();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,fragment_address).addToBackStack(null).commit();
            }
        });



        return view;

    }

    private void getImageData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot di : dataSnapshot.getChildren()) {
                    Cart_model dat = di.getValue(Cart_model.class);
                    cartList.add(dat);








                    CustomAdapterCart customAdapterCart = new CustomAdapterCart(Fragment_Cart.this, cartList);
                    recyclerView.setAdapter(customAdapterCart);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}
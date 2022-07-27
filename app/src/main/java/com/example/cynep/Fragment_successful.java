package com.example.cynep;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_successful extends Fragment {
    Button shopagain;
    ImageView imageView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_successful, container, false);

        imageView = (ImageView) view.findViewById(R.id.succesful);


        shopagain = (Button) view.findViewById(R.id.shopagain);
        shopagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Drawer_Activity.class);
                startActivity(i);


            }
        });


        return view;
    }
}

package com.example.cynep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Fragment_address extends Fragment {
    Button ordernow;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_address, container, false);

        ordernow = (Button) view.findViewById(R.id.ordernow);
        ordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_successful newFragment = new Fragment_successful();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack(null).commit();


            }
        });

        return  view;
    }
}

package com.example.cynep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.
                        getActiveNetworkInfo().isConnected()) {
                    Intent i = new Intent(Splash_Activity.this, Signinpage.class);
                    finish();
                    startActivity(i);
                } else {
                    Intent i = new Intent(Splash_Activity.this, NoInternet_Activity.class);
                    startActivity(i);
                    finish();
                }
            }},2000);



    }
}
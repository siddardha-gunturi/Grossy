package com.example.cynep;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Drawer_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;


    DatabaseReference databaseReference;
    private FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);
        TextView email = (TextView) header.findViewById(R.id.headertextView);
        String cynepmail = auth.getCurrentUser().getEmail();

        email.setText(cynepmail);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home,R.id.offers)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.cart:
                Fragment_Cart newFragment = new Fragment_Cart();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack(null).commit();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem itemData = menu.findItem(R.id.cart);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        int fragmentscount =getFragmentManager().getBackStackEntryCount();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if(fragmentscount==1) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Drawer_Activity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        else
        {
            if(getFragmentManager().getBackStackEntryCount()>1)
            {
                getFragmentManager().popBackStack();
            }
            else
            {
                Drawer_Activity.super.onBackPressed();
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.offers:
                Fragment_offers newFragments = new Fragment_offers();
                FragmentManager fragmentManagers = getSupportFragmentManager();
                fragmentManagers.beginTransaction().replace(R.id.nav_host_fragment,newFragments).commit();
                break;

            case R.id.home:
                Fragment_Home newFragment = new Fragment_Home();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack(null).commit();
                break;


        }
        return true;
    }
}
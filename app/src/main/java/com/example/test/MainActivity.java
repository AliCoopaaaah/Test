package com.example.test;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String message = null;

        if(item.getItemId()==R.id.duck){
            message = getResources().getString(R.string.duck);
        } else if (item.getItemId()==R.id.dragon){
            message = getResources().getString(R.string.dragon);
        } else if (item.getItemId()==R.id.home){
            message = "home placeholder";
        } else if (item.getItemId()==R.id.dad_joke){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, new DadJoke());
            transaction.commit();
        } else if (item.getItemId()==R.id.exit){
            message = "exit placeholder";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        return true;
    }
}
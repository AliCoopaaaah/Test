package com.example.test;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Menu topMenu;
    Menu bottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionMenuView topbar = findViewById(R.id.top_toolbar);
        Menu topMenu = topbar.getMenu();
        getMenuInflater().inflate(R.menu.top_toolbar_menu, topMenu);

        ActionMenuView bottombar = findViewById(R.id.bottom_toolbar);
        Menu bottomMenu = bottombar.getMenu();
        getMenuInflater().inflate(R.menu.bottom_toolbar_menu, bottomMenu);
    }//onCreate


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}

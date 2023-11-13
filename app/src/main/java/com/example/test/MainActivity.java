package com.example.test;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView icon = findViewById(R.id.icon);
        icon.setImageResource(R.drawable.img);

        Button friends = findViewById(R.id.friends);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.friendsList);

        ArrayList<String> usernames = new ArrayList<String>();
        usernames.add("Friend 1");
        usernames.add("Friend 2");
        usernames.add("Friend 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_layout, usernames);
        list.setAdapter(adapter);
    }//onCreate

}
